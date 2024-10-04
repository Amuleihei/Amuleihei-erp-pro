package com.skyeye.exam.examquchenoption.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examquchenoption.entity.ExamQuChenOption;
import com.skyeye.exam.examquchenoption.service.ExamQuChenOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "矩陈题-题选项管理", tags = "矩陈题-题选项管理", modelName = "矩陈题-题选项管理")
public class ExamQuChenOptionController {

    @Autowired
    private ExamQuChenOptionService examQuChenOptionService;

    /**
     * 添加或修改矩陈题-题选项信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeExamQuChenOption", value = "新增/编辑矩陈题-题选项信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = ExamQuChenOption.class)
    @RequestMapping("/post/ExamQuChenOptionController/writeExamQuChenOption")
    public void writeExamQuChenOption(InputObject inputObject, OutputObject outputObject) {
        examQuChenOptionService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 获取矩陈题-题选项信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryExamQuChenOptionList", value = "获取矩陈题-题选项信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ExamQuChenOptionController/queryExamQuChenOptionList")
    public void queryExamQuChenOptionList(InputObject inputObject, OutputObject outputObject) {
        examQuChenOptionService.queryPageList(inputObject, outputObject);
    }

    /**
     * 根据ID删除矩陈题-题选项信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteExamQuChenOptionById", value = "根据ID删除矩陈题-题选项信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ExamQuChenOptionController/deleteExamQuChenOptionById")
    public void deleteExamQuChenOptionById(InputObject inputObject, OutputObject outputObject) {
        examQuChenOptionService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id获取矩陈题-题选项列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryExamQuChenOptionListById", value = "根据id获取矩陈题-题选项列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ExamQuChenOptionController/queryExamQuChenOptionListById")
    public void queryExamQuChenOptionListById(InputObject inputObject, OutputObject outputObject) {
        examQuChenOptionService.queryExamQuChenOptionListById(inputObject, outputObject);
    }

}
