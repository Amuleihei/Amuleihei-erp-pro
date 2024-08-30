/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.inventory.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.inventory.entity.InventoryChildCode;

import java.util.List;

/**
 * @ClassName: InventoryChildCodeService
 * @Description: 盘点任务表-子单据关联的编码服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/18 17:18
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface InventoryChildCodeService extends SkyeyeBusinessService<InventoryChildCode> {

    void saveList(String orderId, List<InventoryChildCode> beans);

    void deleteByOrderId(String orderId);

    List<InventoryChildCode> selectByOrderId(String... orderId);

    List<InventoryChildCode> selectByParentId(String... parentId);

}
