/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.business.classenum;

import cn.hutool.core.map.MapUtil;
import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: OrderItemQualityInspectionType
 * @Description: 采购订单/采购入库单 质检类型枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/21 16:30
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OrderItemQualityInspectionType implements SkyeyeEnumClass {

    NOT_NEED_QUALITYINS_INS(1, "免检", true, true),
    SAMPLING_INS(2, "抽检", true, false),
    FULL_INSPECTION(3, "全检", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

    public static Map<String, Object> getMation(Integer type) {
        for (OrderItemQualityInspectionType bean : OrderItemQualityInspectionType.values()) {
            if (type == bean.getKey()) {
                Map<String, Object> result = new HashMap<>();
                result.put("id", bean.getKey());
                result.put("name", bean.getValue());
                return result;
            }
        }
        return MapUtil.newHashMap();
    }

}
