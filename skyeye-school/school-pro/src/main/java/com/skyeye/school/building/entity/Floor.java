package com.skyeye.school.building.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: Floor
 * @Description: 楼层教室管理实体类
 * @author: skyeye云系列--lqy
 * @date: 2024/11/6 11:40
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName("school_floor")
@ApiModel(description = "楼层教室管理实体类")
public class Floor extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id，为空时新增，不为空时编辑")
    private String id;

    @TableField("location_id")
    @ApiModelProperty(value = "所属地点id",required = "required")
    private String locationId;

    @TableField("floor_num")
    @ApiModelProperty(value = "楼层号",required = "required")
    private String floorNum;

    @TableField("class_num")
    @ApiModelProperty(value = "教室号",required = "required")
    private String classNum;
}
