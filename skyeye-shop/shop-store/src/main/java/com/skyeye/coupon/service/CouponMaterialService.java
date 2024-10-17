package com.skyeye.coupon.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.coupon.entity.CouponMaterial;

import java.util.List;
import java.util.Map;

public interface CouponMaterialService extends SkyeyeBusinessService<CouponMaterial> {
    List<CouponMaterial> queryListByCouponId(String couponId);

    Map<String, List<CouponMaterial>> queryListByCouponId(List<String> parentIdList);
    void deleteByCouponId(String id);
}
