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
@TableName(value = "shop_delivery_company")
@ApiModel("快递公司管理")
public class Delivery extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "`code_num`")
    @ApiModelProperty(value = "快递公司 code",required = "required")
    private String codeNum;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "快递公司名称", required = "required")
    private String name;

    @TableField(value = "`logo`")
    @ApiModelProperty(value = "快递公司 logo",required = "required")
    private String logo;

    @TableField(value = "`remark`")
    @ApiModelProperty(value = "快递公司 code")
    private String remark;

    @TableField(value = "`enabled`")
    @ApiModelProperty(value = "状态",required = "required")
    private String enabled;

    @TableField(value = "`order_by`")
    @ApiModelProperty(value = "排序",required = "required")
    private String orderBy;

    @TableField(value = "`store_id`")
    @ApiModelProperty(value = "门店id",required = "required")
    private String storeId;
}
