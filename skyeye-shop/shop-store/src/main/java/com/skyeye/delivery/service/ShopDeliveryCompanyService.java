package com.skyeye.delivery.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.delivery.entity.ShopDeliveryCompany;


public interface ShopDeliveryCompanyService extends SkyeyeBusinessService<ShopDeliveryCompany> {

    void streamlineDeliveryList(InputObject inputObject, OutputObject outputObject);
}
