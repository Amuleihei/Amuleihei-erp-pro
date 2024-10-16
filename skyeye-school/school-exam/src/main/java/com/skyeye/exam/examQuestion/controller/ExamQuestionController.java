package com.skyeye.exam.examQuestion.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuestion.entity.ExamQuestion;
import com.skyeye.exam.examQuestion.service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ExamQuestionController
 * @Description: 问题表管理控制层
 * @author: skyeye云系列--lqy
 * @date: 2024/7/16 11:01
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "问题表管理", tags = "问题表管理", modelName = "问题表管理")
public class ExamQuestionController {

    @Autowired
    private ExamQuestionService examQuestionService;

    /**
     * 新增或编辑问题
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeOrUpdateQuestions", value = "新增或编辑问题", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = ExamQuestion.class)
    @RequestMapping("/post/ExamQuestionController/writeOrUpdateQuestions")
    public void writeOrUpdateQuestions(InputObject inputObject, OutputObject outputObject) {
        examQuestionService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id获取问题信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryQuestionById", value = "新增问题", method = "POST", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id",value = "问题id",required = "required")
    })
    @RequestMapping("/post/ExamQuestionController/queryQuestionById")
    public void queryQuestionById(InputObject inputObject, OutputObject outputObject) {
        examQuestionService.selectById(inputObject, outputObject);
    }

    /**
     * 获取所有问题信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryQuestionsList", value = "获取所有问题信息", method = "POST", allUse = "2")
    @RequestMapping("/post/ExamQuestionController/queryQuestionsPageList")
    public void queryQuestionsList(InputObject inputObject, OutputObject outputObject) {
        examQuestionService.queryQuestionsList(inputObject, outputObject);
    }

    /**
     * 根据id删除问题信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "exam015", value = "新增问题", method = "POST", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id",value = "问题id",required = "required")
    })
    @RequestMapping("/post/ExamQuestionController/deleteQuestionById")
    public void deleteQuestionById(InputObject inputObject, OutputObject outputObject) {
        examQuestionService.deleteQuestionById(inputObject, outputObject);
    }

}
