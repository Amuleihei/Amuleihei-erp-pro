package com.skyeye.delivery.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.delivery.entity.ShopDeliveryTemplateCharge;


public interface ShopDeliveryTemplateChargeService extends SkyeyeBusinessService<ShopDeliveryTemplateCharge> {


    void shopDeliveryTemplateChargeList(InputObject inputObject, OutputObject outputObject);
}
