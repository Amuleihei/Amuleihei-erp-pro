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

    @Data
    public static class QianfanProperties {
        private String apiKey;
        private String secretKey;
    }
}
