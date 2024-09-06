/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.classenum;

import cn.hutool.core.map.MapUtil;
import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MaterialShelvesState
 * @Description: 商品上下架状态枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/4 8:48
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum MaterialShelvesState implements SkyeyeEnumClass {
    NOT_ON_SHELVE(1, "未上架", "red", true, false),
    PART_ON_SHELVE(2, "部分上架", "blue", true, false),
    ON_SHELVE(3, "全部上架", "green", true, false);

    private Integer key;

    private String value;

    private String color;

    private Boolean show;

    private Boolean isDefault;

    public static Map<String, Object> getMation(Integer type) {
        for (MaterialShelvesState bean : MaterialShelvesState.values()) {
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
