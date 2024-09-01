/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.lightapp.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.lightapp.entity.LightAppType;
import com.skyeye.eve.lightapp.service.LightAppTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: LightAppTypeController
 * @Description: 轻应用类型管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/4 23:04
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "轻应用类型管理", tags = "轻应用类型管理", modelName = "轻应用管理")
public class LightAppTypeController {

    @Autowired
    private LightAppTypeService lightAppTypeService;

    /**
     * 获取轻应用类型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryLightAppTypeList", value = "获取轻应用类型列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/LightAppTypeController/queryLightAppTypeList")
    public void queryLightAppTypeList(InputObject inputObject, OutputObject outputObject) {
        lightAppTypeService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑轻应用类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeLightAppType", value = "新增/编辑轻应用类型", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = LightAppType.class)
    @RequestMapping("/post/LightAppTypeController/writeLightAppType")
    public void writeLightAppType(InputObject inputObject, OutputObject outputObject) {
        lightAppTypeService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除轻应用类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteLightAppTypeById", value = "删除轻应用类型", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/LightAppTypeController/deleteLightAppTypeById")
    public void deleteLightAppTypeById(InputObject inputObject, OutputObject outputObject) {
        lightAppTypeService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取启用的轻应用类型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryLightAppTypeUpList", value = "获取启用的轻应用类型列表", method = "GET", allUse = "2")
    @RequestMapping("/post/LightAppTypeController/queryLightAppTypeUpList")
    public void queryLightAppTypeUpList(InputObject inputObject, OutputObject outputObject) {
        lightAppTypeService.queryLightAppTypeUpList(inputObject, outputObject);
    }

}
