/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.datafrom.classenum;

import cn.hutool.core.util.StrUtil;
import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ReportDataFromType
 * @Description: 数据来源类型
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/3 21:08
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ReportDataFromType implements SkyeyeEnumClass {

    XML(1, "XML数据源", true, true),
    JSON(2, "JSON数据源", true, false),
    REST(3, "Rest接口数据源", true, false),
    SQL(4, "SQL数据源", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

    public static String getNameByType(Integer type) {
        for (ReportDataFromType q : ReportDataFromType.values()) {
            if (q.getKey().equals(type)) {
                return q.getValue();
            }
        }
        return StrUtil.EMPTY;
    }

}
