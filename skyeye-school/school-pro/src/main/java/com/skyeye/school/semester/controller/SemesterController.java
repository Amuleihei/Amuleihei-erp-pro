/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.semester.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.semester.entity.Semester;
import com.skyeye.school.semester.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SemesterController
 * @Description: 学期管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/29 10:51
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "学期管理", tags = "学期管理", modelName = "学期管理")
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    /**
     * 获取学期信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "querySemesterList", value = "获取学期信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/SemesterController/querySemesterList")
    public void querySemesterList(InputObject inputObject, OutputObject outputObject) {
        semesterService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加或修改学期信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeSemester", value = "新增/编辑学期信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = Semester.class)
    @RequestMapping("/post/SemesterController/writeSemester")
    public void writeSemester(InputObject inputObject, OutputObject outputObject) {
        semesterService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除学期信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteSemesterById", value = "根据ID删除学期信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SemesterController/deleteSemesterById")
    public void deleteSemesterById(InputObject inputObject, OutputObject outputObject) {
        semesterService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取所有学期列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAllSemesterList", value = "获取所有学期列表", method = "GET", allUse = "2")
    @RequestMapping("/post/SemesterController/queryAllSemesterList")
    public void queryAllSemesterList(InputObject inputObject, OutputObject outputObject) {
        semesterService.queryAllSemesterList(inputObject, outputObject);
    }

}
