/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.sms.entity.SmsTemplate;

import java.util.Map;

/**
 * @ClassName: SmsTemplateService
 * @Description: 短信模板服务接口
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 22:29
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SmsTemplateService extends SkyeyeBusinessService<SmsTemplate> {

    SmsTemplate selectByCodeNum(String codeNum);

    String formatSmsTemplateContent(String content, Map<String, Object> params);

}
