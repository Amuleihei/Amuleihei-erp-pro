/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.server.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.server.entity.ServiceBeanCustom;
import com.skyeye.server.service.ServiceBeanCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ServerController
 * @Description: 自定义服务管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/1/6 22:26
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "自定义服务管理", tags = "自定义服务管理", modelName = "系统公共模块")
public class ServiceBeanCustomController {

    @Autowired
    private ServiceBeanCustomService serviceBeanCustomService;

    /**
     * 获取服务信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryServiceBeanCustom", value = "获取服务信息", method = "GET", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "className", name = "className", value = "service的className", required = "required"),
        @ApiImplicitParam(id = "appId", name = "appId", value = "服务的appId", required = "required")})
    @RequestMapping("/post/ServiceBeanCustomController/queryServiceBeanCustom")
    public void queryServiceBeanCustom(InputObject inputObject, OutputObject outputObject) {
        serviceBeanCustomService.queryServiceBeanCustom(inputObject, outputObject);
    }

    /**
     * 保存自定义服务信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "saveServiceBeanCustom", value = "保存自定义服务信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = ServiceBeanCustom.class)
    @RequestMapping("/post/ServiceBeanCustomController/saveServiceBeanCustom")
    public void saveServiceBeanCustom(InputObject inputObject, OutputObject outputObject) {
        serviceBeanCustomService.saveOrUpdateEntity(inputObject, outputObject);
    }

}
