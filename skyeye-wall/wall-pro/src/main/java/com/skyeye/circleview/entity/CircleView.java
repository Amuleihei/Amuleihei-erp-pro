/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.circleview.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: Circle
 * @Description: 圈子实体层
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/9 14:31
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "wall_circle_view")
@ApiModel(value = "圈子浏览记录实体层")
public class CircleView extends CommonInfo {

    @TableId("id")
    @ApiModelProperty("主键id")
    private String id;

    @TableField("create_id")
    @ApiModelProperty(value = "创建人id")
    private String createId;

    @TableField("circle_id")
    @ApiModelProperty(value = "圈子id", required = "required")
    private String circleId;

}