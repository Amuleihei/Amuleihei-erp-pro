/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;
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
public class SkyeyeZuulFilter extends ZuulFilter {

    private static final List<String> METHOD_LIST = Arrays.asList("GET", "POST", "PUT", "DELETE");

    /**
     * 过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。 这里定义为pre，代表会在请求被路由之前执行。
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filter执行顺序，通过数字指定。 数字越大，优先级越低。
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断该过滤器是否需要被执行。这里我们直接返回了true，因此该过滤器对所有请求都会生效。 实际运用中我们可以利用该函数来指定过滤器的有效范围。
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        String method = RequestContext.getCurrentContext().getRequest().getMethod().toUpperCase();
        if (!METHOD_LIST.contains(method)) {
            return false;
        }
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String uri = request.getRequestURI();
        if (uri.contains("/images/")) {
            return false;
        }
        return true;
    }

    /**
     * 过滤器的具体逻辑
     *
     * @return
     */
    @Override
    public Object run() {
        // 获取currentContext
        RequestContext currentContext = RequestContext.getCurrentContext();
        // 获取响应对象
        HttpServletResponse response = currentContext.getResponse();
        // 设置响应格式
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        System.err.println("REQUEST:: @@START@@ " + request.getSession().getId());
        System.err.println("REQUEST:: " + request.getScheme() + " " + request.getRemoteAddr() + ":" + request.getRemotePort());
        System.err.println("REQUEST:: " + request.getScheme() + " " + request.getRemoteAddr() + ":" + request.getRemotePort());
        StringBuilder params = new StringBuilder("?");
        Enumeration<String> names = request.getParameterNames();
        if (request.getMethod().equals("GET")) {
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                params.append(name);
                params.append("=");
                params.append(request.getParameter(name));
                params.append("&");
            }
        }
        if (params.length() > 0) {
            params.delete(params.length() - 1, params.length());
        }
        System.err.println("REQUEST:: > " + request.getMethod() + " " + request.getRequestURI() + params + " " + request.getProtocol());
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String name = headers.nextElement();
            String value = request.getHeader(name);
            System.err.println("REQUEST:: > " + name + ":" + value);
        }
        System.err.println("REQUEST:: @@END@@ " + request.getSession().getId());

        return null;
    }

}
