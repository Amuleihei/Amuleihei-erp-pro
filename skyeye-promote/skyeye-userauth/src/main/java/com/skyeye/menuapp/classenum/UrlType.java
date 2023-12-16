/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menuapp.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UrlType
 * @Description: APP菜单URL类型
 * @author: skyeye云系列--卫志强
 * @date: 2023/12/16 9:21
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UrlType implements SkyeyeEnumClass {

    EXTERNAL_SYSTEM_MENU(1, "外部系统菜单", true, false),
    SELF_SYSTEM_MENU(2, "自身系统菜单", true, true);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
