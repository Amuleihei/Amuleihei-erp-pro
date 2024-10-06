/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.message.service.impl;

import com.baidubce.qianfan.Qianfan;
import com.baidubce.qianfan.core.builder.ChatBuilder;
import com.baidubce.qianfan.model.chat.ChatResponse;
import com.skyeye.ai.core.enums.AiPlatformEnum;
import com.skyeye.ai.core.factory.AiFactory;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName: MessageServiceImpl
 * @Description: 消息服务实现类
 * @author: skyeye云系列--卫志强
 * @date: 2024/10/5 17:25
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "消息管理", groupName = "消息管理")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private AiFactory aiFactory;

    @Override
    public void sendMessage(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String message = params.get("message").toString();
        Qianfan qianfan = (Qianfan) aiFactory.getDefaultChatModel(AiPlatformEnum.YI_YAN);
        ChatBuilder bulder = qianfan.chatCompletion()
            .model("ERNIE-Speed-8K");//你要使用的大模型款式，最好和我一样，其他的很有可能是收费的
        bulder.addMessage("user", message);//你的问题
        ChatResponse response = bulder.execute();
        outputObject.setBean(response);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

}
