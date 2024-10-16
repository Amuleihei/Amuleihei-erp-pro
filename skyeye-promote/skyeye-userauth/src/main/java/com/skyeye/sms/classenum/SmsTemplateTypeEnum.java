/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: SmsTemplateTypeEnum
 * @Description: 短信的模板类型枚举
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 21:56
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SmsTemplateTypeEnum implements SkyeyeEnumClass {

    VERIFICATION_CODE(1, "验证码", true, false),
    NOTICE(2, "通知", true, true),
    PROMOTION(3, "营销", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
