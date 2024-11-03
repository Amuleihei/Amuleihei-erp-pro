package com.skyeye.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

import java.util.Map;

@Data
@TableName("shop_order_item")
@ApiModel("商品订单单子项管理实体类")
public class OrderItem extends OperatorUserInfo {


    @TableId("id")
    @ApiModelProperty(value = "主键id")
    private String id;

    @TableField("parent_id")
    @ApiModelProperty(value = "订单id")
    private String parentId;

    @TableField("material_id")
    @ApiModelProperty(value = "商品id", required = "required")
    private String materialId;

    @TableField(exist = false)
    @Property(value = "商品信息")
    private Map<String, Object> materialMation;

    @TableField("norms_id")
    @ApiModelProperty(value = "规格id", required = "required")
    private String normsId;

    @TableField(exist = false)
    @Property(value = "规格信息")
    private Map<String, Object> normsMation;

    @TableField("count")
    @ApiModelProperty(value = "购买数量", required = "required")
    private Integer count;

    @TableField("comment_state")
    @Property(value = "是否评价")
    private Integer commentState;

    @TableField("price")
    @Property(value = "商品原价（单），单位：分")
    private String price;

    @TableField("discount_price")
    @Property(value = "优惠金额（总），单位：分")
    private String discountPrice;

    @TableField("delivery_price")
    @Property(value = "运费金额（总），单位：分")
    private String deliveryPrice;

    @TableField("adjust_price")
    @ApiModelProperty(value = "订单调价（总），单位：分")
    private String adjustPrice;

    @TableField("pay_price")
    @Property(value = "应付金额（总），单位：分")
    private String payPrice;

    @TableField("coupon_id")
    @ApiModelProperty(value = "优惠券id")
    private String couponId;

    @TableField(exist = false)
    @Property(value = "优惠券信息")
    private Map<String, Object> couponMation;

    @TableField("coupon_price")
    @Property(value = "优惠劵减免金额，单位：分")
    private String couponPrice;

    @TableField("use_point")
    @ApiModelProperty(value = "使用的积分")
    private Integer usePoint;

    @TableField("point_price")
    @ApiModelProperty(value = "积分抵扣的金额，单位：分")
    private String pointPrice;

    @TableField("give_point")
    @ApiModelProperty(value = "赠送的积分")
    private Integer givePoint;

    @TableField("vip_price")
    @ApiModelProperty(value = "VIP 减免金额，单位：分")
    private String vipPrice;
}