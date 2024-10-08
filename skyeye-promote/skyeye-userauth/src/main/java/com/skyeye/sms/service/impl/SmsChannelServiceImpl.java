/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.sms.classenum.SmsChannelEnum;
import com.skyeye.sms.core.service.SmsClient;
import com.skyeye.sms.core.service.SmsClientFactory;
import com.skyeye.sms.dao.SmsChannelDao;
import com.skyeye.sms.entity.SmsChannel;
import com.skyeye.sms.service.SmsChannelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: SmsChannelServiceImpl
 * @Description: 短信渠道服务实现类
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 22:21
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "短信渠道", groupName = "短信渠道")
public class SmsChannelServiceImpl extends SkyeyeBusinessServiceImpl<SmsChannelDao, SmsChannel> implements SmsChannelService {

    @Autowired
    private SmsClientFactory smsClientFactory;

    @Override
    public void validatorEntity(SmsChannel entity) {
        // 校验渠道编码是否重复
        QueryWrapper<SmsChannel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(SmsChannel::getCodeNum), entity.getCodeNum());
        if (StringUtils.isNotEmpty(entity.getId())) {
            queryWrapper.ne(CommonConstants.ID, entity.getId());
        }
        SmsChannel checkSmsChannel = getOne(queryWrapper, false);
        if (ObjectUtil.isNotEmpty(checkSmsChannel)) {
            throw new CustomException("渠道编码已存在.");
        }
    }

    @Override
    public SmsClient getSmsClientById(String channelId) {
        SmsChannel smsChannel = selectById(channelId);
        if (smsChannel != null) {
            smsClientFactory.createOrUpdateSmsClient(smsChannel);
        }
        return smsClientFactory.getSmsClientById(channelId);
    }

    @Override
    public SmsChannel selectById(String id) {
        SmsChannel smsChannel = super.selectById(id);
        smsChannel.setCodeNumMation(SmsChannelEnum.getMation(smsChannel.getCodeNum()));
        return smsChannel;
    }

    @Override
    public SmsClient getSmsClient(String channelCode) {
        SmsChannel smsChannel = selectByCodeNum(channelCode);
        if (smsChannel != null) {
            smsClientFactory.createOrUpdateSmsClient(smsChannel);
        }
        return smsClientFactory.getSmsClient(channelCode);
    }

    @Override
    public SmsChannel selectByCodeNum(String codeNum) {
        QueryWrapper<SmsChannel> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(SmsChannel::getCodeNum), codeNum);
        SmsChannel smsChannel = getOne(wrapper, false);
        return smsChannel;
    }

    @Override
    public void queryEnabledSmsChannelList(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<SmsChannel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(SmsChannel::getEnabled), EnableEnum.ENABLE_USING.getKey());
        List<SmsChannel> smsChannelList = list(queryWrapper);
        outputObject.setBeans(smsChannelList);
        outputObject.settotal(smsChannelList.size());
    }

}
