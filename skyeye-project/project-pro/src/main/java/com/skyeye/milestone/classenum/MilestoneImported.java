/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.milestone.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: MilestoneImported
 * @Description: 里程碑重要性
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/14 20:27
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum MilestoneImported implements SkyeyeEnumClass {

    ORDINARY("ordinary", "普通", true, false),
    COMMONLY("commonly", "一般", true, false),
    IMPORTANT("important", "重要", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
