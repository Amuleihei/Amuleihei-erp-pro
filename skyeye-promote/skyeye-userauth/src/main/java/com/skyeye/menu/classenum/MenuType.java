/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menu.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: MenuType
 * @Description: APP菜单类型
 * @author: skyeye云系列--卫志强
 * @date: 2023/12/16 9:23
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum MenuType implements SkyeyeEnumClass {

    FOLDER(1, "目录", true, false),
    PAGE(2, "页面", true, true);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
