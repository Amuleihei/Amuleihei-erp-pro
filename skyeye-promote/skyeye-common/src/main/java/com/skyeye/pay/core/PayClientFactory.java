/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pay.core;


import com.skyeye.pay.enums.PayType;

/**
 * @ClassName: PayClientFactory
 * @Description: 支付客户端的工厂接口
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/10 8:23
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface PayClientFactory {

    /**
     * 获得支付客户端
     *
     * @param channelId 渠道编号
     * @return 支付客户端
     */
    PayClient getPayClient(String channelId);

    /**
     * 创建支付客户端
     *
     * @param channelId   渠道编号
     * @param channelCode 渠道编码
     * @param config      支付配置
     * @return 支付客户端
     */
    <Config extends PayClientConfig> PayClient createOrUpdatePayClient(String channelId, String channelCode,
                                                                       Config config);

    /**
     * 注册支付客户端 Class，用于模块中实现的 PayClient
     *
     * @param channel        支付渠道的编码的枚举
     * @param payClientClass 支付客户端 class
     */
    void registerPayClientClass(PayType channel, Class<?> payClientClass);

}
