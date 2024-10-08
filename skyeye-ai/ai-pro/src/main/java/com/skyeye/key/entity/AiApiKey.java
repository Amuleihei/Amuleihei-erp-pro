/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.key.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: ShopDeliveryCompanyController
 * @Description: ai配置实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/10/8 10:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField
@TableName(value = "skyeye_ai_api_key")
@ApiModel("API配置")
public class AiApiKey extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "名称", required = "required", fuzzyLike = true)
    private String name;

    @TableField(value = "`remark`")
    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField(value = "`api_key`")
    @ApiModelProperty(value = "密钥")
    private String apiKey;

    @TableField(value = "`secret_key`")
    @ApiModelProperty(value = "secretKey")
    private String secretKey;

    @TableField(value = "`enabled`")
    @ApiModelProperty(value = "状态",required = "required")
    private String enabled;

    @TableField(value = "`platform`")
    @ApiModelProperty(value = "ai平台",required = "required")
    private String platform;

    @TableField(value = "`url`")
    @ApiModelProperty(value = "API 地址")
    private String url;

    @TableField(value = "`role_id`")
    @ApiModelProperty(value = "角色id")
    private String roleId;
}
