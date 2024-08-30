/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.meal.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: ShopMealType
 * @Description: 套餐分类枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/13 17:18
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ShopMealType implements SkyeyeEnumClass {

    MAINTENANCE_PACKAGE(1, "保养套餐", true, false),
    WARRANTY_PACKAGE(2, "保修套餐", true, true);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

    public static String getShowName(Integer type) {
        for (ShopMealType value : ShopMealType.values()) {
            if (value.getKey().equals(type)) {
                return value.getValue();
            }
        }
        return StringUtils.EMPTY;
    }

}
