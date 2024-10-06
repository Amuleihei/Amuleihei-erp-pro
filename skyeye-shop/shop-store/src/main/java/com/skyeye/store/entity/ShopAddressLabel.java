package com.skyeye.store.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

@Data
@TableName(value = "shop_address_label")
@ApiModel("收件地址标签管理实体类")
public class ShopAddressLabel extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("content")
    @ApiModelProperty(value = "标签内容", required = "required", fuzzyLike = true)
    private String content;
}
