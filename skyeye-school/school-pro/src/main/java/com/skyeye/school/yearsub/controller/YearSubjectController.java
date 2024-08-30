/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.yearsub.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.yearsub.entity.YearSubjectBox;
import com.skyeye.school.yearsub.service.YearSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: YearSubjectController
 * @Description: 学年科目控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/29 16:26
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "学年科目管理", tags = "学年科目管理", modelName = "学年科目管理")
public class YearSubjectController {

    @Autowired
    private YearSubjectService yearSubjectService;

    /**
     * 根据专业id和学年id批量查询科目
     *
     * @param majorId 专业id
     * @param year    所属年份
     */
    @ApiOperation(id = "queryYearSubjectList", value = "获取学年科目信息", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "majorId", name = "majorId", value = "专业id", required = "required"),
        @ApiImplicitParam(id = "year", name = "year", value = "所属年份", required = "required")})
    @RequestMapping("/post/YearSubjectController/queryYearSubjectList")
    public void queryYearSubjectList(String majorId, Integer year) {
        yearSubjectService.queryYearSubjectList(majorId, year);
    }

    /**
     * 新增学年科目信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeYearSubject", value = "新增学年科目信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = YearSubjectBox.class)
    @RequestMapping("/post/YearSubjectController/writeYearSubject")
    public void writeYearSubject(InputObject inputObject, OutputObject outputObject) {
        yearSubjectService.writeYearSubject(inputObject, outputObject);
    }

}