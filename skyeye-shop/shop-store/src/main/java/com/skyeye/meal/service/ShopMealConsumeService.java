/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.meal.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.meal.entity.ShopMealConsume;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ShopMealConsumeService
 * @Description: 套餐耗材管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/15 8:32
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ShopMealConsumeService extends SkyeyeBusinessService<ShopMealConsume> {

    void deleteMealConsumeByMealId(String mealId);

    void saveShopMealConsume(List<ShopMealConsume> shopMealConsumeList, String mealId);

    List<ShopMealConsume> queryMealConsumeByMealId(String mealId);

    Map<String, List<ShopMealConsume>> queryMealConsumeByMealId(String... mealId);

}
