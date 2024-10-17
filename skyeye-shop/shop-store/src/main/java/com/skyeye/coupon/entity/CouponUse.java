package com.skyeye.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

import java.util.List;

@Data
@TableName(value = "shop_coupon_use")
@ApiModel(value = "优惠券领取信息管理实体类")
public class CouponUse extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "coupon_id")
    @ApiModelProperty(value = "优惠券id", required = "required")
    private String couponId;

    @TableField(value = "state")
    @Property(value = "状态")
    private Integer state;

    @TableField(value = "task_type")
    @ApiModelProperty(value = "领取类型", required = "required")
    private Integer taskType;

    @TableField(value = "use_price")
    @Property(value = "是否设置满多少金额可用，单位：分。0 - 不限制，大于 0 - 多少金额可用")
    private String usePrice;

    @TableField(value = "product_scope")
    @Property(value = "商品范围")
    private Integer productScope;

    @TableField(value = "valid_start_time")
    @Property(value = "生效开始时间")
    private String validStartTime;

    @TableField(value = "valid_end_time")
    @Property(value = "生效结束时间")
    private String validEndTime;

    @TableField(value = "discount_type")
    @Property(value = "折扣类型")
    private Integer discountType;

    @TableField(value = "discount_percent")
    @Property(value = "折扣百分比，例如，80% 为 80")
    private Integer discountPercent;

    @TableField(value = "discount_price")
    @Property(value = "优惠金额，单位：分")
    private String discountPrice;

    @TableField(value = "discount_limit_price")
    @Property(value = "折扣上限,百分比折扣也受其越约束")
    private String discountLimitPrice;

    @TableField(value = "use_order_id")
    @ApiModelProperty(value = "使用订单id")
    private String useOrderId;

    @TableField(value = "use_time")
    @ApiModelProperty(value = "使用时间")
    private String useTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "用户领取的优惠券适用对象列表")
    private List<CouponUseMaterial> couponUseMaterialList;
}
