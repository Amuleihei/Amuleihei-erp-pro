package com.skyeye.delivery.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.delivery.entity.Delivery;


public interface DeliveryService extends SkyeyeBusinessService<Delivery> {

    void streamlineDeliveryList(InputObject inputObject, OutputObject outputObject);
}
