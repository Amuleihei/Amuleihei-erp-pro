/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com;

import org.nutz.plugin.spring.boot.NutzDaoAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    NutzDaoAutoConfiguration.class
})
@ComponentScan(basePackages = {"com.skyeye"})
@EnableDiscoveryClient // 开启服务发现
public class SkyeyeZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkyeyeZuulApplication.class, args);
    }

}
