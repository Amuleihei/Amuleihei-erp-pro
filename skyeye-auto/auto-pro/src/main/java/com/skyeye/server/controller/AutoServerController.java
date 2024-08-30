/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.server.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.server.entity.AutoServer;
import com.skyeye.server.service.AutoServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AutoServerController
 * @Description: 服务器管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/26 8:59
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "服务器管理", tags = "服务器管理", modelName = "服务器管理")
public class AutoServerController {

    @Autowired
    private AutoServerService autoServerService;

    /**
     * 获取服务器信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAutoServerList", value = "获取服务器信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/AutoServerController/queryAutoServerList")
    public void queryAutoServerList(InputObject inputObject, OutputObject outputObject) {
        autoServerService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加或修改服务器信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeAutoServer", value = "新增/编辑服务器信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = AutoServer.class)
    @RequestMapping("/post/AutoServerController/writeAutoServer")
    public void writeAutoServer(InputObject inputObject, OutputObject outputObject) {
        autoServerService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除服务器信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteAutoServerById", value = "根据ID删除服务器信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AutoServerController/deleteAutoServerById")
    public void deleteAutoServerById(InputObject inputObject, OutputObject outputObject) {
        autoServerService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据环境id获取服务器信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAutoServerListByEnvironmentId", value = "根据环境id获取服务器信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "environmentId", name = "environmentId", value = "环境id")})
    @RequestMapping("/post/AutoServerController/queryAutoServerListByEnvironmentId")
    public void queryAutoServerListByEnvironmentId(InputObject inputObject, OutputObject outputObject) {
        autoServerService.queryAutoServerListByEnvironmentId(inputObject, outputObject);
    }
}
