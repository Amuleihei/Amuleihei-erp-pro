package com.skyeye.school.interaction.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.announcement.entity.Announcement;
import com.skyeye.school.interaction.entity.QuestionAnswer;
import com.skyeye.school.interaction.service.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: questionAnswerController
 * @Description: 互动答题题目答案管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/17 12:48
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "互动答题题目答案管理", tags = "互动答题题目答案管理", modelName = "互动答题题目答案管理")
public class QuestionAnswerController {

    @Autowired
    private QuestionAnswerService questionAnswerService;

    /**
     * 添加或修该题目答案
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeQuestionAnswer" , value = "添加或修该题目答案", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = QuestionAnswer.class)
    @RequestMapping("/post/QuestionAnswerController/writeQuestionAnswer")
    public void writeQuestionAnswer(InputObject inputObject, OutputObject outputObject) {
        questionAnswerService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据questionId查询答案
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAnswerByQuestionId" , value = "根据questionId查询答案", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id="questionId",name = "questionId" ,value="题目id" ,required = "required")})
    @RequestMapping("/post/QuestionAnswerController/queryAnswerByQuestionId")
    public void queryAnswerByQuestionId(InputObject inputObject, OutputObject outputObject) {
        questionAnswerService.queryAnswerByQuestionId(inputObject, outputObject);
    }

    /**
     * 根据id删除答案
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteAnswerById" , value = "根据id删除答案", method = "POST", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id="id",name = "id" ,value="主键id" ,required = "required")})
    @RequestMapping("/post/QuestionAnswerController/deleteAnswerById")
    public void deleteAnswerById(InputObject inputObject, OutputObject outputObject) {
        questionAnswerService.deleteById(inputObject, outputObject);
    }

}
