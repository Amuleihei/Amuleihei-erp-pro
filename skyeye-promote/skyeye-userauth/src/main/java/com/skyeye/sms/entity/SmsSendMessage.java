/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.entity;

import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.KeyValue;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: SmsSendMessage
 * @Description: 短信发送消息
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 21:28
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
public class SmsSendMessage {

    @ApiModelProperty(value = "手机号", required = "required")
    private String mobile;

    @ApiModelProperty(value = "短信渠道编号", required = "required")
    private String channelId;

    @ApiModelProperty(value = "短信 API 的模板编号", required = "required")
    private String apiTemplateId;

    @ApiModelProperty(value = "短信模板参数")
    private List<KeyValue<String, Object>> templateParams;

}
