/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.education.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.education.entity.Education;
import com.skyeye.education.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: EducationController
 * @Description: 员工教育背景管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/5/17 7:56
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "员工教育背景", tags = "员工教育背景", modelName = "员工教育背景")
public class EducationController {

    @Autowired
    private EducationService educationService;

    /**
     * 查询教育背景列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryEducationList", value = "查询教育背景列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/EducationController/queryEducationList")
    public void queryEducationList(InputObject inputObject, OutputObject outputObject) {
        educationService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑员工教育背景信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeEducation", value = "新增/编辑员工教育背景信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Education.class)
    @RequestMapping("/post/EducationController/writeEducation")
    public void writeEducation(InputObject inputObject, OutputObject outputObject) {
        educationService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id删除员工教育背景信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteEducationById", value = "根据id删除员工教育背景信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/EducationController/deleteEducationById")
    public void deleteEducationById(InputObject inputObject, OutputObject outputObject) {
        educationService.deleteById(inputObject, outputObject);
    }

}
