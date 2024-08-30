/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.history.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: AutoHistoryCaseExecuteResult
 * @Description: 执行结果枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/16 20:18
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum AutoHistoryCaseExecuteResult implements SkyeyeEnumClass {

    IN_PROGRESS(1, "执行中", true, false),
    EXECUTION_SUCCESSFUL(2, "执行成功", true, false),
    EXECUTION_FAILED(3, "执行失败", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
