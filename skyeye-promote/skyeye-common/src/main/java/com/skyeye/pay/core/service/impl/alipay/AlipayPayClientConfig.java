/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pay.core.service.impl.alipay;

import com.skyeye.pay.core.PayClientConfig;
import lombok.Data;

import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: AlipayPayClientConfig
 * @Description: 支付宝的 PayClientConfig 实现类
 * 属性主要来自 {@link com.alipay.api.AlipayConfig} 的必要属性
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/11 9:03
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
public class AlipayPayClientConfig implements PayClientConfig {

    /**
     * 公钥类型 - 公钥模式
     */
    public static final Integer MODE_PUBLIC_KEY = 1;
    /**
     * 公钥类型 - 证书模式
     */
    public static final Integer MODE_CERTIFICATE = 2;

    /**
     * 接口内容加密方式 - AES 加密
     */
    public static final String ENC_TYPE_AES = "AES";

    /**
     * 签名算法类型 - RSA
     */
    public static final String SIGN_TYPE_DEFAULT = "RSA2";

    /**
     * 网关地址
     * <p>
     * 1. <a href="https://openapi.alipay.com/gateway.do">生产环境</a>
     * 2. <a href="https://openapi-sandbox.dl.alipaydev.com/gateway.do">沙箱环境</a>
     */
    @NotBlank(message = "网关地址不能为空", groups = {ModePublicKey.class, ModeCertificate.class})
    private String serverUrl;

    /**
     * 开放平台上创建的应用的 ID
     */
    @NotBlank(message = "开放平台上创建的应用的 ID不能为空", groups = {ModePublicKey.class, ModeCertificate.class})
    private String appId;

    /**
     * 签名算法类型，推荐：RSA2
     * <p>
     * {@link #SIGN_TYPE_DEFAULT}
     */
    @NotBlank(message = "签名算法类型不能为空", groups = {ModePublicKey.class, ModeCertificate.class})
    private String signType;

    /**
     * 公钥类型
     * 1. {@link #MODE_PUBLIC_KEY} 情况，privateKey + alipayPublicKey
     * 2. {@link #MODE_CERTIFICATE} 情况，appCertContent + alipayPublicCertContent + rootCertContent
     */
    @NotNull(message = "公钥类型不能为空", groups = {ModePublicKey.class, ModeCertificate.class})
    private Integer mode;

    // ========== 公钥模式 ==========
    /**
     * 商户私钥
     */
    @NotBlank(message = "商户私钥不能为空", groups = {ModePublicKey.class})
    private String privateKey;

    /**
     * 支付宝公钥字符串
     */
    @NotBlank(message = "支付宝公钥字符串不能为空", groups = {ModePublicKey.class})
    private String alipayPublicKey;

    // ========== 证书模式 ==========
    /**
     * 指定商户公钥应用证书内容字符串
     */
    @NotBlank(message = "指定商户公钥应用证书内容不能为空", groups = {ModeCertificate.class})
    private String appCertContent;
    /**
     * 指定支付宝公钥证书内容字符串
     */
    @NotBlank(message = "指定支付宝公钥证书内容不能为空", groups = {ModeCertificate.class})
    private String alipayPublicCertContent;
    /**
     * 指定根证书内容字符串
     */
    @NotBlank(message = "指定根证书内容字符串不能为空", groups = {ModeCertificate.class})
    private String rootCertContent;

    /**
     * 接口内容加密方式
     * <p>
     * 1. 如果为空，将使用无加密方式
     * 2. 如果要加密，目前支付宝只有 AES 一种加密方式
     *
     * @see <a href="https://opendocs.alipay.com/common/02mse3">支付宝开放平台</a>
     * @see AlipayPayClientConfig#ENC_TYPE_AES
     */
    private String encryptType;

    /**
     * 接口内容加密的私钥
     */
    private String encryptKey;

    public interface ModePublicKey {
    }

    public interface ModeCertificate {
    }

    @Override
    public void validate(Validator validator) {
        validator.validate(this, MODE_PUBLIC_KEY.equals(this.getMode()) ? ModePublicKey.class : ModeCertificate.class);
    }

}
