package com.skyeye.school.score.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

@Data
@RedisCacheField(name = "school:score")
@TableName(value = "school_score")
@ApiModel(value = "成绩实体类")
public class Score extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("no")
    @ApiModelProperty(value = "学号", required = "required")
    private String no;

    @TableField("class_id")
    @ApiModelProperty(value = "所属班级", required = "required")
    private String classId;

    @TableField("object_id")
    @ApiModelProperty(value = "科目id", required = "required")
    private String objectId;

    @TableField("semester_id")
    @ApiModelProperty(value = "学期id", required = "required")
    private String semesterId;

    @TableField("year")
    @ApiModelProperty(value = "学期id", required = "required")
    private Integer year;

    @TableField("grade")
    @ApiModelProperty(value = "成绩", required = "required")
    private Integer grade;

    @TableField("faculty_id")
    @ApiModelProperty(value = "院校id", required = "required")
    private String facultyId;

}
