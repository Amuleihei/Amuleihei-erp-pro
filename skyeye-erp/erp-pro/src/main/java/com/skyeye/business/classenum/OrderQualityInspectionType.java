/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.business.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: OrderQualityInspectionType
 * @Description: 单据质检类型枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/21 16:30
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OrderQualityInspectionType implements SkyeyeEnumClass {

    NOT_NEED_QUALITYINS_INS(1, "免检", "purple", true, true),
    NEED_QUALITYINS_INS(2, "需要质检", "blue", true, false),
    PARTIAL_QUALITY_INSPECTION(3, "部分质检完成", "orange", true, false),
    COMPLATE_QUALITY_INSPECTION(4, "全部质检完成", "green", true, false);


    private Integer key;

    private String value;

    private String color;

    private Boolean show;

    private Boolean isDefault;

}
