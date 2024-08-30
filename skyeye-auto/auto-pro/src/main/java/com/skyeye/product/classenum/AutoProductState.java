/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.product.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum AutoProductState implements SkyeyeEnumClass {
    NEW("new", "新建", true, false),
    PROGRESS("progress", "进行中", true, false),
    COMPLETE("complete", "完成", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;
}
