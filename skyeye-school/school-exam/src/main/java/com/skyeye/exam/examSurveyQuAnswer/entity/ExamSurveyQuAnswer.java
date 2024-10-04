package com.skyeye.exam.examSurveyQuAnswer.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

@Data
@RedisCacheField(name = "exam:quanswer")
@TableName(value = "exam_survey_qu_answer")
@ApiModel("答卷 题目和所得分数的关联表实体类")
public class ExamSurveyQuAnswer extends CommonInfo {

    @TableField("qu_id")
    @ApiModelProperty(value = "问题id", required = "required")
    private String quId;

    @TableField("answer_id")
    @ApiModelProperty(value = "答案id", required = "required")
    private String answerId;

    @TableField("fraction")
    @ApiModelProperty(value = "所得分数", required = "required")
    private Integer fraction;
}