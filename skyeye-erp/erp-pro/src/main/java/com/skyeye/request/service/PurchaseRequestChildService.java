/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.request.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.request.entity.PurchaseRequestChild;

import java.util.List;

/**
 * @ClassName: PurchaseRequestChildService
 * @Description: 采购申请-子单据服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/22 11:07
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface PurchaseRequestChildService extends SkyeyeBusinessService<PurchaseRequestChild> {

    void saveList(String parentId, List<PurchaseRequestChild> beans);

    void deleteByParentId(String parentId);

    List<PurchaseRequestChild> selectByParentId(String parentId);

    String calcOrderAllTotalPrice(List<PurchaseRequestChild> purchaseRequestChildList);
}
