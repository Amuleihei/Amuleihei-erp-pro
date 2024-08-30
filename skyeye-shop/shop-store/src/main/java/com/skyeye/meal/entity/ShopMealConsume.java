/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.meal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: MealConsumeMation
 * @Description: 套餐耗材实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/5 15:20
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "shop_meal_consume")
@ApiModel("套餐耗材实体类")
public class ShopMealConsume extends CommonInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "meal_id")
    @Property(value = "套餐ID")
    private String mealId;

    @TableField(value = "material_id")
    @ApiModelProperty(value = "商品ID")
    private String materialId;

    @TableField(value = "consume_explain")
    @ApiModelProperty(value = "耗材说明", required = "required")
    private String consumeExplain;

}
