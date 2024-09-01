/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.mq.job.impl;

import cn.hutool.json.JSONUtil;
import com.skyeye.common.constans.MqConstants;
import com.skyeye.common.util.MailUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.email.dao.EmailDao;
import com.skyeye.eve.email.entity.Email;
import com.skyeye.eve.email.service.EmailService;
import com.skyeye.eve.service.ISystemFoundationSettingsService;
import com.skyeye.util.MqSendUtil;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 卫志强
 * @ClassName: MailDraftsEditServiceImpl
 * @Description: 草稿编辑同步
 * @date 2020年8月22日
 */
@Component
@RocketMQMessageListener(
    topic = "${topic.mail-drafts-edit-service}",
    consumerGroup = "${topic.mail-drafts-edit-service}",
    selectorExpression = "${spring.profiles.active}")
public class MailDraftsEditServiceImpl implements RocketMQListener<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailDraftsEditServiceImpl.class);

    @Value("${IMAGES_PATH}")
    private String tPath;

    @Autowired
    private EmailDao emailDao;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ISystemFoundationSettingsService iSystemFoundationSettingsService;

    @Override
    public void onMessage(String data) {
        Map<String, Object> map = JSONUtil.toBean(data, null);
        String jobId = map.get("jobMateId").toString();
        try {
            // 任务开始
            MqSendUtil.comMQJobMation(jobId, MqConstants.JOB_TYPE_IS_PROCESSING, "");
            //获取服务器信息
            Map<String, Object> emailServer = iSystemFoundationSettingsService.querySystemFoundationSettingsList();
            String title = map.get("title").toString();//标题
            String content = map.get("content").toString();//邮件内容
            String toPeople = map.get("toPeople").toString();//收件人
            String toCc = map.get("toCc").toString();//抄送人
            String toBCc = map.get("toBcc").toString();//暗送人
            String username = map.get("userAddress").toString();//登录邮箱账号
            String password = map.get("userPassword").toString();//密码
            String emailEnclosure = map.get("emailEnclosure").toString();
            String emailId = map.get("emailId").toString();
            List<Map<String, Object>> emailEnclosureList = JSONUtil.toList(emailEnclosure, null);
            List<Map<String, Object>> beans = new ArrayList<>();
            Map<String, Object> bean = null;
            for (int i = 0; i < emailEnclosureList.size(); i++) {
                Map<String, Object> j = emailEnclosureList.get(i);
                bean = new HashMap<>();
                bean.put("fileName", j.get("fileName"));
                bean.put("filePath", j.get("filePath"));
                beans.add(bean);
            }
            //获取邮件的messageid
            Email message = emailService.selectById(emailId);
            //删除之前的草稿件
            new MailUtil(username, password, emailServer.get("emailReceiptServer").toString())
                .deleteEmail(username, title, message.getMessageId());
            //保存邮件为草稿
            String messageId = new MailUtil(username, password, emailServer.get("emailReceiptServer").toString())
                .saveDraftsEmail(toPeople, toCc, toBCc, title, content, tPath.replace("images", ""), beans);
            if (!ToolUtil.isBlank(messageId)) {
                Map<String, Object> emailEditMessageId = new HashMap<>();
                emailEditMessageId.put("id", emailId);
                emailEditMessageId.put("messageId", messageId);
                emailDao.editEmailMessageIdByEmailId(emailEditMessageId);
            }
            // 任务完成
            MqSendUtil.comMQJobMation(jobId, MqConstants.JOB_TYPE_IS_SUCCESS, "");
        } catch (Exception e) {
            LOGGER.warn("Draft edit synchronization failed, reason is {}.", e);
            // 任务失败
            MqSendUtil.comMQJobMation(jobId, MqConstants.JOB_TYPE_IS_FAIL, "");
        }
    }

}
