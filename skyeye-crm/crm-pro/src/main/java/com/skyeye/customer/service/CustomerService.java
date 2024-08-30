/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.customer.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.customer.entity.CustomerMation;

public interface CustomerService extends SkyeyeBusinessService<CustomerMation> {

    void queryInternationalCustomerList(InputObject inputObject, OutputObject outputObject);

}
