/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.clazz.entity.classenum.SkyeyeClassEnumApiMation;
import com.skyeye.clazz.service.SkyeyeClassEnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SkyeyeClassEnumController
 * @Description: 基本框架---具备某个特征的枚举类管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/11 19:50
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "枚举类管理", tags = "枚举类管理", modelName = "系统公共模块")
public class SkyeyeClassEnumController {

    @Autowired
    private SkyeyeClassEnumService skyeyeClassEnumService;

    /**
     * 批量新增枚举类
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeClassEnum", value = "批量新增枚举类", method = "POST", allUse = "0")
    @ApiImplicitParams(classBean = SkyeyeClassEnumApiMation.class)
    @RequestMapping("/post/SkyeyeClassEnumController/writeClassEnum")
    public void writeClassEnum(InputObject inputObject, OutputObject outputObject) {
        skyeyeClassEnumService.writeClassEnum(inputObject, outputObject);
    }

    /**
     * 根据className获取可以展示在界面上的枚举数据信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "getEnumDataByClassName", value = "根据className获取可以展示在界面上的枚举数据信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "className", name = "className", value = "className", required = "required"),
        @ApiImplicitParam(id = "filterKey", name = "filterKey", value = "指定需要匹配的key"),
        @ApiImplicitParam(id = "filterValue", name = "filterValue", value = "需要过滤出来的指定的枚举数据，多个逗号隔开")})
    @RequestMapping("/post/SkyeyeClassEnumController/getEnumDataByClassName")
    public void getEnumDataByClassName(InputObject inputObject, OutputObject outputObject) {
        skyeyeClassEnumService.getEnumDataByClassName(inputObject, outputObject);
    }

    /**
     * 根据className获取可以展示在界面上的枚举数据信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "getEnumDataMapByClassName", value = "根据className获取可以展示在界面上的枚举数据信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "classNameList", name = "classNameList", value = "className的集合", required = "required")})
    @RequestMapping("/post/SkyeyeClassEnumController/getEnumDataMapByClassName")
    public void getEnumDataMapByClassName(InputObject inputObject, OutputObject outputObject) {
        skyeyeClassEnumService.getEnumDataMapByClassName(inputObject, outputObject);
    }

}
