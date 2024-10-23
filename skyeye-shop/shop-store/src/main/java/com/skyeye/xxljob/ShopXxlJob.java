/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.xxljob;

import com.skyeye.coupon.service.CouponService;
import com.skyeye.coupon.service.CouponUseService;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ShopXxlJob
 * @Description: 优惠券过期记录
 * @author: skyeye云系列--卫志强
 * @date: 2023/10/11 19:20
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Component
public class ShopXxlJob {

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponUseService couponUseService;

    @XxlJob("setStateByCoupon")
    public void setStateByCoupon() {
        couponService.setStateByCoupon();
    }

    @XxlJob("setStateByCouponUse")
    public void setStateByCouponUse() {
        couponUseService.setStateByCouponUse();
    }
}
