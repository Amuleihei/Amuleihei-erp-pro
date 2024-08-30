/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.request.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.request.entity.PurchaseRequestFixedChild;

import java.util.List;

/**
 * @ClassName: PurchaseRequestFixedChildService
 * @Description: 采购申请-定价子单据服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/26 14:45
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface PurchaseRequestFixedChildService extends SkyeyeBusinessService<PurchaseRequestFixedChild> {

    void saveList(String parentId, List<PurchaseRequestFixedChild> beans);

    void deleteByParentId(String parentId);

    List<PurchaseRequestFixedChild> selectByParentId(String parentId);

    String calcOrderAllTotalPrice(List<PurchaseRequestFixedChild> purchaseRequestFixedChildList);

}
