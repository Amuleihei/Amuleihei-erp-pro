/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserIsTermOfValidity
 * @Description: 用户是否长期有效枚举
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/20 21:33
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserIsTermOfValidity implements SkyeyeEnumClass {

    LONG_TERM(1, "长期有效", true, true),
    EFFECTIVE_TIME_PERIOD(2, "时间段有效", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
