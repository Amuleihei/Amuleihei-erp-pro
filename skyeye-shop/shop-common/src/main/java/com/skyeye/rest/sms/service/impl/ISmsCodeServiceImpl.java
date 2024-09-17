/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.rest.sms.service.impl;

import com.skyeye.base.rest.service.impl.IServiceImpl;
import com.skyeye.common.client.ExecuteFeignClient;
import com.skyeye.rest.sms.rest.ISmsCodeRest;
import com.skyeye.rest.sms.service.ISmsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ISmsCodeServiceImpl
 * @Description: 短信验证码服务接口实现类
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/16 16:27
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class ISmsCodeServiceImpl extends IServiceImpl implements ISmsCodeService {

    @Autowired
    private ISmsCodeRest iSmsCodeRest;

    @Override
    public void sendSmsCodeReq(String mobile, Integer scene) {
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("scene", scene);
        ExecuteFeignClient.get(() -> iSmsCodeRest.sendSmsCodeReq(params)).getRows();
    }

    @Override
    public void validateSmsCode(String mobile, String smsCode, Integer scene) {
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("smsCode", smsCode);
        params.put("scene", scene);
        ExecuteFeignClient.get(() -> iSmsCodeRest.validateSmsCode(params)).getRows();
    }
}
