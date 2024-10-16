/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.ai.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName: SkyeyeAiProperties
 * @Description: Skyeye AI 配置类
 * @author: skyeye云系列--卫志强
 * @date: 2024/10/5 18:03
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ConfigurationProperties(prefix = "spring.ai")
public class SkyeyeAiProperties {

    private QianfanProperties qianfan;

    private  XunfeiProperties xunfei;

    private  DeepseekProperties deepSeek;

    private TongyiProperties tongYi;

    private  ZhupuProperties zhiPu;

    @Data
    public static class QianfanProperties {
        private String apiKey;
        private String secretKey;
    }

    @Data
    public static class XunfeiProperties {
        private String appId;
        private String apiKey;
        private String secretKey;
    }

    @Data
    public static class DeepseekProperties {
        private String apiKey;
        private String secretKey = "DEEP_SEEK";
        private String url;
    }

    @Data
    public static class TongyiProperties {
        private String apiKey;
        private String secretKey = "TONG_YI";
        private String message;
    }

    @Data
    public static class ZhupuProperties {
        private String apiKey;
        private String secretKey;
        private String url;
    }
}
