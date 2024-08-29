/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.sms.core.service.SmsClient;
import com.skyeye.sms.entity.SmsChannel;

/**
 * @ClassName: SmsChannelService
 * @Description: 短信渠道服务接口
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 22:20
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SmsChannelService extends SkyeyeBusinessService<SmsChannel> {

    SmsClient getSmsClient(Long channelId);
}
