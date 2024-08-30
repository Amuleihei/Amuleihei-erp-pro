/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.entity;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("短信验证码的使用 Request")
public class SmsCodeUseReq implements Serializable {

    @ApiModelProperty(value = "手机号", required = "required,phone")
    private String mobile;

    @ApiModelProperty(value = "发送场景，参考#SmsSceneEnum", required = "required")
    private Integer scene;

    @ApiModelProperty(value = "验证码", required = "required")
    private String code;

}
