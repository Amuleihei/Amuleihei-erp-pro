package com.skyeye.exam.examQuOrderby.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuOrderby.entity.ExamQuOrderby;
import com.skyeye.exam.examQuOrderby.service.ExamQuOrderbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "排序题行选项管理", tags = "排序题行选项管理", modelName = "排序题行选项管理")
public class ExamQuOrderbyController {

    @Autowired
    private ExamQuOrderbyService examQuOrderbyService;

    /**
     * 添加或修改排序题行选项信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeExamQuOrderby", value = "新增/编辑排序题行选项信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = ExamQuOrderby.class)
    @RequestMapping("/post/ExamQuOrderbyController/writeExamQuOrderby")
    public void writeExamQuOrderby(InputObject inputObject, OutputObject outputObject) {
        examQuOrderbyService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 获取排序题行选项信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryExamQuOrderbyList", value = "获取排序题行选项信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ExamQuOrderbyController/queryExamQuOrderbyList")
    public void queryExamQuOrderbyList(InputObject inputObject, OutputObject outputObject) {
        examQuOrderbyService.queryPageList(inputObject, outputObject);
    }

    /**
     * 根据ID删除排序题行选项信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteExamQuOrderbyById", value = "根据ID删除排序题行选项信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ExamQuOrderbyController/deleteExamQuOrderbyById")
    public void deleteExamQuOrderbyById(InputObject inputObject, OutputObject outputObject) {
        examQuOrderbyService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id获取排序题行选项列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryExamQuOrderbyListById", value = "根据id获取排序题行选项列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ExamQuOrderbyController/queryExamQuOrderbyListById")
    public void queryExamQuOrderbyListById(InputObject inputObject, OutputObject outputObject) {
        examQuOrderbyService.queryExamQuOrderbyListById(inputObject, outputObject);
    }
}
