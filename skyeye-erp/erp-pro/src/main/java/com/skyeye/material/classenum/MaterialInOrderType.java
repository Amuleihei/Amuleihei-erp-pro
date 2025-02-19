/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: MaterialInOrderType
 * @Description: 商品在单据中的类型
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/13 16:30
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum MaterialInOrderType implements SkyeyeEnumClass {

    GENERAL(0, "普通", false, false),
    ASSEMBLY(1, "组合件", true, true),
    GENERAL_SUBASSEMBLY(2, "普通子件", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

    public static String getName(Integer type) {
        for (MaterialInOrderType bean : MaterialInOrderType.values()) {
            if (type == bean.getKey()) {
                return bean.getValue();
            }
        }
        return StringUtils.EMPTY;
    }

}
