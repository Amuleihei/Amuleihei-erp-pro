/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.environment.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.environment.entity.AutoEnvironment;
import com.skyeye.environment.service.AutoEnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AutoEnvironmentController
 * @Description: 环境管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/26 8:37
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "环境管理", tags = "环境管理", modelName = "环境管理")
public class AutoEnvironmentController {

    @Autowired
    private AutoEnvironmentService autoEnvironmentService;

    /**
     * 获取环境信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAutoEnvironmentList", value = "获取环境信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/AutoEnvironmentController/queryAutoEnvironmentList")
    public void queryAutoEnvironmentList(InputObject inputObject, OutputObject outputObject) {
        autoEnvironmentService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加或修改环境信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeAutoEnvironment", value = "新增/编辑环境信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = AutoEnvironment.class)
    @RequestMapping("/post/AutoEnvironmentController/writeAutoEnvironment")
    public void writeAutoEnvironment(InputObject inputObject, OutputObject outputObject) {
        autoEnvironmentService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除环境信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteAutoEnvironmentById", value = "根据ID删除环境信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AutoEnvironmentController/deleteAutoEnvironmentById")
    public void deleteAutoEnvironmentById(InputObject inputObject, OutputObject outputObject) {
        autoEnvironmentService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取所有环境信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAllAutoEnvironmentList", value = "获取所有环境信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "objectId", name = "objectId", value = "所属第三方业务数据id", required = "required"),
        @ApiImplicitParam(id = "objectKey", name = "objectKey", value = "所属第三方业务数据的key", required = "required")})
    @RequestMapping("/post/AutoEnvironmentController/queryAllAutoEnvironmentList")
    public void queryAllAutoEnvironmentList(InputObject inputObject, OutputObject outputObject) {
        autoEnvironmentService.queryAllAutoEnvironmentList(inputObject, outputObject);
    }
}

