package com.skyeye.coupon.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.coupon.entity.CouponUse;

public interface CouponUseService extends SkyeyeBusinessService<CouponUse> {
    void setStateByCouponUse();
}
