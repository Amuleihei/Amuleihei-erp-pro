package com.skyeye.exam.examSurveyMarkExam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

@Data
@RedisCacheField(name = "exam:markexam")
@TableName(value = "exam_survey_mark_exam")
@ApiModel("试卷与阅卷人关系表实体类")
public class ExamSurveyMarkExam extends CommonInfo {

    @TableField("survey_id")
    @ApiModelProperty(value = "试卷id", required = "required")
    private String surveyId;

    @TableField("user_id")
    @ApiModelProperty(value = "阅卷人id（用户id）", required = "required")
    private String userId;
}