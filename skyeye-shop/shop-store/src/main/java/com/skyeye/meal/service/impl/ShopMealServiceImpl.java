/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.meal.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.meal.dao.ShopMealDao;
import com.skyeye.meal.entity.ShopMeal;
import com.skyeye.meal.entity.ShopMealArea;
import com.skyeye.meal.entity.ShopMealConsume;
import com.skyeye.meal.service.ShopMealAreaService;
import com.skyeye.meal.service.ShopMealConsumeService;
import com.skyeye.meal.service.ShopMealService;
import com.skyeye.store.entity.ShopStore;
import com.skyeye.store.service.ShopAreaService;
import com.skyeye.store.service.ShopStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ShopMealServiceImpl
 * @Description: 套餐管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/5 15:12
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "套餐管理", groupName = "套餐管理")
public class ShopMealServiceImpl extends SkyeyeBusinessServiceImpl<ShopMealDao, ShopMeal> implements ShopMealService {

    @Autowired
    private ShopStoreService shopStoreService;

    @Autowired
    private ShopMealAreaService shopMealAreaService;

    @Autowired
    private ShopAreaService shopAreaService;

    @Autowired
    private ShopMealConsumeService shopMealConsumeService;

    @Override
    public void setCommonPageInfoOtherInfo(CommonPageInfo commonPageInfo) {
        super.setCommonPageInfoOtherInfo(commonPageInfo);
        if (StrUtil.isNotEmpty(commonPageInfo.getObjectId())) {
            // 如果门店id不为空，则获取门店所在区域的套餐
            ShopStore shopStore = shopStoreService.selectById(commonPageInfo.getObjectId());
            List<ShopMealArea> shopMealAreas = shopMealAreaService.queryMealAreaByAreaId(shopStore.getShopAreaId());
            List<String> mealIds = shopMealAreas.stream().map(ShopMealArea::getMealId).collect(Collectors.toList());
            commonPageInfo.setObjectIdList(mealIds);
        }
    }

    @Override
    public QueryWrapper<ShopMeal> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<ShopMeal> queryWrapper = super.getQueryWrapper(commonPageInfo);
        if (!StrUtil.equals(commonPageInfo.getType(), "All")) {
            if (CollectionUtil.isNotEmpty(commonPageInfo.getObjectIdList())) {
                queryWrapper.in(MybatisPlusUtil.toColumns(ShopMeal::getId), commonPageInfo.getObjectIdList());
            }
        }
        return queryWrapper;
    }

    @Override
    public void writePostpose(ShopMeal entity, String userId) {
        super.writePostpose(entity, userId);
        // 保存套餐耗材信息
        shopMealConsumeService.saveShopMealConsume(entity.getMealConsumeList(), entity.getId());
        // 保存套餐使用区域
        shopMealAreaService.saveShopMealArea(entity.getMealAreaList(), entity.getId());
    }

    @Override
    public void deletePostpose(String id) {
        // 删除套餐耗材信息
        shopMealConsumeService.deleteMealConsumeByMealId(id);
        // 删除套餐使用区域
        shopMealAreaService.deleteMealAreaByMealId(id);
    }

    @Override
    public ShopMeal getDataFromDb(String id) {
        ShopMeal shopMeal = super.getDataFromDb(id);
        // 套餐耗材信息
        shopMeal.setMealConsumeList(shopMealConsumeService.queryMealConsumeByMealId(shopMeal.getId()));
        // 套餐使用区域信息
        shopMeal.setMealAreaList(shopMealAreaService.queryMealAreaByMealId(shopMeal.getId()));
        return shopMeal;
    }

    @Override
    public List<ShopMeal> getDataFromDb(List<String> idList) {
        List<ShopMeal> shopMealList = super.getDataFromDb(idList);
        // 套餐耗材信息
        Map<String, List<ShopMealConsume>> mealConsumeMap = shopMealConsumeService.queryMealConsumeByMealId(idList.toArray(new String[]{}));
        // 套餐使用区域信息
        Map<String, List<ShopMealArea>> mealAreaMap = shopMealAreaService.queryMealAreaByMealId(idList.toArray(new String[]{}));
        for (ShopMeal shopMeal : shopMealList) {
            shopMeal.setMealConsumeList(mealConsumeMap.get(shopMeal.getId()));
            shopMeal.setMealAreaList(mealAreaMap.get(shopMeal.getId()));
        }
        return shopMealList;
    }

    @Override
    public ShopMeal selectById(String id) {
        ShopMeal shopMeal = super.selectById(id);
        shopAreaService.setDataMation(shopMeal.getMealAreaList(), ShopMealArea::getAreaId);
        shopMeal.setName(shopMeal.getTitle());
        return shopMeal;
    }

    @Override
    public List<ShopMeal> selectByIds(String... ids) {
        List<ShopMeal> shopMealList = super.selectByIds(ids);
        shopMealList.forEach(shopMeal -> {
            shopMeal.setName(shopMeal.getTitle());
        });
        return shopMealList;
    }

    @Override
    public void queryShopMealByStoreId(InputObject inputObject, OutputObject outputObject) {
        String storeId = inputObject.getParams().get("storeId").toString();
        if (StrUtil.isEmpty(storeId)) {
            return;
        }
        ShopStore shopStore = shopStoreService.selectById(storeId);
        // 获取门店所在区域的套餐
        List<ShopMealArea> shopMealAreas = shopMealAreaService.queryMealAreaByAreaId(shopStore.getShopAreaId());
        if (CollectionUtil.isEmpty(shopMealAreas)) {
            return;
        }
        List<String> mealIds = shopMealAreas.stream().map(ShopMealArea::getMealId).collect(Collectors.toList());
        QueryWrapper<ShopMeal> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(CommonConstants.ID, mealIds);
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopMeal::getEnabled), EnableEnum.ENABLE_USING.getKey());
        List<ShopMeal> shopMealList = list(queryWrapper);
        shopMealList.forEach(shopMeal -> {
            shopMeal.setName(shopMeal.getTitle());
        });
        outputObject.setBeans(shopMealList);
        outputObject.settotal(shopMealList.size());
    }
}
