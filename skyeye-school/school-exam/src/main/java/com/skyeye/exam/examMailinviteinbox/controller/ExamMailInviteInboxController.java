package com.skyeye.exam.examMailinviteinbox.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examMailinviteinbox.entity.ExamMailInviteInbox;
import com.skyeye.exam.examMailinviteinbox.service.ExamMailInviteInboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "是非题结果保存表管理", tags = "是非题结果保存表管理", modelName = "是非题结果保存表管理")
public class ExamMailInviteInboxController {

    @Autowired
    private ExamMailInviteInboxService examMailInviteInboxService;

    /**
     * 添加或修改是非题结果保存表保存表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeExamMailInviteInbox", value = "新增/编辑是非题结果保存表保存表信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = ExamMailInviteInbox.class)
    @RequestMapping("/post/ExamMailInviteInboxController/writeExamMailInviteInbox")
    public void writeExamMailInviteInbox(InputObject inputObject, OutputObject outputObject) {
        examMailInviteInboxService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 获取是非题结果保存表信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryExamMailInviteInboxList", value = "获取是非题结果保存表信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ExamMailInviteInboxController/queryExamMailInviteInboxList")
    public void queryExamMailInviteInboxList(InputObject inputObject, OutputObject outputObject) {
        examMailInviteInboxService.queryPageList(inputObject, outputObject);
    }

    /**
     * 删除是非题结果保存表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteExamMailInviteInboxById", value = "根据ID删除是非题结果保存表信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ExamMailInviteInboxController/deleteExamMailInviteInboxById")
    public void deleteExamMailInviteInboxById(InputObject inputObject, OutputObject outputObject) {
        examMailInviteInboxService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id获取是非题结果保存表列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryExamMailInviteInboxListById", value = "根据id获取是非题结果保存表列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ExamMailInviteInboxController/queryExamMailInviteInboxListById")
    public void queryExamMailInviteInboxListById(InputObject inputObject, OutputObject outputObject) {
        examMailInviteInboxService.queryExamMailInviteInboxListById(inputObject, outputObject);
    }

}
