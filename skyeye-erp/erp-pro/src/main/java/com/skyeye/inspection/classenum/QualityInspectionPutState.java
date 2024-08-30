/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.inspection.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: QualityInspectionPutState
 * @Description: 质检单入库状态枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/31 11:54
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum QualityInspectionPutState implements SkyeyeEnumClass {

    NOT_NEED_PUT(1, "无需入库", true, true),
    NEED_PUT(2, "待入库", true, false),
    PARTIAL_PUT(3, "部分入库", true, false),
    COMPLATE_PUT(4, "全部入库", true, false);


    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
