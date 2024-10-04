package com.skyeye.exam.examSurveyClass.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.entity.CommonInfo;
import com.skyeye.common.entity.features.BaseGeneralInfo;
import lombok.Data;

@Data
@RedisCacheField(name = "exam:class")
@TableName(value = "exam_survey_class")
@ApiModel("试卷与班级关系表实体类")
public class ExamSurveyClass extends CommonInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("class_id")
    @ApiModelProperty(value = "班级id", required = "required")
    private String classId;

    @TableField("exam_survey_id")
    @ApiModelProperty(value = "试卷id", required = "required")
    private String examSurveyId;
}