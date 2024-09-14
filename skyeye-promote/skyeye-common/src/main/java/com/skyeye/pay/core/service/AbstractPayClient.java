/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pay.core.service;

import cn.hutool.json.JSONUtil;
import com.skyeye.pay.core.PayClient;
import com.skyeye.pay.core.PayClientConfig;
import com.skyeye.pay.core.dto.order.PayOrderRespDTO;
import com.skyeye.pay.core.dto.order.PayOrderUnifiedReqDTO;
import com.skyeye.pay.core.dto.refund.PayRefundRespDTO;
import com.skyeye.pay.core.dto.refund.PayRefundUnifiedReqDTO;
import com.skyeye.pay.core.dto.transfer.PayTransferRespDTO;
import com.skyeye.pay.core.dto.transfer.PayTransferUnifiedReqDTO;
import com.skyeye.pay.enums.PayTransferType;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @ClassName: AbstractPayClient
 * @Description: 支付客户端的抽象类，提供模板方法，减少子类的冗余代码
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/10 8:25
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Slf4j
public abstract class AbstractPayClient<Config extends PayClientConfig> implements PayClient {

    /**
     * 渠道编号
     */
    private final Long channelId;
    /**
     * 渠道编码
     */
    @SuppressWarnings("FieldCanBeLocal")
    private final String channelCode;
    /**
     * 支付配置
     */
    protected Config config;

    public AbstractPayClient(Long channelId, String channelCode, Config config) {
        this.channelId = channelId;
        this.channelCode = channelCode;
        this.config = config;
    }

    /**
     * 初始化
     */
    public final void init() {
        doInit();
        log.debug("[init][客户端({}) 初始化完成]", getId());
    }

    /**
     * 自定义初始化
     */
    protected abstract void doInit();

    public final void refresh(Config config) {
        // 判断是否更新
        if (config.equals(this.config)) {
            return;
        }
        log.info("[refresh][客户端({})发生变化，重新初始化]", getId());
        this.config = config;
        // 初始化
        this.init();
    }

    @Override
    public Long getId() {
        return channelId;
    }

    // ============ 支付相关 ==========

    @Override
    public final PayOrderRespDTO unifiedOrder(PayOrderUnifiedReqDTO reqDTO) {
        // 执行统一下单
        PayOrderRespDTO resp = null;
        try {
            resp = doUnifiedOrder(reqDTO);
        } catch (Throwable ex) {
            // 系统异常，则包装成 PayException 异常抛出
            log.error("[unifiedOrder][客户端({}) request({}) 发起支付异常]",
                getId(), JSONUtil.toJsonStr(reqDTO), ex);
        }
        return resp;
    }

    protected abstract PayOrderRespDTO doUnifiedOrder(PayOrderUnifiedReqDTO reqDTO)
        throws Throwable;

    @Override
    public final PayOrderRespDTO parseOrderNotify(Map<String, String> params, String body) {
        try {
            return doParseOrderNotify(params, body);
        } catch (Throwable ex) {
            log.error("[parseOrderNotify][客户端({}) params({}) body({}) 解析失败]",
                getId(), params, body, ex);
        }
        return null;
    }

    protected abstract PayOrderRespDTO doParseOrderNotify(Map<String, String> params, String body)
        throws Throwable;

    @Override
    public final PayOrderRespDTO getOrder(String outTradeNo) {
        try {
            return doGetOrder(outTradeNo);
        } catch (Throwable ex) {
            log.error("[getOrder][客户端({}) outTradeNo({}) 查询支付单异常]",
                getId(), outTradeNo, ex);
        }
        return null;
    }

    protected abstract PayOrderRespDTO doGetOrder(String outTradeNo)
        throws Throwable;

    // ============ 退款相关 ==========

    @Override
    public final PayRefundRespDTO unifiedRefund(PayRefundUnifiedReqDTO reqDTO) {
        // 执行统一退款
        PayRefundRespDTO resp = null;
        try {
            resp = doUnifiedRefund(reqDTO);
        } catch (Throwable ex) {
            // 系统异常，则包装成 PayException 异常抛出
            log.error("[unifiedRefund][客户端({}) request({}) 发起退款异常]",
                getId(), JSONUtil.toJsonStr(reqDTO), ex);
        }
        return resp;
    }

    protected abstract PayRefundRespDTO doUnifiedRefund(PayRefundUnifiedReqDTO reqDTO) throws Throwable;

    @Override
    public final PayRefundRespDTO parseRefundNotify(Map<String, String> params, String body) {
        try {
            return doParseRefundNotify(params, body);
        } catch (Throwable ex) {
            log.error("[parseRefundNotify][客户端({}) params({}) body({}) 解析失败]",
                getId(), params, body, ex);
        }
        return null;
    }

    protected abstract PayRefundRespDTO doParseRefundNotify(Map<String, String> params, String body)
        throws Throwable;

    @Override
    public final PayRefundRespDTO getRefund(String outTradeNo, String outRefundNo) {
        try {
            return doGetRefund(outTradeNo, outRefundNo);
        } catch (Throwable ex) {
            log.error("[getRefund][客户端({}) outTradeNo({}) outRefundNo({}) 查询退款单异常]",
                getId(), outTradeNo, outRefundNo, ex);
        }
        return null;
    }

    protected abstract PayRefundRespDTO doGetRefund(String outTradeNo, String outRefundNo)
        throws Throwable;

    @Override
    public final PayTransferRespDTO unifiedTransfer(PayTransferUnifiedReqDTO reqDTO) {
        PayTransferRespDTO resp = null;
        try {
            resp = doUnifiedTransfer(reqDTO);
        } catch (Throwable ex) {
            // 系统异常，则包装成 PayException 异常抛出
            log.error("[unifiedTransfer][客户端({}) request({}) 发起转账异常]",
                getId(), JSONUtil.toJsonStr(reqDTO), ex);
        }
        return resp;
    }

    @Override
    public final PayTransferRespDTO getTransfer(String outTradeNo, PayTransferType type) {
        try {
            return doGetTransfer(outTradeNo, type);
        } catch (Throwable ex) {
            log.error("[getTransfer][客户端({}) outTradeNo({}) type({}) 查询转账单异常]",
                getId(), outTradeNo, type, ex);
        }
        return null;
    }

    protected abstract PayTransferRespDTO doUnifiedTransfer(PayTransferUnifiedReqDTO reqDTO)
        throws Throwable;

    protected abstract PayTransferRespDTO doGetTransfer(String outTradeNo, PayTransferType type)
        throws Throwable;

    // ========== 各种工具方法 ==========

}
