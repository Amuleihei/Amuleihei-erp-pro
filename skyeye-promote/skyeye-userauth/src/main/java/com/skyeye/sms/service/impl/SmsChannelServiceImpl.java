/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.sms.core.service.SmsClient;
import com.skyeye.sms.core.service.SmsClientFactory;
import com.skyeye.sms.dao.SmsChannelDao;
import com.skyeye.sms.entity.SmsChannel;
import com.skyeye.sms.service.SmsChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public SmsClient getSmsClientById(String channelId) {
        SmsChannel smsChannel = selectById(channelId);
        if (smsChannel != null) {
            smsClientFactory.createOrUpdateSmsClient(smsChannel);
        }
        return smsClientFactory.getSmsClientById(channelId);
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

}
