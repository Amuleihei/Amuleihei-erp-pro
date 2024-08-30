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
import com.skyeye.school.courseware.service.CoursewareStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CoursewareStudyController
 * @Description: 互动课件学习信息控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/2 10:02
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "互动课件学习信息", tags = "互动课件学习信息", modelName = "互动课件")
public class CoursewareStudyController {

    @Autowired
    private CoursewareStudyService coursewareStudyService;

    /**
     * 课件学习
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "studyCoursewareByCoursewareId", value = "课件学习", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "coursewareId", name = "coursewareId", value = "课件id", required = "required")})
    @RequestMapping("/post/CoursewareStudyController/studyCoursewareByCoursewareId")
    public void studyCoursewareByCoursewareId(InputObject inputObject, OutputObject outputObject) {
        coursewareStudyService.studyCoursewareByCoursewareId(inputObject, outputObject);
    }

}
