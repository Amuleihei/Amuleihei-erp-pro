/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.purchase.service;

import com.skyeye.business.service.SkyeyeErpOrderService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.purchase.entity.PurchaseDelivery;

/**
 * @ClassName: PurchaseDeliveryService
 * @Description: 到货单服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/21 20:49
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface PurchaseDeliveryService extends SkyeyeErpOrderService<PurchaseDelivery> {

    /**
     * 修改质检状态
     *
     * @param id                到货单id
     * @param qualityInspection 质检状态
     */
    void editQualityInspection(String id, Integer qualityInspection);

    void deliveryToQualityInspection(InputObject inputObject, OutputObject outputObject);

    void queryPurchaseDeliveryTransById(InputObject inputObject, OutputObject outputObject);

    void deliveryToPurchasePut(InputObject inputObject, OutputObject outputObject);

    void queryPurchaseDeliveryTransPurchasePutById(InputObject inputObject, OutputObject outputObject);
}
