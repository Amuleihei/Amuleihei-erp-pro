/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.attr.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.attr.entity.AttrDefinitionCustom;
import com.skyeye.attr.service.AttrDefinitionCustomService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AttrDefinitionCustomController
 * @Description: 用户自定义服务类属性控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/12/30 11:01
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "自定义属性管理", tags = "自定义属性管理", modelName = "系统公共模块")
public class AttrDefinitionCustomController {

    @Autowired
    private AttrDefinitionCustomService attrDefinitionCustomService;

    /**
     * 获取属性信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAttrDefinitionCustom", value = "获取属性信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "className", name = "className", value = "service的className", required = "required"),
        @ApiImplicitParam(id = "attrKey", name = "attrKey", value = "属性", required = "required"),
        @ApiImplicitParam(id = "appId", name = "appId", value = "服务的appId", required = "required")})
    @RequestMapping("/post/AttrDefinitionCustomController/queryAttrDefinitionCustom")
    public void queryAttrDefinitionCustom(InputObject inputObject, OutputObject outputObject) {
        attrDefinitionCustomService.queryAttrDefinitionCustom(inputObject, outputObject);
    }

    /**
     * 保存自定义属性信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "saveAttrDefinitionCustom", value = "保存自定义属性信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = AttrDefinitionCustom.class)
    @RequestMapping("/post/AttrDefinitionCustomController/saveAttrDefinitionCustom")
    public void saveAttrDefinitionCustom(InputObject inputObject, OutputObject outputObject) {
        attrDefinitionCustomService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除自定义属性信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteAttrDefinitionCustom", value = "删除自定义属性信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "className", name = "className", value = "service的className", required = "required"),
        @ApiImplicitParam(id = "attrKey", name = "attrKey", value = "属性", required = "required"),
        @ApiImplicitParam(id = "appId", name = "appId", value = "服务的appId", required = "required")})
    @RequestMapping("/post/AttrDefinitionCustomController/deleteAttrDefinitionCustom")
    public void deleteAttrDefinitionCustom(InputObject inputObject, OutputObject outputObject) {
        attrDefinitionCustomService.deleteAttrDefinitionCustom(inputObject, outputObject);
    }

    /**
     * 根据组件id查询正在使用该组件的服务类信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAttrByComponentId", value = "根据组件id查询正在使用该组件的服务类信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "componentId", name = "componentId", value = "组件id", required = "required")})
    @RequestMapping("/post/AttrDefinitionCustomController/queryAttrByComponentId")
    public void queryAttrByComponentId(InputObject inputObject, OutputObject outputObject) {
        attrDefinitionCustomService.queryAttrByComponentId(inputObject, outputObject);
    }

    /**
     * 根据id删除自定义属性信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteAttrDefinitionCustomById", value = "根据id删除自定义属性信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AttrDefinitionCustomController/deleteAttrDefinitionCustomById")
    public void deleteAttrDefinitionCustomById(InputObject inputObject, OutputObject outputObject) {
        attrDefinitionCustomService.deleteById(inputObject, outputObject);
    }

}
