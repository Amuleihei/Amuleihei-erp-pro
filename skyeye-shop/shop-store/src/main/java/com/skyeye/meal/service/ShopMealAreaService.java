/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.meal.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.meal.entity.ShopMealArea;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ShopMealAreaService
 * @Description: 套餐使用区域服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/13 22:31
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ShopMealAreaService extends SkyeyeBusinessService<ShopMealArea> {

    void deleteMealAreaByMealId(String mealId);

    void saveShopMealArea(List<ShopMealArea> shopMealAreaList, String mealId);

    List<ShopMealArea> queryMealAreaByMealId(String mealId);

    Map<String, List<ShopMealArea>> queryMealAreaByMealId(String... mealId);

    List<ShopMealArea> queryMealAreaByAreaId(String areaId);

}
