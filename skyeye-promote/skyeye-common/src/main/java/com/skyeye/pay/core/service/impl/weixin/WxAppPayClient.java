/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pay.core.service.impl.weixin;

import cn.hutool.json.JSONUtil;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.skyeye.pay.core.dto.order.PayOrderRespDTO;
import com.skyeye.pay.core.dto.order.PayOrderUnifiedReqDTO;
import com.skyeye.pay.enums.PayOrderDisplayMode;
import com.skyeye.pay.enums.PayType;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: WxAppPayClient
 * @Description: 微信支付【App 支付】的 PayClient 实现类
 * 档：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3/open/pay/chapter2_5_3.shtml">App 支付</a>
 * 未详细测试，因为手头没 App
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/11 9:35
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Slf4j
public class WxAppPayClient extends AbstractWxPayClient {

    public WxAppPayClient(Long channelId, WxPayClientConfig config) {
        super(channelId, PayType.WX_APP.getKey(), config);
    }

    @Override
    protected void doInit() {
        super.doInit(WxPayConstants.TradeType.APP);
    }

    @Override
    protected PayOrderRespDTO doUnifiedOrderV2(PayOrderUnifiedReqDTO reqDTO) throws WxPayException {
        // 构建 WxPayUnifiedOrderRequest 对象
        WxPayUnifiedOrderRequest request = buildPayUnifiedOrderRequestV2(reqDTO);
        // 执行请求
        WxPayMpOrderResult response = client.createOrder(request);

        // 转换结果
        return PayOrderRespDTO.waitingOf(PayOrderDisplayMode.APP.getKey(), JSONUtil.toJsonStr(response),
            reqDTO.getOutTradeNo(), response);
    }

    @Override
    protected PayOrderRespDTO doUnifiedOrderV3(PayOrderUnifiedReqDTO reqDTO) throws WxPayException {
        // 构建 WxPayUnifiedOrderV3Request 对象
        WxPayUnifiedOrderV3Request request = buildPayUnifiedOrderRequestV3(reqDTO);
        // 执行请求
        WxPayUnifiedOrderV3Result.AppResult response = client.createOrderV3(TradeTypeEnum.APP, request);

        // 转换结果
        return PayOrderRespDTO.waitingOf(PayOrderDisplayMode.APP.getKey(), JSONUtil.toJsonStr(response),
            reqDTO.getOutTradeNo(), response);
    }

}
