/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.EmailUser;
import com.skyeye.eve.service.EmailUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: EmailUserController
 * @Description: 用户绑定的邮箱控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/9 8:28
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "用户绑定的邮箱", tags = "用户绑定的邮箱", modelName = "用户绑定的邮箱")
public class EmailUserController {

    @Autowired
    private EmailUserService emailUserService;

    /**
     * 根据用户获取该用户绑定的邮箱信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryEmailListByUserId", value = "获取指定年度的客户新增量，联系人新增量", method = "GET", allUse = "2")
    @RequestMapping("/post/EmailUserController/queryEmailListByUserId")
    public void queryEmailListByUserId(InputObject inputObject, OutputObject outputObject) {
        emailUserService.queryEmailListByUserId(inputObject, outputObject);
    }

    /**
     * 添加邮箱信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "createEmailUser", value = "添加邮箱信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = EmailUser.class)
    @RequestMapping("/post/EmailUserController/createEmailUser")
    public void createEmailUser(InputObject inputObject, OutputObject outputObject) {
        emailUserService.createEntity(inputObject, outputObject);
    }

    /**
     * 从服务器上获取收件箱里的邮件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "useremail003", value = "从服务器上获取收件箱里的邮件", method = "GET", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "emailUserId", name = "emailUserId", value = "用户绑定的邮箱id", required = "required")})
    @RequestMapping("/post/EmailUserController/insertEmailListFromServiceByUserId")
    public void insertEmailListFromServiceByUserId(InputObject inputObject, OutputObject outputObject) {
        emailUserService.insertEmailListFromServiceByUserId(inputObject, outputObject);
    }

    /**
     * 从服务器上获取已发送邮件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "useremail006", value = "从服务器上获取已发送邮件", method = "GET", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "emailUserId", name = "emailUserId", value = "用户绑定的邮箱id", required = "required")})
    @RequestMapping("/post/EmailUserController/insertSendedEmailListFromServiceByUserId")
    public void insertSendedEmailListFromServiceByUserId(InputObject inputObject, OutputObject outputObject) {
        emailUserService.insertSendedEmailListFromServiceByUserId(inputObject, outputObject);
    }

    /**
     * 从服务器上获取已删除邮件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "useremail008", value = "从服务器上获取已删除邮件", method = "GET", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "emailUserId", name = "emailUserId", value = "用户绑定的邮箱id", required = "required")})
    @RequestMapping("/post/EmailUserController/insertDelsteEmailListFromServiceByUserId")
    public void insertDelsteEmailListFromServiceByUserId(InputObject inputObject, OutputObject outputObject) {
        emailUserService.insertDelsteEmailListFromServiceByUserId(inputObject, outputObject);
    }

    /**
     * 从服务器上获取草稿箱邮件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "useremail010", value = "从服务器上获取草稿箱邮件", method = "GET", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "emailUserId", name = "emailUserId", value = "用户绑定的邮箱id", required = "required")})
    @RequestMapping("/post/EmailUserController/insertDraftsEmailListFromServiceByUserId")
    public void insertDraftsEmailListFromServiceByUserId(InputObject inputObject, OutputObject outputObject) {
        emailUserService.insertDraftsEmailListFromServiceByUserId(inputObject, outputObject);
    }

}
