/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.production.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ProductionOutState
 * @Description: 生产计划单委外状态枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/31 11:54
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ProductionOutState implements SkyeyeEnumClass {

    NOT_NEED_OUT(1, "无需委外", "purple", true, true),
    NEED_OUT(2, "待委外", "blue", true, false),
    PARTIAL_OUT(3, "部分委外", "orange", true, false),
    COMPLATE_OUT(4, "全部委外", "green", true, false);


    private Integer key;

    private String value;

    private String color;

    private Boolean show;

    private Boolean isDefault;

}
