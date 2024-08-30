package com.skyeye.school.interaction.controller;


import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.interaction.entity.QuestionAnswer;
import com.skyeye.school.interaction.entity.QuestionStuRecord;
import com.skyeye.school.interaction.service.QuestionStuRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: questionStuRecordController
 * @Description: 互动答题学生答题记录管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/17 12:48
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "互动答题学生答题记录管理", tags = "互动答题学生答题记录管理", modelName = "互动答题学生答题记录管理")
public class QuestionStuRecordController {

    @Autowired
    private QuestionStuRecordService questionStuRecordService;

    /**
     * 学生提交问题答案
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "submitQuestionAnswer" , value = "学生提交问题答案", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = QuestionStuRecord.class)
    @RequestMapping("/post/QuestionAnswerController/submitQuestionAnswer")
    public void submitQuestionAnswer(InputObject inputObject, OutputObject outputObject) {
        questionStuRecordService.submitQuestionAnswer(inputObject, outputObject);
    }

    /**
     * 根据questionId查询学生答题记录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryRecordByQuestionId" , value = "根据questionId查询学生答题记录", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "questionId", name = "questionId",value = "题目Id", required = "required")})
    @RequestMapping("/post/QuestionAnswerController/queryRecordByQuestionId")
    public void queryRecordByQuestionId(InputObject inputObject, OutputObject outputObject) {
        questionStuRecordService.queryRecordByQuestionId(inputObject, outputObject);
    }
}
