/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.shopmaterial.enums;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ShopMaterialDistributionType
 * @Description: 商城商品分销方式枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/4 17:32
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ShopMaterialDistributionType implements SkyeyeEnumClass {

    DEFAULT_SET(1, "默认设置", true, true),
    SINGLE_SET(2, "单独设置", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
