/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.sms.entity.SmsChannel;
import com.skyeye.sms.service.SmsChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SmsChannelController
 * @Description: 短信渠道控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 22:23
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "短信渠道", tags = "短信渠道", modelName = "短信渠道")
public class SmsChannelController {

    @Autowired
    private SmsChannelService smsChannelService;

    @ApiOperation(id = "querySmsChannelList", value = "获取短信渠道列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/SmsChannelController/querySmsChannelList")
    public void querySmsChannelList(InputObject inputObject, OutputObject outputObject) {
        smsChannelService.queryPageList(inputObject, outputObject);
    }

    /**
     * 获取全部已启用的短信渠道信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAllEnabledList", value = "获取全部已启用的短信渠道信息", method = "POST", allUse = "2")
    @RequestMapping("/post/SmsChannelController/SmsChannelController")
    public void queryAllEnabledList(InputObject inputObject, OutputObject outputObject) {
        smsChannelService.queryList(inputObject, outputObject);
    }

    @ApiOperation(id = "writeSmsChannel", value = "新增/编辑短信渠道", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SmsChannel.class)
    @RequestMapping("/post/SmsChannelController/writeSmsChannel")
    public void writeSmsChannel(InputObject inputObject, OutputObject outputObject) {
        smsChannelService.saveOrUpdateEntity(inputObject, outputObject);
    }

    @ApiOperation(id = "deleteSmsChannelById", value = "删除短信渠道", method = "DELETE", allUse = "1")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SmsChannelController/deleteSmsChannelById")
    public void deleteSmsChannelById(InputObject inputObject, OutputObject outputObject) {
        smsChannelService.deleteById(inputObject, outputObject);
    }

    @ApiOperation(id = "queryEnabledSmsChannelList", value = "获取所有启动的短信渠道列表", method = "GET", allUse = "2")
    @RequestMapping("/post/SmsChannelController/queryEnabledSmsChannelList")
    public void queryEnabledSmsChannelList(InputObject inputObject, OutputObject outputObject) {
        smsChannelService.queryEnabledSmsChannelList(inputObject, outputObject);
    }

}
