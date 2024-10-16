/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.sms.classenum.SmsTemplateAuditStatusEnum;
import com.skyeye.sms.core.entity.SmsTemplateResp;
import com.skyeye.sms.core.service.SmsClient;
import com.skyeye.sms.dao.SmsTemplateDao;
import com.skyeye.sms.entity.SmsTemplate;
import com.skyeye.sms.service.SmsChannelService;
import com.skyeye.sms.service.SmsTemplateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @ClassName: SmsTemplateServiceImpl
 * @Description: 短信模板服务实现类
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 22:30
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "短信模板", groupName = "短信模板")
public class SmsTemplateServiceImpl extends SkyeyeBusinessServiceImpl<SmsTemplateDao, SmsTemplate> implements SmsTemplateService {

    /**
     * 正则表达式，匹配 {} 中的变量
     */
    private static final Pattern PATTERN_PARAMS = Pattern.compile("\\{(.*?)}");

    @Autowired
    private SmsChannelService smsChannelService;

    @Override
    public void validatorEntity(SmsTemplate entity) {
        // 校验短信编码是否重复
        QueryWrapper<SmsTemplate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(SmsTemplate::getCodeNum), entity.getCodeNum());
        if (StringUtils.isNotEmpty(entity.getId())) {
            queryWrapper.ne(CommonConstants.ID, entity.getId());
        }
        SmsTemplate checkSmsTemplate = getOne(queryWrapper, false);
        if (ObjectUtil.isNotEmpty(checkSmsTemplate)) {
            throw new CustomException("模板编码已存在.");
        }
        // 校验短信模板
        validateApiTemplate(entity.getChannelId(), entity.getApiTemplateId());

        entity.setParams(parseTemplateContentParams(entity.getContent()));
    }

    @Override
    public SmsTemplate selectById(String id) {
        SmsTemplate smsTemplate = super.selectById(id);
        smsChannelService.setDataMation(smsTemplate, SmsTemplate::getChannelId);
        return smsTemplate;
    }

    /**
     * 校验 API 短信平台的模板是否有效
     *
     * @param channelId     渠道编号
     * @param apiTemplateId API 模板编号
     */
    private void validateApiTemplate(String channelId, String apiTemplateId) {
        // 获得短信模板
        SmsClient smsClient = smsChannelService.getSmsClientById(channelId);
        Assert.notNull(smsClient, String.format("短信客户端(%s) 不存在", channelId));
        SmsTemplateResp template;
        try {
            template = smsClient.getSmsTemplate(apiTemplateId);
        } catch (Throwable ex) {
            throw new CustomException("短信 API 模板调用失败");
        }
        // 校验短信模版
        if (template == null) {
            throw new CustomException("短信 API 模版无法使用，原因：模版不存在");
        }
        if (Objects.equals(template.getAuditStatus(), SmsTemplateAuditStatusEnum.CHECKING.getKey())) {
            throw new CustomException("短信 API 模版无法使用，原因：审批中");
        }
        if (Objects.equals(template.getAuditStatus(), SmsTemplateAuditStatusEnum.FAIL.getKey())) {
            throw new CustomException("短信 API 模版无法使用，原因：审批不通过");
        }
        Assert.equals(template.getAuditStatus(), SmsTemplateAuditStatusEnum.SUCCESS.getKey(),
            String.format("短信模板(%s) 审核状态(%d) 不正确", apiTemplateId, template.getAuditStatus()));
    }

    @Override
    public SmsTemplate selectByCodeNum(String codeNum) {
        QueryWrapper<SmsTemplate> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(SmsTemplate::getCodeNum), codeNum);
        SmsTemplate smsTemplate = getOne(wrapper, false);
        return smsTemplate;
    }

    @Override
    public String formatSmsTemplateContent(String content, Map<String, Object> params) {
        return StrUtil.format(content, params);
    }

    private List<String> parseTemplateContentParams(String content) {
        return ReUtil.findAllGroup1(PATTERN_PARAMS, content);
    }

}
