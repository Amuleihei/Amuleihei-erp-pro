/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: CorsResponseHeaderFilter
 * @Description: 解决 Spring Cloud Gateway 2.x 跨域时，出现重复 Origin 的 BUG
 * 参考文档：<a href="https://blog.csdn.net/zimou5581/article/details/90043178" />
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/15 14:04
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Component
public class CorsResponseHeaderFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        // 指定此过滤器位于 NettyWriteResponseFilter 之后
        // 即待处理完响应体后接着处理响应头
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER + 1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.defer(() -> {
            List<Map.Entry<String, List<String>>> collect = exchange.getResponse().getHeaders().entrySet().stream()
                .filter(kv -> (kv.getValue() != null && kv.getValue().size() > 1))
                .filter(kv -> (kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)
                    || kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS))).collect(Collectors.toList());
            collect.stream().forEach(kv -> kv.setValue(new ArrayList<String>() {{
                add(kv.getValue().get(0));
            }}));
            return chain.filter(exchange);
        }));
    }

}
