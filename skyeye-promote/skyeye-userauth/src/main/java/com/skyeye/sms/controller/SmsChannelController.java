/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.sms.service.SmsChannelService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
