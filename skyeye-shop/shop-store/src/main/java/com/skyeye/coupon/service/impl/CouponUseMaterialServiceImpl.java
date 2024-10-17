package com.skyeye.coupon.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.coupon.dao.CouponUseMaterialDao;
import com.skyeye.coupon.entity.CouponUseMaterial;
import com.skyeye.coupon.service.CouponUseMaterialService;
import org.springframework.stereotype.Service;

@Service
@SkyeyeService(name = "用户领取的优惠券适用商品对象管理", groupName = "用户领取的优惠券适用商品对象管理")
public class CouponUseMaterialServiceImpl extends SkyeyeBusinessServiceImpl<CouponUseMaterialDao, CouponUseMaterial> implements CouponUseMaterialService {
}
