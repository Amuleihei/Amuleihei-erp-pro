/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pay.core.service.impl.weixin;

import com.github.binarywang.wxpay.bean.order.WxPayMwebOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.skyeye.pay.core.dto.order.PayOrderRespDTO;
import com.skyeye.pay.core.dto.order.PayOrderUnifiedReqDTO;
import com.skyeye.pay.enums.PayOrderDisplayMode;
import com.skyeye.pay.enums.PayType;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: WxWapPayClient
 * @Description: 微信支付（H5 网页）的 PayClient 实现类
 * 文档：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_3_1.shtml">H5下单API</>
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/11 9:42
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Slf4j
public class WxWapPayClient extends AbstractWxPayClient {

    public WxWapPayClient(Long channelId, WxPayClientConfig config) {
        super(channelId, PayType.WX_WAP.getKey(), config);
    }

    protected WxWapPayClient(Long channelId, String channelCode, WxPayClientConfig config) {
        super(channelId, channelCode, config);
    }

    @Override
    protected void doInit() {
        super.doInit(WxPayConstants.TradeType.MWEB);
    }

    @Override
    protected PayOrderRespDTO doUnifiedOrderV2(PayOrderUnifiedReqDTO reqDTO) throws WxPayException {
        // 构建 WxPayUnifiedOrderRequest 对象
        WxPayUnifiedOrderRequest request = buildPayUnifiedOrderRequestV2(reqDTO);
        // 执行请求
        WxPayMwebOrderResult response = client.createOrder(request);

        // 转换结果
        return PayOrderRespDTO.waitingOf(PayOrderDisplayMode.URL.getKey(), response.getMwebUrl(),
            reqDTO.getOutTradeNo(), response);
    }

    @Override
    protected PayOrderRespDTO doUnifiedOrderV3(PayOrderUnifiedReqDTO reqDTO) throws WxPayException {
        // 构建 WxPayUnifiedOrderRequest 对象
        WxPayUnifiedOrderV3Request request = buildPayUnifiedOrderRequestV3(reqDTO);
        // 执行请求
        String response = client.createOrderV3(TradeTypeEnum.H5, request);

        // 转换结果
        return PayOrderRespDTO.waitingOf(PayOrderDisplayMode.URL.getKey(), response,
            reqDTO.getOutTradeNo(), response);
    }

}
