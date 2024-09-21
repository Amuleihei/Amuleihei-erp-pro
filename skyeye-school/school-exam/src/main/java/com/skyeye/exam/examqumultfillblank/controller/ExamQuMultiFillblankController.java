package com.skyeye.exam.examqumultfillblank.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examqumultfillblank.entity.ExamQuMultiFillblank;
import com.skyeye.exam.examqumultfillblank.service.ExamQuMultiFillblankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "多行填空题管理", tags = "多行填空题管理", modelName = "多行填空题管理")
public class ExamQuMultiFillblankController {

    @Autowired
    private ExamQuMultiFillblankService examQuMultiFillblankService;

    /**
     * 添加或修改多行填空题信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeExamQuMultiFillblank", value = "新增/编辑多行填空题信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = ExamQuMultiFillblank.class)
    @RequestMapping("/post/ExamQuMultiFillblankController/writeExamQuMultiFillblank")
    public void writeExamQuMultiFillblank(InputObject inputObject, OutputObject outputObject) {
        examQuMultiFillblankService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 获取多行填空题信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryExamQuMultiFillblankList", value = "获取多行填空题信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ExamQuMultiFillblankController/queryExamQuMultiFillblankList")
    public void queryExamQuMultiFillblankList(InputObject inputObject, OutputObject outputObject) {
        examQuMultiFillblankService.queryPageList(inputObject, outputObject);
    }

    /**
     * 根据ID删除多行填空题信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteExamQuMultiFillblankById", value = "根据ID删除多行填空题信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ExamQuMultiFillblankController/deleteExamQuMultiFillblankById")
    public void deleteExamQuMultiFillblankById(InputObject inputObject, OutputObject outputObject) {
        examQuMultiFillblankService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id获取多行填空题列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryExamQuMultiFillblankListById", value = "根据id获取多行填空题列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ExamQuMultiFillblankController/queryExamQuMultiFillblankListById")
    public void queryExamQuMultiFillblankListById(InputObject inputObject, OutputObject outputObject) {
        examQuMultiFillblankService.queryExamQuMultiFillblankListById(inputObject, outputObject);
    }
}
