/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: SmsTemplateAuditStatusEnum
 * @Description: 短信模板的审核状态枚举
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 23:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SmsTemplateAuditStatusEnum implements SkyeyeEnumClass {

    CHECKING(1, "审核中", true, false),
    SUCCESS(2, "通过", true, false),
    FAIL(3, "失败", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
