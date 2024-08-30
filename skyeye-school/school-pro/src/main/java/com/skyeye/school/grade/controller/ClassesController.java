/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.grade.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.grade.entity.Classes;
import com.skyeye.school.grade.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ClassesController
 * @Description: 班级信息管理
 * @author: skyeye云系列--卫志强
 * @date: 2022/5/26 12:48
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "班级管理", tags = "班级管理", modelName = "班级管理")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    /**
     * 获取班级信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryClassList", value = "获取班级信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ClassesController/queryClassList")
    public void queryClassList(InputObject inputObject, OutputObject outputObject) {
        classesService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加或修改班级信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeClass", value = "新增/编辑班级信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = Classes.class)
    @RequestMapping("/post/ClassesController/writeClass")
    public void writeClass(InputObject inputObject, OutputObject outputObject) {
        classesService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除班级信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteClassById", value = "根据ID删除班级信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ClassesController/deleteClassById")
    public void deleteClassById(InputObject inputObject, OutputObject outputObject) {
        classesService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据专业id获取班级列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */

    @ApiOperation(id = "queryClassListByMajorId", value = "根据专业id获取班级列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "majorId", name = "majorId", value = "专业id")})
    @RequestMapping("/post/ClassesController/queryClassListByMajorId")
    public void queryClassListByMajorId(InputObject inputObject, OutputObject outputObject) {
        classesService.queryClassListByMajorId(inputObject, outputObject);
    }

}
