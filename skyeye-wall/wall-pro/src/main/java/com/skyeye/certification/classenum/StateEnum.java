/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.certification.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: StateEnum
 * @Description: 学生认证状态枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/15 8:58
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum StateEnum implements SkyeyeEnumClass {
    UNVERIFIED(1, "未认证", true, false),
    CERTIFIEDING(2, "认证中", true, false),
    CERTIFIEDFAILURE(3, "认证失败", true, false),
    CERTIFIEDSUCCESS(4, "认证成功", true, true);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;
}
