/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: CouponMaterial
 * @Description: 优惠券/模版适用商品对象管理实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/10/23 10:39
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName("shop_coupon_material")
@ApiModel(value = "优惠券/模版适用商品对象管理实体类")
public class CouponMaterial extends CommonInfo {

    @TableId("id")
    @Property("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "material_id")
    @ApiModelProperty(value = "商品id")
    private String materialId;

    @TableField(value = "coupon_id")
    @Property(value = "优惠券/模版id")
    private String couponId;
}
