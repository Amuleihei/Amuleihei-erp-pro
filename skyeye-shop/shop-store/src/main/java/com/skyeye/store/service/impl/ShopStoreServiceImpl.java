/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.store.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.enumeration.WhetherEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.CalculationUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.store.dao.ShopStoreDao;
import com.skyeye.store.entity.ShopStore;
import com.skyeye.store.entity.ShopStoreStaff;
import com.skyeye.store.service.ShopAreaService;
import com.skyeye.store.service.ShopStoreService;
import com.skyeye.store.service.ShopStoreStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: StoreServiceImpl
 * @Description: 门店管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/4 12:35
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "门店管理", groupName = "门店管理")
public class ShopStoreServiceImpl extends SkyeyeBusinessServiceImpl<ShopStoreDao, ShopStore> implements ShopStoreService {

    @Autowired
    private ShopAreaService shopAreaService;

    @Autowired
    private ShopStoreStaffService shopStoreStaffService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        shopAreaService.setMationForMap(beans, "shopAreaId", "shopAreaMation");
        return beans;
    }

    @Override
    public ShopStore selectById(String id) {
        ShopStore shopStore = super.selectById(id);
        shopAreaService.setDataMation(shopStore, ShopStore::getShopAreaId);
        return shopStore;
    }

    @Override
    public List<ShopStore> selectByIds(String... ids) {
        List<ShopStore> shopStores = super.selectByIds(ids);
        shopAreaService.setDataMation(shopStores, ShopStore::getShopAreaId);
        return shopStores;
    }

    /**
     * 获取门店列表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryStoreListByParams(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String shopAreaId = params.get("shopAreaId").toString();
        String enabled = params.get("enabled").toString();
        QueryWrapper<ShopStore> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(shopAreaId)) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(ShopStore::getShopAreaId), shopAreaId);
        }
        if (StrUtil.isNotEmpty(enabled)) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(ShopStore::getEnabled), enabled);
        }
        List<ShopStore> list = list(queryWrapper);
        outputObject.setBeans(list);
        outputObject.settotal(list.size());
    }

    /**
     * 根据门店ID获取门店设置的线上预约信息(已结合当前登陆用户)
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryStoreOnlineById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String id = params.get("id").toString();
        ShopStore shopStore = selectById(id);

        // 获取该门店的成员
        List<ShopStoreStaff> shopStoreStaffs = shopStoreStaffService.getShopStoresByStoreId(id);
        Map<String, Map<String, Object>> userStaffMap = iAuthUserService.queryUserMationListByStaffIds(
            shopStoreStaffs.stream().map(ShopStoreStaff::getStaffId).collect(Collectors.toList()));
        shopStoreStaffs.forEach(shopStoreStaff -> {
            shopStoreStaff.setStaffMation(userStaffMap.get(shopStoreStaff.getStaffId()));
        });
        shopStore.setStoreStaffList(shopStoreStaffs);

        outputObject.setBean(shopStore);
        outputObject.settotal(1);
    }

    /**
     * 保存门店线上预约信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void saveStoreOnlineMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        UpdateWrapper<ShopStore> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, params.get("id").toString());
        updateWrapper.set(MybatisPlusUtil.toColumns(ShopStore::getStartTime), params.get("startTime").toString());
        updateWrapper.set(MybatisPlusUtil.toColumns(ShopStore::getEndTime), params.get("endTime").toString());
        updateWrapper.set(MybatisPlusUtil.toColumns(ShopStore::getOnlineBookAppoint), params.get("onlineBookAppoint").toString());
        if (Integer.parseInt(params.get("onlineBookAppoint").toString()) == WhetherEnum.ENABLE_USING.getKey()) {
            // 开启线上预约
            updateWrapper.set(MybatisPlusUtil.toColumns(ShopStore::getOnlineBookRadix), params.get("onlineBookRadix").toString());
            updateWrapper.set(MybatisPlusUtil.toColumns(ShopStore::getOnlineBookType), params.get("onlineBookType").toString());
            updateWrapper.set(MybatisPlusUtil.toColumns(ShopStore::getOnlineBookJson), params.get("onlineBookJson").toString());
        } else {
            updateWrapper.set(MybatisPlusUtil.toColumns(ShopStore::getOnlineBookRadix), null);
            updateWrapper.set(MybatisPlusUtil.toColumns(ShopStore::getOnlineBookType), null);
            updateWrapper.set(MybatisPlusUtil.toColumns(ShopStore::getOnlineBookJson), null);
        }
        update(updateWrapper);
        refreshCache(params.get("id").toString());
    }

    /**
     * 获取门店指定日期的预约信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryStoreOnlineMationPointDay(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String id = params.get("id").toString();
        String onlineDay = params.get("onlineDay").toString();
        ShopStore shopStore = selectById(id);

        // 是否开启线上预约
        if (shopStore.getOnlineBookAppoint() == WhetherEnum.ENABLE_USING.getKey()) {
            // 获取预约时间段信息
            List<String> onlineTime = shopStore.getOnlineBookJson().stream().map(bean -> bean.get("time").toString()).collect(Collectors.toList());
            // 获取现有的预约信息
            List<Map<String, Object>> onlineAppointmentMation = skyeyeBaseMapper.queryOnlineAppointmentMation(onlineDay, onlineTime);
            Map<String, String> onlineAppointmentMap = onlineAppointmentMation
                .stream().collect(Collectors.toMap(bean -> bean.get("onlineTime").toString(), bean -> bean.get("onlineOrderNum").toString()));
            // 循环遍历预约时间点，减去预约日期指定时间段内已有的预约单数
            shopStore.getOnlineBookJson().forEach(bean -> {
                if (onlineAppointmentMap.containsKey(bean.get("time").toString())) {
                    bean.put("value", CalculationUtil.subtract(bean.get("onlineOrderNum").toString(), onlineAppointmentMap.get(bean.get("time").toString())));
                }
            });
        }
        outputObject.setBean(shopStore);
        outputObject.settotal(1);
    }

}
