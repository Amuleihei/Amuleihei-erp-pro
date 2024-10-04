/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pay.core.service.impl.alipay;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.skyeye.exception.CustomException;
import com.skyeye.pay.core.dto.order.PayOrderRespDTO;
import com.skyeye.pay.core.dto.order.PayOrderUnifiedReqDTO;
import com.skyeye.pay.enums.PayOrderDisplayMode;
import com.skyeye.pay.enums.PayType;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @ClassName: AlipayBarPayClient
 * @Description: 支付宝【条码支付】的 PayClient 实现类
 * 文档：<a href="https://opendocs.alipay.com/open/194/105072">当面付</a>
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/11 9:10
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Slf4j
public class AlipayBarPayClient extends AbstractAlipayPayClient {

    public AlipayBarPayClient(String channelId, AlipayPayClientConfig config) {
        super(channelId, PayType.ALIPAY_BAR.getKey(), config);
    }

    @Override
    public PayOrderRespDTO doUnifiedOrder(PayOrderUnifiedReqDTO reqDTO) throws AlipayApiException {
        String authCode = MapUtil.getStr(reqDTO.getChannelExtras(), "auth_code");
        if (StrUtil.isEmpty(authCode)) {
            throw new CustomException("条形码不能为空");
        }

        // 1.1 构建 AlipayTradePayModel 请求
        AlipayTradePayModel model = new AlipayTradePayModel();
        // ① 通用的参数
        model.setOutTradeNo(reqDTO.getOutTradeNo());
        model.setSubject(reqDTO.getSubject());
        model.setBody(reqDTO.getBody());
        model.setTotalAmount(formatAmount(reqDTO.getPrice()));
        model.setScene("bar_code"); // 当面付条码支付场景
        // ② 个性化的参数
        model.setAuthCode(authCode);
        // ③ 支付宝条码支付只有一种展示
        String displayMode = PayOrderDisplayMode.BAR_CODE.getKey();

        // 1.2 构建 AlipayTradePayRequest 请求
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(reqDTO.getNotifyUrl());
        request.setReturnUrl(reqDTO.getReturnUrl());

        // 2.1 执行请求
        AlipayTradePayResponse response;
        if (Objects.equals(config.getMode(), AlipayPayClientConfig.MODE_CERTIFICATE)) {
            // 证书模式
            response = client.certificateExecute(request);
        } else {
            response = client.execute(request);
        }
        // 2.2 处理结果
        if (!response.isSuccess()) {
            return buildClosedPayOrderRespDTO(reqDTO, response);
        }
        if ("10000".equals(response.getCode())) { // 免密支付
            LocalDateTime successTime = LocalDateTimeUtil.of(response.getGmtPayment());
            return PayOrderRespDTO.successOf(response.getTradeNo(), response.getBuyerUserId(), successTime,
                    response.getOutTradeNo(), response)
                .setDisplayMode(displayMode).setDisplayContent("");
        }
        // 大额支付，需要用户输入密码，所以返回 waiting。此时，前端一般会进行轮询
        return PayOrderRespDTO.waitingOf(displayMode, "",
            reqDTO.getOutTradeNo(), response);
    }
}
