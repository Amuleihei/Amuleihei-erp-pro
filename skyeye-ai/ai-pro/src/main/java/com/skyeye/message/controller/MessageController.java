/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.message.controller;

import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: MessageController
 * @Description: 消息控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/10/5 17:24
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "消息管理", tags = "消息管理", modelName = "消息管理")
public class MessageController {

    @Autowired
    private MessageService messageService;

 /*   @ApiOperation(id = "sendMessage", value = "发送消息", method = "POST", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "message", name = "message", value = "消息", required = "required")})
    @RequestMapping("/post/MessageController/sendMessage")
    public void sendMessage(InputObject inputObject, OutputObject outputObject) {
        messageService.sendMessage(inputObject, outputObject);
    }*/

    @ApiOperation(id = "sendMessage", value = "发送消息", method = "POST", allUse = "0")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "message", name = "message", value = "消息", required = "required")})
    @RequestMapping("/post/MessageController/sendMessage")
    public void sendMessage(InputObject inputObject, OutputObject outputObject) {
        messageService.sendMessage(inputObject, outputObject);
    }
}
