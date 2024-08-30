/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.department.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.features.BaseGeneralInfo;
import com.skyeye.eve.entity.School;
import com.skyeye.school.faculty.entity.Faculty;
import com.skyeye.school.major.entity.Major;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: Department
 * @Description: 教研室实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/18 16:55
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField(value = {"facultyId", "name"})
@TableName(value = "school_department", autoResultMap = true)
@ApiModel(value = "教研室实体类")
public class Department extends BaseGeneralInfo {

    @TableField("school_id")
    @ApiModelProperty(value = "所属学校id", required = "required")
    private String schoolId;

    @TableField(exist = false)
    @Property(value = "所属学校信息")
    private School schoolMation;

    @TableField("faculty_id")
    @ApiModelProperty(value = "所属院系", required = "required")
    private String facultyId;

    @TableField(exist = false)
    @Property(value = "所属院系信息")
    private Faculty facultyMation;

    @TableField("theme_target")
    @ApiModelProperty(value = "主题及目标")
    private String themeTarget;

    @TableField("leader")
    @ApiModelProperty(value = "主要负责人id", required = "required")
    private String leader;

    @TableField(exist = false)
    @Property(value = "主要负责人信息")
    private Map<String, Object> leaderMation;

    @TableField("main_type")
    @ApiModelProperty(value = "主体类型，参考数据字典", required = "required")
    private String mainType;

    @TableField(value = "grand_major", typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "所授专业id", required = "json")
    private List<String> grandMajor;

    @TableField(exist = false)
    @Property(value = "所授专业信息")
    private List<Major> grandMajorMation;

    @TableField("img")
    @ApiModelProperty(value = "图片", required = "required")
    private String img;

}
