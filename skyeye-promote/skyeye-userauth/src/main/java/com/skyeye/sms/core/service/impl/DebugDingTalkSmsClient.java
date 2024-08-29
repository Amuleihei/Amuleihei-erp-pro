/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.core.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.HmacAlgorithm;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.skyeye.common.entity.KeyValue;
import com.skyeye.sms.classenum.SmsTemplateAuditStatusEnum;
import com.skyeye.sms.core.entity.SmsReceiveResp;
import com.skyeye.sms.core.entity.SmsSendResp;
import com.skyeye.sms.core.entity.SmsTemplateResp;
import com.skyeye.sms.entity.SmsChannel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName: DebugDingTalkSmsClient
 * @Description: 基于钉钉 WebHook 实现的调试的短信客户端实现类
 * 考虑到省钱，我们使用钉钉 WebHook 模拟发送短信，方便调试。
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 23:24
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class DebugDingTalkSmsClient extends AbstractSmsClient {

    public DebugDingTalkSmsClient(SmsChannel properties) {
        super(properties);
        Assert.notEmpty(properties.getApiKey(), "apiKey 不能为空");
        Assert.notEmpty(properties.getApiSecret(), "apiSecret 不能为空");
    }

    @Override
    protected void doInit() {
    }

    @Override
    public SmsSendResp sendSms(String mobile,
                               String apiTemplateId, List<KeyValue<String, Object>> templateParams) throws Throwable {
        // 构建请求
        String url = buildUrl("robot/send");
        Map<String, Object> params = new HashMap<>();
        params.put("msgtype", "text");
        String content = String.format("【模拟短信】\n手机号：%s\n模板参数：%s",
            mobile, com.skyeye.common.util.MapUtil.convertMap(templateParams));
        params.put("text", MapUtil.builder().put("content", content).build());
        // 执行请求
        String responseText = HttpUtil.post(url, JSONUtil.toJsonStr(params));
        // 解析结果
        Map<?, ?> responseObj = JSONUtil.toBean(responseText, Map.class);
        String errorCode = MapUtil.getStr(responseObj, "errcode");
        return new SmsSendResp().setSuccess(Objects.equals(errorCode, "0")).setSerialNo(StrUtil.uuid())
            .setApiCode(errorCode).setApiMsg(MapUtil.getStr(responseObj, "errorMsg"));
    }

    /**
     * 构建请求地址
     * <p>
     * 参见 <a href="https://developers.dingtalk.com/document/app/custom-robot-access/title-nfv-794-g71">文档</a>
     *
     * @param path 请求路径
     * @return 请求地址
     */
    @SuppressWarnings("SameParameterValue")
    private String buildUrl(String path) {
        // 生成 timestamp
        long timestamp = System.currentTimeMillis();
        // 生成 sign
        String secret = properties.getApiSecret();
        String stringToSign = timestamp + "\n" + secret;
        byte[] signData = DigestUtil.hmac(HmacAlgorithm.HmacSHA256, StrUtil.bytes(secret)).digest(stringToSign);
        String sign = Base64.encode(signData);
        // 构建最终 URL
        return String.format("https://oapi.dingtalk.com/%s?access_token=%s&timestamp=%d&sign=%s",
            path, properties.getApiKey(), timestamp, sign);
    }

    @Override
    public List<SmsReceiveResp> parseSmsReceiveStatus(String text) {
        throw new UnsupportedOperationException("模拟短信客户端，暂时无需解析回调");
    }

    @Override
    public SmsTemplateResp getSmsTemplate(String apiTemplateId) {
        return new SmsTemplateResp().setId(apiTemplateId).setContent("")
            .setAuditStatus(SmsTemplateAuditStatusEnum.SUCCESS.getKey()).setAuditReason("");
    }

}
