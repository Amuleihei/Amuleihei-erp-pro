/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.ai.core.config;

import com.skyeye.ai.core.factory.AiFactory;
import com.skyeye.ai.core.factory.AiFactoryImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: AiConfiguration
 * @Description: AI配置类
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/18 22:10
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Configuration
@EnableConfigurationProperties(SkyeyeAiProperties.class)
public class AiConfiguration {

    @Bean
    public AiFactory aiClientFactory() {
        return new AiFactoryImpl();
    }

}
