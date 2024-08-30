/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.courseware.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.courseware.entity.Courseware;
import com.skyeye.school.courseware.service.CoursewareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CoursewareController
 * @Description: 互动课件控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/2 9:31
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "互动课件", tags = "互动课件", modelName = "互动课件")
public class CoursewareController {

    @Autowired
    private CoursewareService coursewareService;

    /**
     * 添加或修改互动课件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeCourseware", value = "新增/编辑互动课件信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Courseware.class)
    @RequestMapping("/post/CoursewareController/writeCourseware")
    public void writeCourseware(InputObject inputObject, OutputObject outputObject) {
        coursewareService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id查询互动课件信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryCoursewareById", value = "根据id查询互动课件信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/CoursewareController/queryCoursewareById")
    public void queryCoursewareById(InputObject inputObject, OutputObject outputObject) {
        coursewareService.selectById(inputObject, outputObject);
    }

    /**
     * 删除互动课件信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteCoursewareById", value = "根据ID删除互动课件信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/CoursewareController/deleteCoursewareById")
    public void deleteCoursewareById(InputObject inputObject, OutputObject outputObject) {
        coursewareService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据科目表与班级表的关系id获取互动课件列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryCoursewareListBySubjectClassesId", value = "根据科目表与班级表的关系id获取互动课件列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "subjectClassesId", name = "subjectClassesId", value = "科目表与班级表的关系id", required = "required")})
    @RequestMapping("/post/CoursewareController/queryCoursewareListBySubjectClassesId")
    public void queryCoursewareListBySubjectClassesId(InputObject inputObject, OutputObject outputObject) {
        coursewareService.queryCoursewareListBySubjectClassesId(inputObject, outputObject);
    }

}
