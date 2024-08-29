/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.annotations.VisibleForTesting;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.MqConstants;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.eve.rest.mq.JobMateMation;
import com.skyeye.eve.service.IJobMateMationService;
import com.skyeye.exception.CustomException;
import com.skyeye.personnel.entity.SysEveUserStaff;
import com.skyeye.personnel.service.SysEveUserStaffService;
import com.skyeye.sms.entity.KeyValue;
import com.skyeye.sms.entity.SmsChannel;
import com.skyeye.sms.entity.SmsSendMessage;
import com.skyeye.sms.entity.SmsTemplate;
import com.skyeye.sms.service.SmsChannelService;
import com.skyeye.sms.service.SmsSendService;
import com.skyeye.sms.service.SmsTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: SmsSendServiceImpl
 * @Description: 短信发送 Service 发送的实现
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 20:55
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@Slf4j
public class SmsSendServiceImpl implements SmsSendService {

    @Autowired
    private IJobMateMationService iJobMateMationService;

    @Autowired
    private SysEveUserStaffService sysEveUserStaffService;

    @Autowired
    private SmsChannelService smsChannelService;

    @Autowired
    private SmsTemplateService smsTemplateService;

    @Override
    public void sendSingleSmsToAdmin(String mobile, String staffId, String templateCode, Map<String, Object> templateParams) {
        // 如果 mobile 为空，则加载用户编号对应的手机号
        if (StrUtil.isEmpty(mobile)) {
            SysEveUserStaff user = sysEveUserStaffService.selectById(staffId);
            if (user != null) {
                mobile = user.getPhone();
            }
        }
        // 执行发送
        sendSingleSms(mobile, staffId, templateCode, templateParams);
    }

    @Override
    public void sendSingleSmsToMember(String mobile, String staffId, String templateCode, Map<String, Object> templateParams) {

    }

    @Override
    public void sendSingleSms(String mobile, String staffId, String templateCode, Map<String, Object> templateParams) {
        // 校验短信模板是否合法
        SmsTemplate template = validateSmsTemplate(templateCode);
        // 校验短信渠道是否合法
        SmsChannel smsChannel = validateSmsChannel(template.getChannelId());

        // 校验手机号码是否存在
        mobile = validateMobile(mobile);
        // 构建有序的模板参数。为什么放在这个位置，是提前保证模板参数的正确性，而不是到了插入发送日志
        List<KeyValue<String, Object>> newTemplateParams = buildTemplateParams(template, templateParams);

        // 如果模板被禁用，则不发送短信
        Boolean isSend = EnableEnum.ENABLE_USING.getKey().equals(template.getEnabled())
            && EnableEnum.ENABLE_USING.getKey().equals(smsChannel.getEnabled());
        // 发送 MQ 消息，异步执行发送短信
        if (isSend) {
            SmsSendMessage smsSendMessage = new SmsSendMessage();
            smsSendMessage.setMobile(mobile);
            smsSendMessage.setChannelId(smsChannel.getId());
            smsSendMessage.setApiTemplateId(template.getApiTemplateId());
            smsSendMessage.setTemplateParams(newTemplateParams);

            Map<String, Object> emailNotice = new HashMap<>();
            emailNotice.put("content", JSONUtil.toJsonStr(smsSendMessage));
            emailNotice.put("type", MqConstants.JobMateMationJobType.SMS_SEND.getJobType());
            JobMateMation jobMateMation = new JobMateMation();
            jobMateMation.setJsonStr(JSONUtil.toJsonStr(emailNotice));
            Map<String, Object> user = InputObject.getLogParamsStatic();
            jobMateMation.setUserId(CollectionUtil.isEmpty(user) ? CommonConstants.ADMIN_USER_ID : user.get("id").toString());
            iJobMateMationService.sendMQProducer(jobMateMation);
        }
    }

    private SmsChannel validateSmsChannel(String channelId) {
        // 获得短信模板。考虑到效率，从缓存中获取
        SmsChannel channel = smsChannelService.selectById(channelId);
        // 短信模板不存在
        if (channel == null) {
            throw new CustomException("短信渠道不存在");
        }
        return channel;
    }

    private SmsTemplate validateSmsTemplate(String codeNum) {
        // 获得短信模板。考虑到效率，从缓存中获取
        SmsTemplate template = smsTemplateService.selectByCodeNum(codeNum);
        // 短信模板不存在
        if (template == null) {
            throw new CustomException("短信模板不存在");
        }
        return template;
    }

    /**
     * 将参数模板，处理成有序的 KeyValue 数组
     * <p>
     * 原因是，部分短信平台并不是使用 key 作为参数，而是数组下标，例如说 <a href="https://cloud.tencent.com/document/product/382/39023">腾讯云</a>
     *
     * @param template       短信模板
     * @param templateParams 原始参数
     * @return 处理后的参数
     */
    @VisibleForTesting
    List<KeyValue<String, Object>> buildTemplateParams(SmsTemplate template, Map<String, Object> templateParams) {
        return template.getParams().stream().map(key -> {
            Object value = templateParams.get(key);
            if (value == null) {
                throw new CustomException("模板参数缺少：" + key);
            }
            return new KeyValue<>(key, value);
        }).collect(Collectors.toList());
    }

    public String validateMobile(String mobile) {
        if (StrUtil.isEmpty(mobile)) {
            throw new CustomException("手机号码不能为空");
        }
        return mobile;
    }

    @Override
    public void doSendSms(SmsSendMessage message) {

    }

    @Override
    public void receiveSmsStatus(String channelCode, String text) throws Throwable {

    }
}
