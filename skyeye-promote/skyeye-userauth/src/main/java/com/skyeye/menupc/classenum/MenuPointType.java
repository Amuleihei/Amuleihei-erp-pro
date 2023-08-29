/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menupc.classenum;

import cn.hutool.core.util.StrUtil;
import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: MenuPointType
 * @Description: 菜单权限点类型枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/29 21:27
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum MenuPointType implements SkyeyeEnumClass {

    AUTH_POINT(1, "权限点", true, true),
    DATA_GROUP(2, "数据分组", true, false),
    DATA_POINT(3, "数据权限", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

    public static String getTypeName(Integer type) {
        for (MenuPointType bean : MenuPointType.values()) {
            if (bean.getKey() == type) {
                return bean.getValue();
            }
        }
        return StrUtil.EMPTY;
    }
}
