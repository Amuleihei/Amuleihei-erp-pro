package com.skyeye.school.interaction.controller;


import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.interaction.entity.QuestionCategories;
import com.skyeye.school.interaction.service.QuestionCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: questionCategoriesController
 * @Description: 互动答题题目类别管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/17 12:48
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "互动答题题目类别管理", tags = "互动答题题目类别管理", modelName = "互动答题题目类别管理")
public class QuestionCategoriesController {

    @Autowired
    private QuestionCategoriesService questionCategoriesService;

    /**
     * 新增/编辑题目类别信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeQuestionCategories", value = "新增/编辑题目类别信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = QuestionCategories.class)
    @RequestMapping("/post/QuestionCategoriesController/writeQuestionCategories")
    public void writeQuestionCategories(InputObject inputObject, OutputObject outputObject) {
        questionCategoriesService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 获取所有题目类别信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryQuestionCategoriesAllList", value = "新增/编辑题目类别信息", method = "GET", allUse = "2")
    @RequestMapping("/post/QuestionCategoriesController/queryQuestionCategoriesAllList")
    public void queryQuestionCategoriesAllList(InputObject inputObject, OutputObject outputObject) {
        questionCategoriesService.queryQuestionCategoriesAllList(inputObject, outputObject);
    }

    /**
     * 根据ID删除题目类别信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteQuestionCategoriesById", value = "根据ID删除题目类别信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/QuestionCategoriesController/deleteQuestionCategoriesById")
    public void deleteQuestionCategoriesById(InputObject inputObject, OutputObject outputObject) {
        questionCategoriesService.deleteById(inputObject, outputObject);
    }
}
