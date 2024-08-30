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
import com.skyeye.store.entity.ShopArea;
import lombok.Data;

/**
 * @ClassName: MealAreaMation
 * @Description: 套餐使用区域实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/5 15:38
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "shop_meal_area")
@ApiModel("套餐使用区域实体类")
public class ShopMealArea extends CommonInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "meal_id")
    @Property(value = "套餐ID")
    private String mealId;

    @TableField(value = "area_id")
    @ApiModelProperty(value = "区域ID", required = "required")
    private String areaId;

    @TableField(exist = false)
    @Property(value = "区域信息")
    private ShopArea areaMation;

}
