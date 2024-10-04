package com.skyeye.adsense.service;


import com.skyeye.adsense.entity.Adsense;
import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface AdsenseService extends SkyeyeBusinessService<Adsense> {
    void streamlineAdsenseList(InputObject inputObject, OutputObject outputObject);
}
