/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.CodeModelGroup;
import com.skyeye.eve.service.CodeModelGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CodeModelGroupController
 * @Description: 模板分组管理
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/6 9:46
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "模板分组", tags = "模板分组", modelName = "代码生成器")
public class CodeModelGroupController {

    @Autowired
    private CodeModelGroupService codeModelGroupService;

    @ApiOperation(id = "codemodel001", value = "获取模板分组列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/CodeModelGroupController/queryCodeModelGroupList")
    public void queryCodeModelGroupList(InputObject inputObject, OutputObject outputObject) {
        codeModelGroupService.queryPageList(inputObject, outputObject);
    }

    @ApiOperation(id = "writeCodeModelGroup", value = "新增/编辑模板分组", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CodeModelGroup.class)
    @RequestMapping("/post/CodeModelGroupController/writeCodeModelGroup")
    public void writeCodeModelGroup(InputObject inputObject, OutputObject outputObject) {
        codeModelGroupService.saveOrUpdateEntity(inputObject, outputObject);
    }

    @ApiOperation(id = "deleteCodeModelGroupById", value = "根据id删除模板分组信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/CodeModelGroupController/deleteCodeModelGroupById")
    public void deleteCodeModelGroupById(InputObject inputObject, OutputObject outputObject) {
        codeModelGroupService.deleteById(inputObject, outputObject);
    }

    @ApiOperation(id = "codemodel011", value = "根据表名获取表的相关信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "tableName", name = "tableName", value = "表名", required = "required")})
    @RequestMapping("/post/CodeModelGroupController/queryTableParameterByTableName")
    public void queryTableParameterByTableName(InputObject inputObject, OutputObject outputObject) {
        codeModelGroupService.queryTableParameterByTableName(inputObject, outputObject);
    }

    @ApiOperation(id = "codemodel012", value = "根据表名获取表的相关转换信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "tableName", name = "tableName", value = "表名", required = "required")})
    @RequestMapping("/post/CodeModelGroupController/queryTableMationByTableName")
    public void queryTableMationByTableName(InputObject inputObject, OutputObject outputObject) {
        codeModelGroupService.queryTableMationByTableName(inputObject, outputObject);
    }

    @ApiOperation(id = "codemodel013", value = "根据分组id获取模板列表", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "groupId", name = "groupId", value = "分组id", required = "required")})
    @RequestMapping("/post/CodeModelGroupController/queryCodeModelListByGroupId")
    public void queryCodeModelListByGroupId(InputObject inputObject, OutputObject outputObject) {
        codeModelGroupService.queryCodeModelListByGroupId(inputObject, outputObject);
    }

}
