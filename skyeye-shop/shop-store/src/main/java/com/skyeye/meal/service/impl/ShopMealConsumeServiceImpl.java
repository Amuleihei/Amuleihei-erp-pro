/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.meal.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.meal.dao.ShopMealConsumeDao;
import com.skyeye.meal.entity.ShopMealConsume;
import com.skyeye.meal.service.ShopMealConsumeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ShopMealConsumeServiceImpl
 * @Description: 套餐耗材管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/15 8:32
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "套餐耗材管理", groupName = "套餐耗材管理", manageShow = false)
public class ShopMealConsumeServiceImpl extends SkyeyeBusinessServiceImpl<ShopMealConsumeDao, ShopMealConsume> implements ShopMealConsumeService {

    @Override
    public void deleteMealConsumeByMealId(String mealId) {
        QueryWrapper<ShopMealConsume> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopMealConsume::getMealId), mealId);
        remove(queryWrapper);
    }

    @Override
    public void saveShopMealConsume(List<ShopMealConsume> shopMealConsumeList, String mealId) {
        deleteMealConsumeByMealId(mealId);
        if (CollectionUtil.isNotEmpty(shopMealConsumeList)) {
            shopMealConsumeList.forEach(bean -> {
                bean.setMealId(mealId);
            });
            createEntity(shopMealConsumeList, StrUtil.EMPTY);
        }
    }

    @Override
    public List<ShopMealConsume> queryMealConsumeByMealId(String mealId) {
        QueryWrapper<ShopMealConsume> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopMealConsume::getMealId), mealId);
        return list(queryWrapper);
    }

    @Override
    public Map<String, List<ShopMealConsume>> queryMealConsumeByMealId(String... mealId) {
        List<String> mealIdList = Arrays.asList(mealId);
        if (CollectionUtil.isEmpty(mealIdList)) {
            return MapUtil.newHashMap();
        }
        QueryWrapper<ShopMealConsume> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(ShopMealConsume::getMealId), mealIdList);
        List<ShopMealConsume> shopMealConsumeList = list(queryWrapper);
        Map<String, List<ShopMealConsume>> listMap = shopMealConsumeList.stream()
            .collect(Collectors.groupingBy(ShopMealConsume::getMealId));
        return listMap;
    }
}
