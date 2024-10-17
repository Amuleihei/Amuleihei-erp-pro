/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.message.service.impl;

import cn.hutool.json.JSONUtil;
import com.baidubce.qianfan.Qianfan;
import com.baidubce.qianfan.core.StreamIterator;
import com.baidubce.qianfan.core.builder.ChatBuilder;
import com.baidubce.qianfan.model.completion.CompletionResponse;
import com.skyeye.ai.core.config.SkyeyeAiProperties;
import com.skyeye.ai.core.enums.AiPlatformEnum;
import com.skyeye.ai.core.factory.AiFactory;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.message.service.MessageService;
import com.skyeye.websocket.AiMessageWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

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

    @Autowired
    private SkyeyeAiProperties skyeyeAiProperties;

    @Autowired
    private Executor messageStreamExecutor;

    @Autowired
    private AiMessageWebSocket aiMessageWebSocket;

    @Override
    public void sendMessage(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String content = params.get("content").toString();
        Qianfan qianfan = (Qianfan) aiFactory.getDefaultChatModel(AiPlatformEnum.YI_YAN);
        ChatBuilder bulder = qianfan.chatCompletion()
            //你要使用的大模型款式，最好和我一样，其他的很有可能是收费的
            .model("ERNIE-Speed-8K")
            .addMessage("user", content);
        bulder.addMessage("user", content);//你的问题
//        ChatResponse response = bulder.execute();
//        outputObject.setBean(response);
//        outputObject.settotal(CommonNumConstants.NUM_ONE)
        try (StreamIterator<CompletionResponse> response = qianfan
            .completion()
            .model("CodeLlama-7b-Instruct")
            .prompt(content)
            .executeStream()) {
            while (response.hasNext()) {
//                outputObject.setBean(response.next().getResult());
                System.out.println(response.next().getResult());
            }
        }
    }

    @Override
    public void sendMessageStream(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String content = params.get("content").toString();
        String userId = inputObject.getLogParams().get("id").toString();
        messageStreamExecutor.execute(() -> {
            // 使用安全认证AK/SK鉴权参数，安全认证Access Key替换your_iam_ak，Secret Key替换your_iam_sk
            Qianfan qianfan = (Qianfan) aiFactory.getDefaultChatModel(AiPlatformEnum.YI_YAN);

            qianfan.chatCompletion()
                .model("ERNIE-Speed-8K")
                .addMessage("user", content)
                // 启用流式返回
                .executeStream()
                .forEachRemaining(chunk -> {
                    Map<String, Object> messageMap = new HashMap<>();
                    messageMap.put("message", chunk.getResult());
                    messageMap.put("end", chunk.getEnd());
                    messageMap.put("orderBy", chunk.getSentenceId());
                    aiMessageWebSocket.sendMessageTo(JSONUtil.toJsonStr(messageMap), userId);
                });
        });
    }
}

