/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.meal.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.SkyeyeFlowable;
import com.skyeye.store.entity.ShopStore;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName: MealRefundOrder
 * @Description: 套餐退款订单实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/25 11:29
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "shop:mealRefundOrder", cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@TableName(value = "shop_meal_refund_order")
@ApiModel("套餐退款订单实体类")
public class MealRefundOrder extends SkyeyeFlowable {

    @TableField("meal_order_child_id")
    @ApiModelProperty(value = "套餐订单子订单id", required = "required")
    private String mealOrderChildId;

    @TableField("refund_reason_id")
    @ApiModelProperty(value = "退款原因id，参考数据字典", required = "required")
    private String refundReasonId;

    @TableField(exist = false)
    @Property(value = "退款原因信息")
    private Map<String, Object> refundReasonMation;

    @TableField("meal_single_price")
    @Property(value = "套餐耗费金额")
    private String mealSinglePrice;

    @TableField("refund_price")
    @Property(value = "退款金额")
    private String refundPrice;

    @TableField("store_id")
    @ApiModelProperty(value = "门店ID")
    private String storeId;

    @TableField(exist = false)
    @Property(value = "门店信息")
    private ShopStore storeMation;

    @TableField(value = "object_id", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "所属第三方业务数据id", required = "required")
    private String objectId;

    @TableField(value = "object_key", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "所属第三方业务数据的key", required = "required")
    private String objectKey;

    @TableField(exist = false)
    @Property(value = "适用对象信息")
    private Map<String, Object> objectMation;

}
