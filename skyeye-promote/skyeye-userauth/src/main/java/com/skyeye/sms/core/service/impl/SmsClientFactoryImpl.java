/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.core.service.impl;

import com.skyeye.sms.classenum.SmsChannelEnum;
import com.skyeye.sms.core.service.SmsClient;
import com.skyeye.sms.core.service.SmsClientFactory;
import com.skyeye.sms.entity.SmsChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @ClassName: SmsClientFactoryImpl
 * @Description: 短信客户端工厂接口
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 22:21
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Validated
@Slf4j
public class SmsClientFactoryImpl implements SmsClientFactory {

    /**
     * 短信客户端 Map
     * key：渠道编号，使用 {@link SmsChannel#getId()}
     */
    private final ConcurrentMap<String, AbstractSmsClient> channelIdClients = new ConcurrentHashMap<>();

    /**
     * 短信客户端 Map
     * key：渠道编码，使用 {@link SmsChannel#getCodeNum()} ()} ()}
     * <p>
     * 注意，一些场景下，需要获得某个渠道类型的客户端，所以需要使用它。
     * 例如说，解析短信接收结果，是相对通用的，不需要使用某个渠道编号的 {@link #channelIdClients}
     */
    private final ConcurrentMap<String, AbstractSmsClient> channelCodeClients = new ConcurrentHashMap<>();

    public SmsClientFactoryImpl() {
        // 初始化 channelCodeClients 集合
        Arrays.stream(SmsChannelEnum.values()).forEach(channel -> {
            // 创建一个空的 SmsChannelProperties 对象
            SmsChannel properties = new SmsChannel().setCodeNum(channel.getKey())
                .setApiKey("default default").setApiSecret("default");
            // 创建 Sms 客户端
            AbstractSmsClient smsClient = createSmsClient(properties);
            channelCodeClients.put(channel.getKey(), smsClient);
        });
    }

    @Override
    public SmsClient getSmsClientById(String channelId) {
        return channelIdClients.get(channelId);
    }

    @Override
    public void removeSmsClientById(String channelId) {
        channelIdClients.remove(channelId);
    }

    @Override
    public SmsClient getSmsClient(String channelCode) {
        return channelCodeClients.get(channelCode);
    }

    @Override
    public void createOrUpdateSmsClient(SmsChannel properties) {
        AbstractSmsClient client = channelIdClients.get(properties.getId());
        if (client == null) {
            client = this.createSmsClient(properties);
            client.init();
            channelIdClients.put(client.getId(), client);
        } else {
            client.refresh(properties);
        }
    }

    private AbstractSmsClient createSmsClient(SmsChannel properties) {
        SmsChannelEnum channelEnum = SmsChannelEnum.getByCode(properties.getCodeNum());
        Assert.notNull(channelEnum, String.format("渠道类型(%s) 为空", channelEnum));
        // 创建客户端
        switch (channelEnum) {
            case ALIYUN:
                return new AliyunSmsClient(properties);
            case DEBUG_DING_TALK:
                return new DebugDingTalkSmsClient(properties);
            case TENCENT:
                return new TencentSmsClient(properties);
            case HUAWEI:
                return new HuaweiSmsClient(properties);
        }
        // 创建失败，错误日志 + 抛出异常
        log.error("[createSmsClient][配置({}) 找不到合适的客户端实现]", properties);
        throw new IllegalArgumentException(String.format("配置(%s) 找不到合适的客户端实现", properties));
    }

}
