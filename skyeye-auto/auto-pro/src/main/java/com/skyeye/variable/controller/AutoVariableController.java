/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.variable.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.variable.entity.AutoVariable;
import com.skyeye.variable.service.AutoVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AutoVariableController
 * @Description: 变量管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/26 9:02
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "变量管理", tags = "变量管理", modelName = "变量管理")
public class AutoVariableController {

    @Autowired
    private AutoVariableService autoVariableService;

    /**
     * 获取变量信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAutoVariableList", value = "获取变量信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/AutoVariableController/queryAutoVariableList")
    public void queryAutoVariableList(InputObject inputObject, OutputObject outputObject) {
        autoVariableService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加或修改变量信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeAutoVariable", value = "新增/编辑变量信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = AutoVariable.class)
    @RequestMapping("/post/AutoVariableController/writeAutoVariable")
    public void writeAutoVariable(InputObject inputObject, OutputObject outputObject) {
        autoVariableService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除变量信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteAutoVariableById", value = "根据ID删除变量信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AutoVariableController/deleteAutoVariableById")
    public void deleteAutoVariableById(InputObject inputObject, OutputObject outputObject) {
        autoVariableService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id查询变量信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAutoVariableById", value = "根据id查询变量信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AutoVariableController/queryAutoVariableById")
    public void queryAutoVariableById(InputObject inputObject, OutputObject outputObject) {
        autoVariableService.selectById(inputObject, outputObject);
    }
}
