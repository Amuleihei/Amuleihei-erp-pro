/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.coupon.enums;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: CouponUseState
 * @Description: 优惠券使用状态枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/19 9:35
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CouponUseState implements SkyeyeEnumClass {

    UNUSED(1, "未使用", true, false),
    USED(2, "已使用", true, false),
    EXPIRE(3, "已过期", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;
}
