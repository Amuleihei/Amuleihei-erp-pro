/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pay.core.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import com.skyeye.pay.core.PayClient;
import com.skyeye.pay.core.PayClientConfig;
import com.skyeye.pay.core.PayClientFactory;
import com.skyeye.pay.core.service.impl.alipay.*;
import com.skyeye.pay.core.service.impl.mock.MockPayClient;
import com.skyeye.pay.core.service.impl.weixin.*;
import com.skyeye.pay.enums.PayType;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @ClassName: PayClientFactoryImpl
 * @Description: 支付客户端的工厂实现类
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/10 9:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Slf4j
public class PayClientFactoryImpl implements PayClientFactory {

    /**
     * 支付客户端 Map
     * <p>
     * key：渠道编号
     */
    private final ConcurrentMap<String, AbstractPayClient<?>> clients = new ConcurrentHashMap<>();

    /**
     * 支付客户端 Class Map
     */
    private final Map<PayType, Class<?>> clientClass = new ConcurrentHashMap<>();

    public PayClientFactoryImpl() {
        // 微信支付客户端
        clientClass.put(PayType.WX_PUB, WxPubPayClient.class);
        clientClass.put(PayType.WX_LITE, WxLitePayClient.class);
        clientClass.put(PayType.WX_APP, WxAppPayClient.class);
        clientClass.put(PayType.WX_BAR, WxBarPayClient.class);
        clientClass.put(PayType.WX_NATIVE, WxNativePayClient.class);
        clientClass.put(PayType.WX_WAP, WxWapPayClient.class);
        // 支付包支付客户端
        clientClass.put(PayType.ALIPAY_WAP, AlipayWapPayClient.class);
        clientClass.put(PayType.ALIPAY_QR, AlipayQrPayClient.class);
        clientClass.put(PayType.ALIPAY_APP, AlipayAppPayClient.class);
        clientClass.put(PayType.ALIPAY_PC, AlipayPcPayClient.class);
        clientClass.put(PayType.ALIPAY_BAR, AlipayBarPayClient.class);
        // Mock 支付客户端
        clientClass.put(PayType.MOCK, MockPayClient.class);
    }

    @Override
    public void registerPayClientClass(PayType channel, Class<?> payClientClass) {
        clientClass.put(channel, payClientClass);
    }

    @Override
    public PayClient getPayClient(Long channelId) {
        AbstractPayClient<?> client = clients.get(channelId);
        if (client == null) {
            log.error("[getPayClient][渠道编号({}) 找不到客户端]", channelId);
        }
        return client;
    }

    @Override
    public <Config extends PayClientConfig> PayClient createOrUpdatePayClient(Long channelId, String channelCode,
                                                                              Config config) {
        AbstractPayClient<Config> client = (AbstractPayClient<Config>) clients.get(channelId);
        if (client == null) {
            client = this.createPayClient(channelId, channelCode, config);
            client.init();
            clients.put(client.getId(), client);
        } else {
            client.refresh(config);
        }
        return client;
    }

    private <Config extends PayClientConfig> AbstractPayClient<Config> createPayClient(Long channelId, String channelCode,
                                                                                       Config config) {
        PayType channelEnum = PayType.getByCode(channelCode);
        Assert.notNull(channelEnum, String.format("支付渠道(%s) 为空", channelCode));
        Class<?> payClientClass = clientClass.get(channelEnum);
        Assert.notNull(payClientClass, String.format("支付渠道(%s) Class 为空", channelCode));
        return (AbstractPayClient<Config>) ReflectUtil.newInstance(payClientClass, channelId, config);
    }

}
