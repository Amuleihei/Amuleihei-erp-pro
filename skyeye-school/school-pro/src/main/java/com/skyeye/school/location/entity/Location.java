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
 * @ClassName: Location
 * @Description: 地点管理实体类
 * @author: skyeye云系列--lqy
 * @date: 2024/11/6 11:40
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName("school_location")
@ApiModel(description = "地点管理实体类")
public class Location extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("type_id")
    @ApiModelProperty(value = "地点类型id",required = "required")
    private String typeId;

    @TableField("name")
    @ApiModelProperty(value = "地点名称",required = "required")
    private String name;

    @TableField("nickname")
    @ApiModelProperty(value = "地点别名",required = "required")
    private String nickname;

    @TableField("longitude")
    @ApiModelProperty(value = "经度",required = "required")
    private Float longitude;

    @TableField("latitude")
    @ApiModelProperty(value = "纬度",required = "required")
    private Float latitude;

    @TableField("logo")
    @ApiModelProperty(value = "地点logo" ,required = "required")
    private String logo;

    @TableField("description")
    @ApiModelProperty(value = "地点描述")
    private String description;

    @TableField(exist = false)
    @Property("地点类型")
    private LocationType typeMation;
}
