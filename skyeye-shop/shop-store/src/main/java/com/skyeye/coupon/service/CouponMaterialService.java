package com.skyeye.coupon.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.coupon.entity.CouponMaterial;

import java.util.List;
import java.util.Map;

public interface CouponMaterialService extends SkyeyeBusinessService<CouponMaterial> {
    List<CouponMaterial> queryListByCouponId(String couponId);

    void deleteByCouponId(String id);

    void deleteByCouponId(List<String> ids);

    void insertCouponMaterial(String  couponId, List<CouponMaterial> couponMaterialList, String userId);
}
