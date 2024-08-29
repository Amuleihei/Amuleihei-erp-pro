/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.features.BaseGeneralInfo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: SmsTemplate
 * @Description: 短信模板
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 21:53
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "skyeye_sms_template", autoResultMap = true)
@ApiModel("短信模板")
public class SmsTemplate extends BaseGeneralInfo {

    @TableField("type")
    @ApiModelProperty(value = "短信类型，参考#SmsTemplateTypeEnum", required = "required")
    private Integer type;

    @TableField("enabled")
    @ApiModelProperty(value = "状态，参考#EnableEnum枚举类", required = "required,num")
    private Integer enabled;

    @TableField("code_num")
    @ApiModelProperty(value = "模板编码，保证唯一", required = "required")
    private String codeNum;

    @TableField("content")
    @ApiModelProperty(value = "模板内容。内容的参数，使用 {} 包括，例如说 {name}", required = "required")
    private String content;

    @TableField(value = "params", typeHandler = JacksonTypeHandler.class)
    @Property(value = "参数数组(自动根据内容生成)")
    private List<String> params;

    @TableField("api_template_id")
    @ApiModelProperty(value = "短信 API 的模板编号", required = "required")
    private String apiTemplateId;

    @TableField(value = "channel_id")
    @ApiModelProperty(value = "短信渠道id", required = "required")
    private String channelId;

    @TableField(exist = false)
    @Property(value = "短信渠道信息")
    private SmsChannel channelMation;

}
