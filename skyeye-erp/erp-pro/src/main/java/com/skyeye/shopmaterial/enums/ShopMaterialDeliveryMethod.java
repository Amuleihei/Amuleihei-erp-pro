/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.shopmaterial.enums;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ShopMaterialDeliveryMethod
 * @Description: 商城商品配送方式枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/4 17:32
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ShopMaterialDeliveryMethod implements SkyeyeEnumClass {

    DEFAULT_SET(1, "快递发货", true, false),
    SINGLE_SET(2, "用户自提", true, false),
    LOCAL_SET(3, "同城配送", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
