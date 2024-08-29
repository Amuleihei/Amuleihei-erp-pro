/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.core.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName: SmsSendResp
 * @Description: 短信发送 Response DTO
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 23:03
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@Accessors(chain = true)
public class SmsSendResp {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * API 请求编号
     */
    private String apiRequestId;

    // ==================== 成功时字段 ====================

    /**
     * 短信 API 发送返回的序号
     */
    private String serialNo;

    // ==================== 失败时字段 ====================

    /**
     * API 返回错误码
     * <p>
     * 由于第三方的错误码可能是字符串，所以使用 String 类型
     */
    private String apiCode;
    /**
     * API 返回提示
     */
    private String apiMsg;

}
