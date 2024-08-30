package com.skyeye.exam.examQuChencolumn.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuChencolumn.entity.ExamQuChenColumn;
import com.skyeye.exam.examQuChencolumn.service.ExamQuChenColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "矩陈题-列选项管理", tags = "矩陈题-列选项管理", modelName = "矩陈题-列选项管理")
public class ExamQuChenColumnController {

    @Autowired
    private ExamQuChenColumnService examQuChenColumnService;

    /**
     * 添加或修改矩陈题-列选项保存表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeExamQuChenColumn", value = "新增/编辑矩陈题-列选项保存表信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = ExamQuChenColumn.class)
    @RequestMapping("/post/ExamQuChenColumnController/writeExamQuChenColumn")
    public void writeExamQuChenColumn(InputObject inputObject, OutputObject outputObject) {
        examQuChenColumnService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 获取矩陈题-列选项保存表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryExamQuChenColumnList", value = "获取矩陈题-列选项保存表信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ExamQuChenColumnController/queryExamQuChenColumnList")
    public void queryExamQuChenColumnList(InputObject inputObject, OutputObject outputObject) {
        examQuChenColumnService.queryPageList(inputObject, outputObject);
    }

    /**
     * 根据ID删除矩陈题-列选项信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteExamQuChenColumnById", value = "根据ID删除矩陈题-列选项信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ExamQuChenColumnController/deleteExamQuChenColumnById")
    public void deleteExamQuChenColumnById(InputObject inputObject, OutputObject outputObject) {
        examQuChenColumnService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id获取矩陈题-列选项列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryExamQuChenColumnListById", value = "根据id获取矩陈题-列选项列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ExamQuChenColumnController/queryExamQuChenColumnListById")
    public void queryExamQuChenColumnListById(InputObject inputObject, OutputObject outputObject) {
        examQuChenColumnService.queryExamQuChenColumnListById(inputObject, outputObject);
    }
}
