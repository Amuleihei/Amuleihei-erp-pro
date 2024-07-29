/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tenant.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: TenantAppMenuType
 * @Description: 应用与菜单的关系中菜单的类型
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/29 17:01
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TenantAppMenuType implements SkyeyeEnumClass {
    PC(1, "PC端菜单", true, false),
    APP(2, "APP端菜单", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;
}
