/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pay.core.service.impl.alipay;

import cn.hutool.http.Method;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.skyeye.pay.core.dto.order.PayOrderRespDTO;
import com.skyeye.pay.core.dto.order.PayOrderUnifiedReqDTO;
import com.skyeye.pay.enums.PayOrderDisplayMode;
import com.skyeye.pay.enums.PayType;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: AlipayWapPayClient
 * @Description: 支付宝【Wap 网站】的 PayClient 实现类
 * 文档：<a href="https://opendocs.alipay.com/apis/api_1/alipay.trade.wap.pay">手机网站支付接口</a>
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/11 9:34
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Slf4j
public class AlipayWapPayClient extends AbstractAlipayPayClient {

    public AlipayWapPayClient(Long channelId, AlipayPayClientConfig config) {
        super(channelId, PayType.ALIPAY_WAP.getKey(), config);
    }

    @Override
    public PayOrderRespDTO doUnifiedOrder(PayOrderUnifiedReqDTO reqDTO) throws AlipayApiException {
        // 1.1 构建 AlipayTradeWapPayModel 请求
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        // ① 通用的参数
        model.setOutTradeNo(reqDTO.getOutTradeNo());
        model.setSubject(reqDTO.getSubject());
        model.setBody(reqDTO.getBody());
        model.setTotalAmount(formatAmount(reqDTO.getPrice()));
        model.setProductCode("QUICK_WAP_PAY"); // 销售产品码. 目前 Wap 支付场景下仅支持 QUICK_WAP_PAY
        // ② 个性化的参数【无】
        // ③ 支付宝 Wap 支付只有一种展示：URL
        String displayMode = PayOrderDisplayMode.URL.getKey();

        // 1.2 构建 AlipayTradeWapPayRequest 请求
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(reqDTO.getNotifyUrl());
        request.setReturnUrl(reqDTO.getReturnUrl());
        model.setQuitUrl(reqDTO.getReturnUrl());
        model.setTimeExpire(formatTime(reqDTO.getExpireTime()));

        // 2.1 执行请求
        AlipayTradeWapPayResponse response = client.pageExecute(request, Method.GET.name());
        // 2.2 处理结果
        if (!response.isSuccess()) {
            return buildClosedPayOrderRespDTO(reqDTO, response);
        }
        return PayOrderRespDTO.waitingOf(displayMode, response.getBody(),
            reqDTO.getOutTradeNo(), response);
    }
}
