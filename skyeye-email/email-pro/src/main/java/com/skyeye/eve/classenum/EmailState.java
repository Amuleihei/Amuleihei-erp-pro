/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: EmailState
 * @Description: 邮件状态枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/8 9:11
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum EmailState implements SkyeyeEnumClass {

    DRAFT(0, "草稿", true, false),
    NORMAL(1, "正常", true, false),
    DELETE(2, "已删除", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
