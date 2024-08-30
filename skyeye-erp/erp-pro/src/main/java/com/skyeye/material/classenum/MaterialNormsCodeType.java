/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.classenum;

import cn.hutool.core.map.MapUtil;
import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MaterialNormsCodeType
 * @Description: 商品规格一物一码类型枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2022/12/25 11:21
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum MaterialNormsCodeType implements SkyeyeEnumClass {

    AUTHENTIC(1, "正品", true, false),
    SCRAP_REPORTING(2, "报废品", true, false),
    DEFECTIVE_PRODUCTS(3, "残次品", true, false),
    SECOND_HAND_GOODS(4, "二手商品", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

    public static String getName(Integer state) {
        for (MaterialNormsCodeType bean : MaterialNormsCodeType.values()) {
            if (state == bean.getKey()) {
                return bean.getValue();
            }
        }
        return StringUtils.EMPTY;
    }

    public static Map<String, Object> getMation(Integer type) {
        for (MaterialNormsCodeType bean : MaterialNormsCodeType.values()) {
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
