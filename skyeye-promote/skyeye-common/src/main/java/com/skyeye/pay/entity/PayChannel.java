/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pay.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import com.skyeye.pay.core.PayClientConfig;
import lombok.Data;

/**
 * @ClassName: PayChannel
 * @Description: 支付渠道实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/9 14:31
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField({"codeNum", "appId"})
@RedisCacheField(name = "skyeye:payChannel", cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@TableName(value = "skyeye_pay_channel", autoResultMap = true)
@ApiModel("支付渠道实体类")
public class PayChannel extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("code_num")
    @ApiModelProperty(value = "渠道编码，参考#PayType", required = "required")
    private String codeNum;

    @TableField("enabled")
    @ApiModelProperty(value = "启用状态", required = "required")
    private Integer enabled;

    @TableField("feeRate")
    @ApiModelProperty(value = "渠道费率，单位：百分比", required = "required")
    private Long feeRate;

    @TableField("app_id")
    @ApiModelProperty(value = "应用id", required = "required")
    private String appId;

    @TableField(exist = false)
    @Property("应用信息")
    private PayApp appMation;

    @TableField(value = "config", typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "支付渠道配置", required = "required")
    private PayClientConfig config;

    @TableField("remark")
    @ApiModelProperty(value = "备注")
    private String remark;
}
