/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.production.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.production.entity.ProductionPlanChild;

import java.util.List;

/**
 * @ClassName: ProductionPlanChildService
 * @Description: 预生产计划单子单据服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/21 20:37
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ProductionPlanChildService extends SkyeyeBusinessService<ProductionPlanChild> {

    void saveList(String parentId, List<ProductionPlanChild> beans);

    void deleteByParentId(String parentId);

    List<ProductionPlanChild> selectByParentId(String parentId);

    List<ProductionPlanChild> selectByParentId(List<String> parentIds);


}
