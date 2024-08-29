/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.Duration;

/**
 * @ClassName: SmsCodeProperties
 * @Description: 配置
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 22:21
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@ConfigurationProperties(prefix = "skyeye.sms-code")
@Validated
@Data
public class SmsCodeProperties {

    /**
     * 过期时间
     */
    @NotNull(message = "过期时间不能为空")
    private Duration expireTimes;

    /**
     * 短信发送频率
     */
    @NotNull(message = "短信发送频率不能为空")
    private Duration sendFrequency;

    /**
     * 每日发送最大数量
     */
    @NotNull(message = "每日发送最大数量不能为空")
    private Integer sendMaximumQuantityPerDay;

    /**
     * 验证码最小值
     */
    @NotNull(message = "验证码最小值不能为空")
    private Integer beginCode;

    /**
     * 验证码最大值
     */
    @NotNull(message = "验证码最大值不能为空")
    private Integer endCode;

}
