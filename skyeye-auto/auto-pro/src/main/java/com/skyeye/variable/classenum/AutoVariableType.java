/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.variable.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: AutoVariableType
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/29 16:54
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum AutoVariableType implements SkyeyeEnumClass {

    GLOBAL_VARIABLE("globalVariable", "全局变量", true, false),
    RANDOM_VARIABLE("randomVariable", "随机变量", true, false),
    GLOBAL_HEADER("globalHeader", "全局Header", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
