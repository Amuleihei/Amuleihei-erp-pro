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
import com.skyeye.meal.dao.ShopMealAreaDao;
import com.skyeye.meal.entity.ShopMealArea;
import com.skyeye.meal.service.ShopMealAreaService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ShopMealAreaServiceImpl
 * @Description: 套餐使用区域服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/13 22:31
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "套餐使用区域管理", groupName = "套餐使用区域管理", manageShow = false)
public class ShopMealAreaServiceImpl extends SkyeyeBusinessServiceImpl<ShopMealAreaDao, ShopMealArea> implements ShopMealAreaService {

    @Override
    public void deleteMealAreaByMealId(String mealId) {
        QueryWrapper<ShopMealArea> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopMealArea::getMealId), mealId);
        remove(queryWrapper);
    }

    @Override
    public void saveShopMealArea(List<ShopMealArea> shopMealAreaList, String mealId) {
        deleteMealAreaByMealId(mealId);
        if (CollectionUtil.isNotEmpty(shopMealAreaList)) {
            shopMealAreaList.forEach(bean -> {
                bean.setMealId(mealId);
            });
            createEntity(shopMealAreaList, StrUtil.EMPTY);
        }
    }

    @Override
    public List<ShopMealArea> queryMealAreaByMealId(String mealId) {
        QueryWrapper<ShopMealArea> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopMealArea::getMealId), mealId);
        return list(queryWrapper);
    }

    @Override
    public Map<String, List<ShopMealArea>> queryMealAreaByMealId(String... mealId) {
        List<String> mealIdList = Arrays.asList(mealId);
        if (CollectionUtil.isEmpty(mealIdList)) {
            return MapUtil.newHashMap();
        }
        QueryWrapper<ShopMealArea> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(ShopMealArea::getMealId), mealIdList);
        List<ShopMealArea> list = list(queryWrapper);
        Map<String, List<ShopMealArea>> listMap = list.stream()
            .collect(Collectors.groupingBy(ShopMealArea::getMealId));
        return listMap;
    }

    @Override
    public List<ShopMealArea> queryMealAreaByAreaId(String areaId) {
        QueryWrapper<ShopMealArea> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopMealArea::getAreaId), areaId);
        return list(queryWrapper);
    }

}
