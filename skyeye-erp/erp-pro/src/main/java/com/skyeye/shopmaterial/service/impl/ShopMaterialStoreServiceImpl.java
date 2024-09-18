/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.shopmaterial.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.material.entity.Material;
import com.skyeye.rest.shop.service.IShopStoreService;
import com.skyeye.shopmaterial.dao.ShopMaterialStoreDao;
import com.skyeye.shopmaterial.entity.ShopMaterialStore;
import com.skyeye.shopmaterial.service.ShopMaterialStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ShopMaterialStoreServiceImpl
 * @Description: 商城商品上线的门店服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/18 14:10
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "商城商品上线的门店", groupName = "商城商品上线的门店", manageShow = false)
public class ShopMaterialStoreServiceImpl extends SkyeyeBusinessServiceImpl<ShopMaterialStoreDao, ShopMaterialStore> implements ShopMaterialStoreService {

    @Autowired
    private IShopStoreService iShopStoreService;

    @Override
    public void deleteByMaterialId(String materialId) {
        QueryWrapper<ShopMaterialStore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopMaterialStore::getMaterialId), materialId);
        remove(queryWrapper);
    }

    @Override
    public List<ShopMaterialStore> selectByMaterialId(String materialId) {
        QueryWrapper<ShopMaterialStore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopMaterialStore::getMaterialId), materialId);
        List<ShopMaterialStore> shopMaterialStoreList = list(queryWrapper);
        return shopMaterialStoreList;
    }

    @Override
    public Map<String, List<ShopMaterialStore>> selectByMaterialId(List<String> materialId) {
        if (CollectionUtil.isEmpty(materialId)) {
            return MapUtil.empty();
        }
        QueryWrapper<ShopMaterialStore> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(ShopMaterialStore::getMaterialId), materialId);
        List<ShopMaterialStore> shopMaterialStoreList = list(queryWrapper);
        Map<String, List<ShopMaterialStore>> collect = shopMaterialStoreList.stream().collect(Collectors.groupingBy(ShopMaterialStore::getMaterialId));
        return collect;
    }

    @Override
    public void saveList(String materialId, List<ShopMaterialStore> shopMaterialStoreList) {
        deleteByMaterialId(materialId);
        if (CollectionUtil.isNotEmpty(shopMaterialStoreList)) {
            for (ShopMaterialStore shopMaterialStore : shopMaterialStoreList) {
                shopMaterialStore.setMaterialId(materialId);
            }
            String userId = InputObject.getLogParamsStatic().get("id").toString();
            createEntity(shopMaterialStoreList, userId);
        }
    }

    @Override
    public void addAllStoreForMaterial(String materialId) {
        List<Map<String, Object>> storeList = iShopStoreService.queryStoreListByParams(StrUtil.EMPTY, null);
        if (CollectionUtil.isEmpty(storeList)) {
            return;
        }
        List<ShopMaterialStore> shopMaterialStoreList = storeList.stream().map(store -> {
            ShopMaterialStore shopMaterialStore = new ShopMaterialStore();
            shopMaterialStore.setStoreId(MapUtil.getStr(store, "id"));
            return shopMaterialStore;
        }).collect(Collectors.toList());
        saveList(materialId, shopMaterialStoreList);
    }

    @Override
    public void saveShopMaterialStore(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String storeId = MapUtil.getStr(params, "storeId");

        // 查询商品id
        QueryWrapper<ShopMaterialStore> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(MybatisPlusUtil.toColumns(ShopMaterialStore::getMaterialId));
        queryWrapper.groupBy(MybatisPlusUtil.toColumns(ShopMaterialStore::getMaterialId));
        List<ShopMaterialStore> shopMaterialStoreList = list(queryWrapper);
        if (CollectionUtil.isEmpty(shopMaterialStoreList)) {
            return;
        }
        // 构造新的门店商品数据进行保存
        List<String> materialIdList = shopMaterialStoreList.stream().map(ShopMaterialStore::getMaterialId).collect(Collectors.toList());
        List<ShopMaterialStore> newList = materialIdList.stream().map(materialId -> {
            ShopMaterialStore shopMaterialStore = new ShopMaterialStore();
            shopMaterialStore.setMaterialId(materialId);
            shopMaterialStore.setStoreId(storeId);
            return shopMaterialStore;
        }).collect(Collectors.toList());
        // 不管该门店之前有没有添加过商品，都先进行删除
        QueryWrapper<ShopMaterialStore> removeWrapper = new QueryWrapper<>();
        removeWrapper.eq(MybatisPlusUtil.toColumns(ShopMaterialStore::getStoreId), storeId);
        remove(removeWrapper);
        // 保存门店商品数据
        createEntity(newList, InputObject.getLogParamsStatic().get("id").toString());
    }

    @Override
    public List<ShopMaterialStore> queryShopMaterialList(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        Page pages = PageHelper.startPage(commonPageInfo.getPage(), commonPageInfo.getLimit());
        MPJLambdaWrapper<ShopMaterialStore> wrapper = new MPJLambdaWrapper<ShopMaterialStore>()
            .innerJoin(Material.class, Material::getId, ShopMaterialStore::getMaterialId)
            .like(StrUtil.isNotBlank(commonPageInfo.getKeyword()), Material::getName, commonPageInfo.getKeyword());

        List<ShopMaterialStore> shopMaterialList = skyeyeBaseMapper.selectJoinList(ShopMaterialStore.class, wrapper);
        iShopStoreService.setDataMation(shopMaterialList, ShopMaterialStore::getStoreId);
        outputObject.settotal(pages.getTotal());
        return shopMaterialList;
    }
}
