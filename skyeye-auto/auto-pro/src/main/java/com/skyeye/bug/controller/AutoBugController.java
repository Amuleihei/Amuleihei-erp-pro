/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.bug.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.bug.entity.AutoBug;
import com.skyeye.bug.entity.AutoBugQueryDo;
import com.skyeye.bug.service.AutoBugService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AutoBugController
 * @Description: bug管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/18 22:01
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "bug管理", tags = "bug管理", modelName = "bug管理")
public class AutoBugController {

    @Autowired
    private AutoBugService autoBugService;

    /**
     * 获取bug列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAutoBugList", value = "获取bug列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = AutoBugQueryDo.class)
    @RequestMapping("/post/AutoBugController/queryAutoBugList")
    public void queryAutoBugList(InputObject inputObject, OutputObject outputObject) {
        autoBugService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑bug
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeAutoBug", value = "新增/编辑bug", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = AutoBug.class)
    @RequestMapping("/post/AutoBugController/writeAutoBug")
    public void writeAutoBug(InputObject inputObject, OutputObject outputObject) {
        autoBugService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除bug信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteAutoBugById", value = "删除bug信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AutoBugController/deleteAutoBugById")
    public void deleteAutoBugById(InputObject inputObject, OutputObject outputObject) {
        autoBugService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id查询bug信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAutoBugById", value = "根据id查询bug信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AutoBugController/queryAutoBugById")
    public void queryAutoBugById(InputObject inputObject, OutputObject outputObject) {
        autoBugService.selectById(inputObject, outputObject);
    }

}
