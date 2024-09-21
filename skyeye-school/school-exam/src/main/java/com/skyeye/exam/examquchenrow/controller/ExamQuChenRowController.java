package com.skyeye.exam.examquchenrow.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examquchenrow.entity.ExamQuChenRow;
import com.skyeye.exam.examquchenrow.service.ExamQuChenRowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "矩陈题-行选项管理", tags = "矩陈题-行选项管理", modelName = "矩陈题-行选项管理")
public class ExamQuChenRowController {

    @Autowired
    private ExamQuChenRowService examQuChenRowService;

    /**
     * 添加或修改矩陈题-行选项信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeExamQuChenRow", value = "新增/编辑矩陈题-行选项信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = ExamQuChenRow.class)
    @RequestMapping("/post/ExamQuChenRowController/writeExamQuChenRow")
    public void writeExamQuChenRow(InputObject inputObject, OutputObject outputObject) {
        examQuChenRowService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 获取矩陈题-行选项信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryExamQuChenRowList", value = "获取矩陈题-行选项信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ExamQuChenRowController/queryExamQuChenRowList")
    public void queryExamQuChenRowList(InputObject inputObject, OutputObject outputObject) {
        examQuChenRowService.queryPageList(inputObject, outputObject);
    }

    /**
     * 根据ID删除矩陈题-行选项信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteExamQuChenRowById", value = "根据ID删除矩陈题-行选项信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ExamQuChenRowController/deleteExamQuChenRowById")
    public void deleteExamQuChenRowById(InputObject inputObject, OutputObject outputObject) {
        examQuChenRowService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id获取矩陈题-行选项列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryExamQuChenRowListById", value = "根据id获取矩陈题-行选项列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ExamQuChenRowController/queryExamQuChenRowListById")
    public void queryExamQuChenRowListById(InputObject inputObject, OutputObject outputObject) {
        examQuChenRowService.queryExamQuChenRowListById(inputObject, outputObject);
    }

}
