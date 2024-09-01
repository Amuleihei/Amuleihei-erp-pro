/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.email.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.email.entity.EmailSendModel;
import com.skyeye.eve.email.service.EmailSendModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: EmailSendModelController
 * @Description: 邮件发送模板控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/10 8:39
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "邮件发送模板", tags = "邮件发送模板", modelName = "邮件发送模板")
public class EmailSendModelController {

    @Autowired
    private EmailSendModelService emailSendModelService;

    /**
     * 获取邮箱发送模板列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "emailsendmodel001", value = "获取邮箱发送模板列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/EmailSendModel/queryEmailSendModelList")
    public void queryEmailSendModelList(InputObject inputObject, OutputObject outputObject) {
        emailSendModelService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑邮件发送模板信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeEmailSendModel", value = "新增/编辑邮件发送模板信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = EmailSendModel.class)
    @RequestMapping("/post/EmailSendModel/writeEmailSendModel")
    public void writeEmailSendModel(InputObject inputObject, OutputObject outputObject) {
        emailSendModelService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id获取邮件模板详情
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryEmailSendById", value = "根据id获取邮件模板详情", method = "GET", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/EmailSendModel/queryEmailSendById")
    public void queryEmailSendById(InputObject inputObject, OutputObject outputObject) {
        emailSendModelService.selectById(inputObject, outputObject);
    }

    /**
     * 根据id删除该模板
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteEmailSendById", value = "根据id删除该模板", method = "DELETE", allUse = "1")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/EmailSendModel/deleteEmailSendById")
    public void deleteEmailSendById(InputObject inputObject, OutputObject outputObject) {
        emailSendModelService.deleteById(inputObject, outputObject);
    }

}
