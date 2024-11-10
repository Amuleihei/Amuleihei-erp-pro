/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.question.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.question.entity.Question;
import com.skyeye.eve.question.service.QuestionService;
import com.skyeye.exam.examanradio.entity.ExamAnRadio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: QuestionController
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2024/1/15 15:21
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "题目管理", tags = "题目管理", modelName = "题库管理")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 添加/编辑问题
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeQuestion", value = "添加/编辑问题", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Question.class)
    @RequestMapping("/post/QuestionController/writeQuestion")
    public void writeQuestion(InputObject inputObject, OutputObject outputObject) {
        questionService.saveOrUpdateEntity(inputObject, outputObject);
    }

}
