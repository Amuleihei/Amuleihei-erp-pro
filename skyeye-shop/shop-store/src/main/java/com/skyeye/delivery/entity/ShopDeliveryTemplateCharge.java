package com.skyeye.delivery.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

@Data
@UniqueField
@TableName(value = "shop_delivery_template_charge")
@ApiModel("快递公司管理")
public class ShopDeliveryTemplateCharge extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "`template_id`")
    @ApiModelProperty(value = "模板ID",required = "required")
    private String templateId;

    @TableField(value = "`area_id`")
    @ApiModelProperty(value = "区域id，集合",required = "required")
    private String areaId;

    @TableField(value = "`start_count`")
    @ApiModelProperty(value = "首件数量(件数,重量，或体积)")
    private Double startCount;

    @TableField(value = "`start_price`")
    @ApiModelProperty(value = "起步价，单位：分")
    private String startPrice;

    @TableField(value = "`extra_count`")
    @ApiModelProperty(value = "续件数量(件, 重量，或体积)")
    private Double extraCount;

    @TableField(value = "`extra_price`")
    @ApiModelProperty(value = "额外价，单位：分")
    private String extraPrice;
}
