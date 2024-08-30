/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.UserMessageBox;
import com.skyeye.eve.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserMessageController
 * @Description: 用户消息管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/1/31 21:35
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@RestController
@Api(value = "用户消息管理", tags = "用户消息管理", modelName = "内部消息模块")
public class UserMessageController {

    @Autowired
    private UserMessageService userMessageService;

    /**
     * 获取当前用户前8条未读的消息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "getTopEightMessageList", value = "获取当前用户前8条未读的消息列表", method = "POST", allUse = "2")
    @RequestMapping("/post/UserMessageController/getTopEightMessageList")
    public void getTopEightMessageList(InputObject inputObject, OutputObject outputObject) {
        userMessageService.getTopEightMessageList(inputObject, outputObject);
    }

    /**
     * 分页查询当前用户的消息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryUserMessageList", value = "分页查询当前用户的消息列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/UserMessageController/queryUserMessageList")
    public void getAllNoticeListByUserId(InputObject inputObject, OutputObject outputObject) {
        userMessageService.queryPageList(inputObject, outputObject);
    }

    /**
     * 用户阅读消息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "editMessageById", value = "用户阅读消息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/UserMessageController/editMessageById")
    public void editMessageById(InputObject inputObject, OutputObject outputObject) {
        userMessageService.editMessageById(inputObject, outputObject);
    }

    /**
     * 删除消息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteMessageById", value = "删除消息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/UserMessageController/deleteMessageById")
    public void deleteMessageById(InputObject inputObject, OutputObject outputObject) {
        userMessageService.deleteById(inputObject, outputObject);
    }

    /**
     * 用户删除全部消息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteAllMessage", value = "用户删除全部消息", method = "DELETE", allUse = "2")
    @RequestMapping("/post/UserMessageController/deleteAllMessage")
    public void deleteAllMessage(InputObject inputObject, OutputObject outputObject) {
        userMessageService.deleteAllMessage(inputObject, outputObject);
    }

    /**
     * 新增用户消息数据---给其他微服务调用
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertUserMessage", value = "新增用户消息数据---给其他微服务调用", method = "POST", allUse = "0")
    @ApiImplicitParams(classBean = UserMessageBox.class)
    @RequestMapping("/post/UserMessageController/insertUserMessage")
    public void insertUserMessage(InputObject inputObject, OutputObject outputObject) {
        userMessageService.insertUserMessage(inputObject, outputObject);
    }

}
