package com.skyeye.exam.examquchckbox.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examquchckbox.entity.ExamQuCheckbox;
import com.skyeye.exam.examquchckbox.service.ExamQuCheckboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "多选题选项表管理", tags = "多选题选项表管理", modelName = "多选题选项表管理")
public class ExamQuCheckboxController {

    @Autowired
    private ExamQuCheckboxService examQuCheckboxService;

//    /**
//     * 添加或修改多选题选项表保存表信息
//     *
//     * @param inputObject  入参以及用户信息等获取对象
//     * @param outputObject 出参以及提示信息的返回值对象
//     */
//    @ApiOperation(id = "writeExamQuCheckbox", value = "新增/编辑多选题选项表保存表信息", method = "POST", allUse = "1")
//    @ApiImplicitParams(classBean = ExamQuCheckbox.class)
//    @RequestMapping("/post/ExamQuCheckboxController/writeExamQuCheckbox")
//    public void writeExamMailInviteInbox(InputObject inputObject, OutputObject outputObject) {
//        examQuCheckboxService.saveOrUpdateEntity(inputObject, outputObject);
//    }
//
//    /**
//     * 获取多选题选项表信息
//     *
//     * @param inputObject  入参以及用户信息等获取对象
//     * @param outputObject 出参以及提示信息的返回值对象
//     */
//    @ApiOperation(id = "queryExamQuCheckboxList", value = "获取多选题选项表信息", method = "POST", allUse = "1")
//    @ApiImplicitParams(classBean = CommonPageInfo.class)
//    @RequestMapping("/post/ExamQuCheckboxController/queryExamQuCheckboxList")
//    public void queryExamQuCheckboxList(InputObject inputObject, OutputObject outputObject) {
//        examQuCheckboxService.queryPageList(inputObject, outputObject);
//    }
//
//    /**
//     * 根据ID删除多选题选项表信息
//     *
//     * @param inputObject  入参以及用户信息等获取对象
//     * @param outputObject 出参以及提示信息的返回值对象
//     */
//    @ApiOperation(id = "deleteExamQuCheckboxById", value = "根据ID删除多选题选项表信息", method = "DELETE", allUse = "1")
//    @ApiImplicitParams({
//            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
//    @RequestMapping("/post/ExamQuCheckboxController/deleteExamQuCheckboxById")
//    public void deleteExamQuCheckboxById(InputObject inputObject, OutputObject outputObject) {
//        examQuCheckboxService.deleteById(inputObject, outputObject);
//    }
//
//    /**
//     * 根据id获取多选题选项表列表
//     *
//     * @param inputObject  入参以及用户信息等获取对象
//     * @param outputObject 出参以及提示信息的返回值对象
//     */
//    @ApiOperation(id = "queryExamQuCheckboxListById", value = "根据id获取多选题选项表列表", method = "GET", allUse = "2")
//    @ApiImplicitParams({
//            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
//    @RequestMapping("/post/ExamQuCheckboxController/queryExamQuCheckboxListById")
//    public void queryExamQuCheckboxListById(InputObject inputObject, OutputObject outputObject) {
//        examQuCheckboxService.queryExamQuCheckboxListById(inputObject, outputObject);
//    }
}
