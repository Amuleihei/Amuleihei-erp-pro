package com.skyeye.school.location.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: LocationService
 * @Description: 地点服务管理实体类
 * @author: skyeye云系列--lqy
 * @date: 2024/11/6 11:40
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */

@Data
@TableName("school_location_service")
@ApiModel(description = "地点服务管理实体类")
public class LocationServe extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("floor_id")
    @ApiModelProperty(value = "楼层教室id",required = "required")
    private String floorId;

    @TableField("service_name")
    @ApiModelProperty(value = "服务名称",required = "required")
    private String serviceName;

    @TableField("description")
    @ApiModelProperty(value = "描述")
    private String description;

    @TableField("logo")
    @ApiModelProperty(value = "logo图片",required = "required")
    private String logo;

    @TableField(exist = false)
    @Property("楼层教室")
    private Floor floorMation;
}
