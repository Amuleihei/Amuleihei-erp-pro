/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.seal.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: SealBgColorType
 * @Description: 印章背景类型枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/23 22:04
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SealBgColorType implements SkyeyeEnumClass {

    WHITE_BACKGROUND(1, "白色背景", true, true),
    TRANSPARENT_BACKGROUND(2, "透明背景", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
