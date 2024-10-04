package com.skyeye.exam.examQuRadio.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuRadio.entity.ExamQuRadio;
import com.skyeye.exam.examQuRadio.service.ExamQuRadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "单选题选项表管理", tags = "单选题选项表管理", modelName = "单选题选项表管理")
public class ExamQuRadioController {

    @Autowired
    private ExamQuRadioService examQuRadioService;

    /**
     * 新增或编辑单选题选项
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "exam010", value = "新增或编辑单选题选项", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = ExamQuRadio.class)
    @RequestMapping("/post/ExamQuRadioController/writeOrUpdateQuRadio")
    public void writeOrUpdateQuRadio(InputObject inputObject, OutputObject outputObject) {
        examQuRadioService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id查询单项选择题选项信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryQuRadioById", value = "根据id查询单项选择题选项信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "单项选择题选项id", required = "required")
    })
    @RequestMapping("/post/ExamQuRadioController/queryQuRadioById")
    public void queryQuRadioById(InputObject inputObject, OutputObject outputObject) {
        examQuRadioService.selectById(inputObject, outputObject);
    }

    /**
     * 根据id删除单项选择题选项信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteQuRadioById", value = "根据id删除单项选择题选项信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "单项选择题选项id", required = "required")
    })
    @RequestMapping("/post/ExamQuRadioController/deleteQuRadioById")
    public void deleteQuRadioById(InputObject inputObject, OutputObject outputObject) {
        examQuRadioService.deleteQuRadioById(inputObject, outputObject);
    }

    /**
     * 根据quId获取单项选择题选项列表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryQuRadioListByQuId", value = "根据quId获取单项选择题选项列表信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "quId", name = "quId", value = "问题id", required = "required")
    })
    @RequestMapping("/post/ExamQuRadioController/queryQuRadioListByQuId")
    public void queryQuRadioListByQuId(InputObject inputObject, OutputObject outputObject) {
        examQuRadioService.queryQuRadioListByQuId(inputObject, outputObject);
    }

}
