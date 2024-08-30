/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface StatisticsService {

    void queryWarehousingDetails(InputObject inputObject, OutputObject outputObject);

    void queryOutgoingDetails(InputObject inputObject, OutputObject outputObject);

    void queryInComimgDetails(InputObject inputObject, OutputObject outputObject);

    void querySalesDetails(InputObject inputObject, OutputObject outputObject);

    void queryCustomerReconciliationDetails(InputObject inputObject, OutputObject outputObject);

    void querySupplierReconciliationDetails(InputObject inputObject, OutputObject outputObject);

}
