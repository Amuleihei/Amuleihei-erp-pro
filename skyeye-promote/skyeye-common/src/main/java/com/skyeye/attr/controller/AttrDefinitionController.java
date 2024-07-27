/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.attr.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.attr.service.AttrDefinitionService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AttrDefinitionController
 * @Description: 服务类属性管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 13:11
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "属性管理", tags = "属性管理", modelName = "系统公共模块")
public class AttrDefinitionController {

    @Autowired
    private AttrDefinitionService attrDefinitionService;

    /**
     * 根据service的className获取属性信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAttrDefinitionList", value = "根据service的className获取属性信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "className", name = "className", value = "service的className", required = "required"),
        @ApiImplicitParam(id = "appId", name = "appId", value = "服务的appId", required = "required")})
    @RequestMapping("/post/AttrDefinitionController/queryAttrDefinitionList")
    public void queryAttrDefinitionList(InputObject inputObject, OutputObject outputObject) {
        attrDefinitionService.queryAttrDefinitionList(inputObject, outputObject);
    }

    /**
     * 获取子属性信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryChildAttrDefinitionList", value = "获取子属性信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "className", name = "className", value = "service的className", required = "required"),
        @ApiImplicitParam(id = "attrKey", name = "attrKey", value = "属性", required = "required"),
        @ApiImplicitParam(id = "appId", name = "appId", value = "服务的appId", required = "required")})
    @RequestMapping("/post/AttrDefinitionController/queryChildAttrDefinitionList")
    public void queryChildAttrDefinitionList(InputObject inputObject, OutputObject outputObject) {
        attrDefinitionService.queryChildAttrDefinitionList(inputObject, outputObject);
    }

}
