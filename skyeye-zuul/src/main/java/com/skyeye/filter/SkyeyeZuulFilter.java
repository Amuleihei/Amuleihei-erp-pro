/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: SkyeyeZuulFilter
 * @Description: 网关过滤器
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/12 10:54
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Component
public class SkyeyeZuulFilter implements GlobalFilter, Ordered {

    private static final List<String> METHOD_LIST = Arrays.asList("GET", "POST", "PUT", "DELETE");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取请求对象
        ServerHttpRequest request = exchange.getRequest();
        // 2.获取响应对象
        ServerHttpResponse response = exchange.getResponse();

        String method = request.getMethod().name().toUpperCase();
        if (!METHOD_LIST.contains(method)) {
            response.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED);
            return response.setComplete();
        }
        String uri = request.getURI().getPath();
        if (uri.contains("/images/")) {
            response.setStatusCode(HttpStatus.NOT_FOUND);
            return response.setComplete();
        }
        // 放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
