/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.core.entity;

import com.skyeye.sms.classenum.SmsTemplateAuditStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName: SmsTemplateResp
 * @Description: 短信模板 Response DTO
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 23:05
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@Accessors(chain = true)
public class SmsTemplateResp {

    /**
     * 模板编号
     */
    private String id;

    /**
     * 短信内容
     */
    private String content;

    /**
     * 审核状态
     * <p>
     * 枚举 {@link SmsTemplateAuditStatusEnum}
     */
    private Integer auditStatus;

    /**
     * 审核未通过的理由
     */
    private String auditReason;

}
