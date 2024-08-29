/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.service;

import com.skyeye.sms.entity.SmsSendMessage;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SmsSendService
 * @Description: 短信发送 Service 接口
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 20:53
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SmsSendService {

    /**
     * 发送单条短信给管理后台的用户
     * <p>
     * 在 mobile 为空时，使用 userId 加载对应管理员的手机号
     *
     * @param mobile         手机号
     * @param staffId        员工id
     * @param templateCode   短信模板编号
     * @param templateParams 短信模板参数
     * @return 发送日志编号
     */
    void sendSingleSmsToAdmin(String mobile, String staffId,
                              String templateCode, Map<String, Object> templateParams);

    /**
     * 发送单条短信给用户 APP 的用户
     * <p>
     * 在 mobile 为空时，使用 userId 加载对应会员的手机号
     *
     * @param mobile         手机号
     * @param staffId        员工id
     * @param templateCode   短信模板编号
     * @param templateParams 短信模板参数
     * @return 发送日志编号
     */
    void sendSingleSmsToMember(String mobile, String staffId,
                               String templateCode, Map<String, Object> templateParams);

    /**
     * 发送单条短信给用户
     *
     * @param mobile         手机号
     * @param staffId        员工id
     * @param templateCode   短信模板编号
     * @param templateParams 短信模板参数
     * @return 发送日志编号
     */
    void sendSingleSms(String mobile, String staffId,
                       String templateCode, Map<String, Object> templateParams);

    default void sendBatchSms(List<String> mobiles, List<String> staffIds,
                              String templateCode, Map<String, Object> templateParams) {
        throw new UnsupportedOperationException("暂时不支持该操作，感兴趣可以实现该功能哟！");
    }

    /**
     * 执行真正的短信发送
     * 注意，该方法仅仅提供给 MQ Consumer 使用
     *
     * @param message 短信
     */
    void doSendSms(SmsSendMessage message);

    /**
     * 接收短信的接收结果
     *
     * @param channelCode 渠道编码
     * @param text        结果内容
     * @throws Throwable 处理失败时，抛出异常
     */
    void receiveSmsStatus(String channelCode, String text) throws Throwable;

}
