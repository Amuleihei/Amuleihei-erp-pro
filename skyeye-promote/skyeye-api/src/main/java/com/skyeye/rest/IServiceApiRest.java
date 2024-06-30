/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.rest;

import com.skyeye.common.client.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

/**
 * @ClassName: IServiceApiRest
 * @Description: 服务接口管理公共的一些操作
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/19 17:26
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@FeignClient(name = "IServiceApiRest${spring.application.name}", configuration = ClientConfiguration.class, url = "EMPTY")
public interface IServiceApiRest {

    /**
     * 获取接口列表
     *
     * @return
     */
    @GetMapping("/queryAllServiceApiList")
    String queryAllServiceApiList(URI uri);

    /**
     * 根据id查询接口
     *
     * @return
     */
    @GetMapping("/queryServiceApiById")
    String queryServiceApiById(URI uri, @RequestParam("apiId") String apiId);

}
