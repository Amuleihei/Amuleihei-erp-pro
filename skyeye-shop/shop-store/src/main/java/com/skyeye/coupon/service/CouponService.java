package com.skyeye.coupon.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.coupon.entity.Coupon;

public interface CouponService extends SkyeyeBusinessService<Coupon> {
    void updateTakeCount(String couponId, Integer takeCount);

    void updateUseCount(String couponId, int useCount);

    void setStateByCoupon();

    void queryCouponListByState(InputObject inputObject, OutputObject outputObject);
}
