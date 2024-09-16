/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.rest.sms.rest;

import com.skyeye.common.client.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @ClassName: ISmsCodeRest
 * @Description: 短信验证码接口
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/16 16:27
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@FeignClient(value = "${webroot.skyeye-pro}", configuration = ClientConfiguration.class)
public interface ISmsCodeRest {

    @PostMapping("/sendSmsCodeReq")
    String sendSmsCodeReq(Map<String, Object> params);

    @PostMapping("/validateSmsCode")
    String validateSmsCode(Map<String, Object> params);

}
