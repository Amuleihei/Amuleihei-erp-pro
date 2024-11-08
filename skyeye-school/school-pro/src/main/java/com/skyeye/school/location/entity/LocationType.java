package com.skyeye.school.location.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: LocationType
 * @Description: 地点分类管理实体类
 * @author: skyeye云系列--lqy
 * @date: 2024/11/6 11:40
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "school_location_type")
@ApiModel(value = "地点分类管理实体类")
public class LocationType extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("name")
    @ApiModelProperty(value = "地点分类名称",required = "required")
    private String name;

    @TableField("description")
    @ApiModelProperty(value = "地点分类描述")
    private String description;

}
