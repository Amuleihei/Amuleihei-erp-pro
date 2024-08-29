/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.core.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @ClassName: SmsReceiveResp
 * @Description: 消息接收 Response DTO
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 23:04
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@Accessors(chain = true)
public class SmsReceiveResp {

    /**
     * 是否接收成功
     */
    private Boolean success;
    /**
     * API 接收结果的编码
     */
    private String errorCode;
    /**
     * API 接收结果的说明
     */
    private String errorMsg;

    /**
     * 手机号
     */
    private String mobile;
    /**
     * 用户接收时间
     */
    private LocalDateTime receiveTime;

    /**
     * 短信 API 发送返回的序号
     */
    private String serialNo;
    /**
     * 短信日志编号
     * <p>
     * 对应 SysSmsLogDO 的编号
     */
    private Long logId;

}
