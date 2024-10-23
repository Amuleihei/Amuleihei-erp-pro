/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.coupon.enums;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: PromotionMaterialScope
 * @Description: 优惠券/模版使用商品范围枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/19 9:02
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PromotionMaterialScope implements SkyeyeEnumClass {

    ALL(1, "全部商品", true, true),
    SPU(2, "指定商品", true, false),
    CATEGORY(3, "指定品类", false, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;
}
