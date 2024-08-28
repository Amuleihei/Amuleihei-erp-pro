/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserInstallMenuSize
 * @Description: 开始菜单尺寸枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/18 11:51
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserInstallMenuSize implements SkyeyeEnumClass {

    MINI("xs", "迷你", true, true),
    MIDDLE("sm", "中等", true, false),
    WIDE("lg", "宽敞", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
