/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamController {

    @Autowired
    private ExamService examService;

    /**
     * 获取所有试卷列表
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/queryExamList")
    public void queryExamList(InputObject inputObject, OutputObject outputObject) {
        examService.queryExamList(inputObject, outputObject);
    }

    /**
     * 获取我的试卷列表
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/queryMyExamList")
    public void queryMyExamList(InputObject inputObject, OutputObject outputObject) {
        examService.queryMyExamList(inputObject, outputObject);
    }

    /**
     * 新增试卷
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/insertExamMation")
    public void insertExamMation(InputObject inputObject, OutputObject outputObject) {
        examService.insertExamMation(inputObject, outputObject);
    }

    /**
     * 获取试卷题目信息
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/queryExamMationById")
    public void queryExamMationById(InputObject inputObject, OutputObject outputObject) {
        examService.queryExamMationById(inputObject, outputObject);
    }

    /**
     * 添加填空题
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/addQuFillblankMation")
    public void addQuFillblankMation(InputObject inputObject, OutputObject outputObject) {
        examService.addQuFillblankMation(inputObject, outputObject);
    }

    /**
     * 添加评分题
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/addQuScoreMation")
    public void addQuScoreMation(InputObject inputObject, OutputObject outputObject) {
        examService.addQuScoreMation(inputObject, outputObject);
    }

    /**
     * 添加排序题
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/addQuOrderquMation")
    public void addQuOrderquMation(InputObject inputObject, OutputObject outputObject) {
        examService.addQuOrderquMation(inputObject, outputObject);
    }

    /**
     * 添加分页标记
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/addQuPagetagMation")
    public void addQuPagetagMation(InputObject inputObject, OutputObject outputObject) {
        examService.addQuPagetagMation(inputObject, outputObject);
    }

    /**
     * 添加单选题
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/addQuRadioMation")
    public void addQuRadioMation(InputObject inputObject, OutputObject outputObject) {
        examService.addQuRadioMation(inputObject, outputObject);
    }

    /**
     * 添加多选题
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/addQuCheckBoxMation")
    public void addQuCheckBoxMation(InputObject inputObject, OutputObject outputObject) {
        examService.addQuCheckBoxMation(inputObject, outputObject);
    }

    /**
     * 添加多选填空题
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/addQuMultiFillblankMation")
    public void addQuMultiFillblankMation(InputObject inputObject, OutputObject outputObject) {
        examService.addQuMultiFillblankMation(inputObject, outputObject);
    }

    /**
     * 添加段落题
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/addQuParagraphMation")
    public void addQuParagraphMation(InputObject inputObject, OutputObject outputObject) {
        examService.addQuParagraphMation(inputObject, outputObject);
    }

    /**
     * 添加矩阵单选题,矩阵多选题,矩阵评分题,矩阵填空题
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/addQuChenMation")
    public void addQuChenMation(InputObject inputObject, OutputObject outputObject) {
        examService.addQuChenMation(inputObject, outputObject);
    }

    /**
     * 删除问题
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/deleteQuestionMationById")
    public void deleteQuestionMationById(InputObject inputObject, OutputObject outputObject) {
        examService.deleteQuestionMationById(inputObject, outputObject);
    }

    /**
     * 删除矩阵单选题,矩阵多选题,矩阵评分题,矩阵填空题列选项
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/deleteQuestionChenColumnMationById")
    public void deleteQuestionChenColumnMationById(InputObject inputObject, OutputObject outputObject) {
        examService.deleteQuestionChenColumnMationById(inputObject, outputObject);
    }

    /**
     * 删除矩阵单选题,矩阵多选题,矩阵评分题,矩阵填空题行选项
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/deleteQuestionChenRowMationById")
    public void deleteQuestionChenRowMationById(InputObject inputObject, OutputObject outputObject) {
        examService.deleteQuestionChenRowMationById(inputObject, outputObject);
    }

    /**
     * 删除单选题选项
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/deleteQuestionRadioOptionMationById")
    public void deleteQuestionRadioOptionMationById(InputObject inputObject, OutputObject outputObject) {
        examService.deleteQuestionRadioOptionMationById(inputObject, outputObject);
    }

    /**
     * 删除多选题选项
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/deleteQuestionChedkBoxOptionMationById")
    public void deleteQuestionChedkBoxOptionMationById(InputObject inputObject, OutputObject outputObject) {
        examService.deleteQuestionChedkBoxOptionMationById(inputObject, outputObject);
    }

    /**
     * 删除评分题选项
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/deleteQuestionScoreOptionMationById")
    public void deleteQuestionScoreOptionMationById(InputObject inputObject, OutputObject outputObject) {
        examService.deleteQuestionScoreOptionMationById(inputObject, outputObject);
    }

    /**
     * 删除排序选项
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/deleteQuestionOrderOptionMationById")
    public void deleteQuestionOrderOptionMationById(InputObject inputObject, OutputObject outputObject) {
        examService.deleteQuestionOrderOptionMationById(inputObject, outputObject);
    }

    /**
     * 删除多项填空题选项
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/deleteQuestionMultiFillblankOptionMationById")
    public void deleteQuestionMultiFillblankOptionMationById(InputObject inputObject, OutputObject outputObject) {
        examService.deleteQuestionMultiFillblankOptionMationById(inputObject, outputObject);
    }

    /**
     * 试卷发布
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/editExamStateToReleaseById")
    public void editExamStateToReleaseById(InputObject inputObject, OutputObject outputObject) {
        examService.editExamStateToReleaseById(inputObject, outputObject);
    }

    /**
     * 获取试卷题目信息用来生成html页面
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/queryExamMationByIdToHTML")
    public void queryExamMationByIdToHTML(InputObject inputObject, OutputObject outputObject) {
        examService.queryExamMationByIdToHTML(inputObject, outputObject);
    }

    /**
     * 删除试卷
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/deleteExamMationById")
    public void deleteExamMationById(InputObject inputObject, OutputObject outputObject) {
        examService.deleteExamMationById(inputObject, outputObject);
    }

    /**
     * 分析报告试卷
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/queryExamFxMationById")
    public void queryExamFxMationById(InputObject inputObject, OutputObject outputObject) {
        examService.queryExamFxMationById(inputObject, outputObject);
    }

    /**
     * 复制试卷
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/insertExamMationCopyById")
    public void insertExamMationCopyById(InputObject inputObject, OutputObject outputObject) {
        examService.insertExamMationCopyById(inputObject, outputObject);
    }

    /**
     * 判断此试卷当前的状态
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/queryAnswerExamMationByIp")
    public void queryAnswerExamMationByIp(InputObject inputObject, OutputObject outputObject) {
        examService.queryAnswerExamMationByIp(inputObject, outputObject);
    }

    /**
     * 用户回答试卷
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/insertAnswerExamMationByIp")
    public void insertAnswerExamMationByIp(InputObject inputObject, OutputObject outputObject) {
        examService.insertAnswerExamMationByIp(inputObject, outputObject);
    }

    /**
     * 手动结束试卷
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/updateExamMationEndById")
    public void updateExamMationEndById(InputObject inputObject, OutputObject outputObject) {
        examService.updateExamMationEndById(inputObject, outputObject);
    }

    /**
     * 获取答卷详情信息
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/queryExamAnswerMationByAnswerId")
    public void queryExamAnswerMationByAnswerId(InputObject inputObject, OutputObject outputObject) {
        examService.queryExamAnswerMationByAnswerId(inputObject, outputObject);
    }

    /**
     * 批阅试卷时获取答卷信息
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/queryExamAnswerMationToMarkingByAnswerId")
    public void queryExamAnswerMationToMarkingByAnswerId(InputObject inputObject, OutputObject outputObject) {
        examService.queryExamAnswerMationToMarkingByAnswerId(inputObject, outputObject);
    }

    /**
     * 批阅试卷提交答卷结果
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/insertExamAnswerResultMation")
    public void insertExamAnswerResultMation(InputObject inputObject, OutputObject outputObject) {
        examService.insertExamAnswerResultMation(inputObject, outputObject);
    }

    /**
     * 获取试卷详情信息
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/queryExamMationDetailById")
    public void queryExamMationDetailById(InputObject inputObject, OutputObject outputObject) {
        examService.queryExamMationDetailById(inputObject, outputObject);
    }

    /**
     * 获取试卷答题情况信息
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/queryExamAnswerMationDetailById")
    public void queryExamAnswerMationDetailById(InputObject inputObject, OutputObject outputObject) {
        examService.queryExamAnswerMationDetailById(inputObject, outputObject);
    }

    /**
     * 获取试卷详情信息以及阅卷人信息
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/queryExamAndMarkPeopleMationDetailById")
    public void queryExamAndMarkPeopleMationDetailById(InputObject inputObject, OutputObject outputObject) {
        examService.queryExamAndMarkPeopleMationDetailById(inputObject, outputObject);
    }

    /**
     * 修改阅卷人信息
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ExamController/editMarkPeopleMationDetailById")
    public void editMarkPeopleMationDetailById(InputObject inputObject, OutputObject outputObject) {
        examService.editMarkPeopleMationDetailById(inputObject, outputObject);
    }

}
