
/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.demand.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: AutoDemandStateEnum
 * @Description: 需求表状态枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/26 8:14
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum AutoDemandStateEnum implements SkyeyeEnumClass {

    WAIT_RESEARCH("waitResearch", "待研发", true, false),
    RESEARCH("research", "研发中", true, false),
    WAIT_TEST("waitTest", "待测试", true, false),
    FINISH("finish", "已完成", true, false),
    INVALID("invalid", "作废", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}


