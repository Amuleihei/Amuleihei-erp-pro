package com.skyeye.exam.examQuScore.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuScore.entity.ExamQuScore;
import com.skyeye.exam.examQuScore.service.ExamQuScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "评分题行选项管理", tags = "评分题行选项管理", modelName = "评分题行选项管理")
public class ExamQuScoreController {

    @Autowired
    private ExamQuScoreService examQuScoreService;

    /**
     * 新增或编辑评分题行选项
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeOrUpdateQuScore", value = "新增或编辑评分题行选项", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = ExamQuScore.class)
    @RequestMapping("/post/ExamQuScoreController/writeOrUpdateQuScore")
    public void writeOrUpdateQuScore(InputObject inputObject, OutputObject outputObject) {
        examQuScoreService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id查询评分题行选项信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryQuScoreById", value = "根据id查询评分题行选项信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "评分题行选项id", required = "required")
    })
    @RequestMapping("/post/ExamQuScoreController/queryQuScoreById")
    public void queryQuScoreById(InputObject inputObject, OutputObject outputObject) {
        examQuScoreService.selectById(inputObject, outputObject);
    }

    /**
     * 根据id删除评分题行选项信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteQuRadioById", value = "根据id删除评分题行选项信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "评分题行选项id", required = "required")
    })
    @RequestMapping("/post/ExamQuScoreController/deleteQuScoreById")
    public void deleteQuScoreById(InputObject inputObject, OutputObject outputObject) {
        examQuScoreService.deleteQuScoreById(inputObject, outputObject);
    }

    /**
     * 根据quId获取评分题行选项列表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryQuScoreListByQuId", value = "根据quId获取评分题行选项列表信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "quId", name = "quId", value = "问题id", required = "required")
    })
    @RequestMapping("/post/ExamQuScoreController/queryQuScoreListByQuId")
    public void queryQuScoreListByQuId(InputObject inputObject, OutputObject outputObject) {
        examQuScoreService.queryQuScoreListByQuId(inputObject, outputObject);
    }
}
