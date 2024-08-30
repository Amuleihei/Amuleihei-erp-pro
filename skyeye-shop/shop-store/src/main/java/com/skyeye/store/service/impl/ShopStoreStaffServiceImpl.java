/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.store.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.store.dao.ShopStoreStaffDao;
import com.skyeye.store.entity.ShopArea;
import com.skyeye.store.entity.ShopStore;
import com.skyeye.store.entity.ShopStoreStaff;
import com.skyeye.store.service.ShopStoreService;
import com.skyeye.store.service.ShopStoreStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName: ShopStoreStaffServiceImpl
 * @Description: 门店与员工的关系服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/4 21:14
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "门店与员工的关系管理", groupName = "门店与员工的关系管理", manageShow = false)
public class ShopStoreStaffServiceImpl extends SkyeyeBusinessServiceImpl<ShopStoreStaffDao, ShopStoreStaff> implements ShopStoreStaffService {

    @Autowired
    private ShopStoreService shopStoreService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        if (StrUtil.isEmpty(commonPageInfo.getObjectId())) {
            return CollectionUtil.newArrayList();
        }
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        // 设置员工信息
        List<String> staffIds = beans.stream().map(bean -> bean.get("staffId").toString())
            .filter(staffId -> StrUtil.isNotEmpty(staffId)).distinct().collect(Collectors.toList());
        Map<String, Map<String, Object>> staffMap = iAuthUserService.queryUserMationListByStaffIds(staffIds);
        beans.forEach(bean -> {
            String staffId = bean.get("staffId").toString();
            bean.put("staffMation", staffMap.get(staffId));
        });
        return beans;
    }

    @Override
    public QueryWrapper<ShopStoreStaff> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<ShopStoreStaff> queryWrapper = super.getQueryWrapper(commonPageInfo);
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopStoreStaff::getStoreId), commonPageInfo.getObjectId());
        return queryWrapper;
    }

    /**
     * 新增门店下的员工信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void insertStoreStaffMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String storeId = params.get("storeId").toString();
        List<String> staffId = (List<String>) params.get("staffId");

        QueryWrapper<ShopStoreStaff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopStoreStaff::getStoreId), storeId);
        List<ShopStoreStaff> list = list(queryWrapper);
        List<String> storeStaffIdList = list.stream().map(ShopStoreStaff::getStaffId).collect(Collectors.toList());

        List<ShopStoreStaff> beans = new ArrayList<>();
        String userId = inputObject.getLogParams().get("id").toString();
        for (String str : staffId) {
            if (storeStaffIdList.contains(str)) {
                // 如果该门店已经存在这个员工，则跳过
                continue;
            }
            if (StrUtil.isNotEmpty(str)) {
                ShopStoreStaff item = new ShopStoreStaff();
                item.setStoreId(storeId);
                item.setStaffId(str);
                beans.add(item);
            }
        }
        if (CollectionUtil.isNotEmpty(beans)) {
            createEntity(beans, userId);
        }
    }

    /**
     * 获取当前登陆用户所属的区域列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryStaffBelongAreaList(InputObject inputObject, OutputObject outputObject) {
        String staffId = inputObject.getLogParams().get("staffId").toString();

        List<ShopArea> shopAreaList = queryStaffBelongAreaListByStaffId(staffId);
        outputObject.setBeans(shopAreaList);
        outputObject.settotal(shopAreaList.size());
    }

    @Override
    public List<ShopArea> queryStaffBelongAreaListByStaffId(String staffId) {
        List<ShopStore> shopStores = getShopStoresByStaffId(staffId);
        List<ShopArea> shopAreaList = new ArrayList<>(shopStores.stream()
            .filter(bean -> ObjectUtil.isNotEmpty(bean.getShopAreaMation()))
            .map(ShopStore::getShopAreaMation)
            .collect(Collectors.toMap(ShopArea::getId, Function.identity(), (oldValue, newValue) -> oldValue))
            .values());
        return shopAreaList;
    }

    /**
     * 获取当前登陆用户所属的门店列表(只包含已启用门店)
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryStaffBelongStoreList(InputObject inputObject, OutputObject outputObject) {
        String staffId = inputObject.getLogParams().get("staffId").toString();
        List<ShopStore> shopStores = getShopStoresByStaffId(staffId);
        shopStores = shopStores.stream().filter(store -> store.getEnabled().equals(EnableEnum.ENABLE_USING.getKey())).collect(Collectors.toList());
        outputObject.setBeans(shopStores);
        outputObject.settotal(shopStores.size());
    }

    private List<ShopStore> getShopStoresByStaffId(String staffId) {
        QueryWrapper<ShopStoreStaff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopStoreStaff::getStaffId), staffId);
        List<ShopStoreStaff> list = list(queryWrapper);
        // 获取门店id
        List<String> storeIds = list.stream().map(ShopStoreStaff::getStoreId).collect(Collectors.toList());
        // 查询门店信息
        List<ShopStore> shopStores = shopStoreService.selectByIds(storeIds.toArray(new String[]{}));
        return shopStores;
    }

    @Override
    public List<ShopStoreStaff> getShopStoresByStoreId(String storeId) {
        QueryWrapper<ShopStoreStaff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopStoreStaff::getStoreId), storeId);
        List<ShopStoreStaff> list = list(queryWrapper);
        return list;
    }

    /**
     * 根据员工id删除所有的所属门店信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void deleteStoreStaffMationByStaffId(InputObject inputObject, OutputObject outputObject) {
        String staffId = inputObject.getParams().get("id").toString();

        QueryWrapper<ShopStoreStaff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopStoreStaff::getStaffId), staffId);
        remove(queryWrapper);
    }
}
