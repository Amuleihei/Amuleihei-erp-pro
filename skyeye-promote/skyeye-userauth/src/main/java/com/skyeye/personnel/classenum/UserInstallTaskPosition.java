/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserInstallTaskPosition
 * @Description: 任务栏在屏幕的位置枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/18 11:51
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserInstallTaskPosition implements SkyeyeEnumClass {

    TOP("top", "顶部", true, true),
    BOTTOM("bottom", "底部", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
