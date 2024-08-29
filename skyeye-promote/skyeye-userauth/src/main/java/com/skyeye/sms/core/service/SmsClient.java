/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.core.service;

import com.skyeye.common.entity.KeyValue;
import com.skyeye.sms.core.entity.SmsReceiveResp;
import com.skyeye.sms.core.entity.SmsSendResp;
import com.skyeye.sms.core.entity.SmsTemplateResp;

import java.util.List;

/**
 * @ClassName: SmsClient
 * @Description: 短信客户端，用于对接各短信平台的 SDK，实现短信发送等功能
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 23:02
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SmsClient {

    /**
     * 获得渠道编号
     *
     * @return 渠道编号
     */
    String getId();

    /**
     * 发送消息
     *
     * @param mobile         手机号
     * @param apiTemplateId  短信 API 的模板编号
     * @param templateParams 短信模板参数。通过 List 数组，保证参数的顺序
     * @return 短信发送结果
     */
    SmsSendResp sendSms(String mobile, String apiTemplateId,
                        List<KeyValue<String, Object>> templateParams) throws Throwable;

    /**
     * 解析接收短信的接收结果
     *
     * @param text 结果
     * @return 结果内容
     * @throws Throwable 当解析 text 发生异常时，则会抛出异常
     */
    List<SmsReceiveResp> parseSmsReceiveStatus(String text) throws Throwable;

    /**
     * 查询指定的短信模板
     * <p>
     * 如果查询失败，则返回 null 空
     *
     * @param apiTemplateId 短信 API 的模板编号
     * @return 短信模板
     */
    SmsTemplateResp getSmsTemplate(String apiTemplateId) throws Throwable;

}
