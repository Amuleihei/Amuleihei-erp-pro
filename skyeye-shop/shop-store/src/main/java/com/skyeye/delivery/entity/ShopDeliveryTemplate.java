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
//@RedisCacheField(name = CacheConstants.SHOP_STORE_CACHE_KEY)  //开启缓存
@TableName(value = "shop_delivery_template")
@ApiModel("快递运费模版")
public class ShopDeliveryTemplate extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "模板名称",required = "required",fuzzyLike = true)
    private String name;

    @TableField(value = "`remark`")
    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField(value = "`type`")
    @ApiModelProperty(value = "配送计费方式",required = "required")
    private Integer type;

    @TableField(value = "`order_by`")
    @ApiModelProperty(value = "排序",required = "required")
    private Integer orderBy;

    @TableField(value = "`store_id`")
    @ApiModelProperty(value = "门店id",fuzzyLike = true)
    private String storeId;

}
