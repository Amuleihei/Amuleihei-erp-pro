package com.skyeye.delivery.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.delivery.entity.ShopDeliveryTemplate;


public interface ShopDeliveryTemplateService extends SkyeyeBusinessService<ShopDeliveryTemplate> {

    void shopDeliveryTemplateList(InputObject inputObject, OutputObject outputObject);
}
