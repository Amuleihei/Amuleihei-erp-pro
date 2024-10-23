/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.coupon.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.coupon.entity.CouponMaterial;

import java.util.List;

/**
 * @ClassName: CouponMaterialService
 * @Description: 优惠券/模版适用商品对象管理服务接口
 * @author: skyeye云系列--卫志强
 * @date: 2024/10/23 10:38
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface CouponMaterialService extends SkyeyeBusinessService<CouponMaterial> {
    List<CouponMaterial> queryListByCouponId(String couponId);

    void deleteByCouponId(String id);

    void deleteByCouponId(List<String> ids);

    void insertCouponMaterial(String couponId, List<CouponMaterial> couponMaterialList, String userId);
}
