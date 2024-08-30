package com.skyeye.school.interaction.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: questionStuRecord
 * @Description: 互动答题学生答题记录实体类
 * @author: skyeye云系列--lqy
 * @date: 2024/7/17 10:35
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "school:question:stu:record", cacheTime = RedisConstants.HALF_A_YEAR_SECONDS)
@TableName(value = "school_question_stu_record")
@ApiModel(value = "互动答题学生答题记录实体类")
public class QuestionStuRecord extends CommonInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("question_id")
    @ApiModelProperty(value = "题目id",required = "required")
    private String questionId;

    @TableField("question_text")
    @ApiModelProperty(value = "题目文本")
    private String questionText;

    @TableField("student_answer")
    @ApiModelProperty(value = "学生回答的答案",required = "required")
    private String studentAnswer;

    @TableField("submit")
    @ApiModelProperty(value = "提交时间")
    private String submit;

    @TableField("stu_no")
    @ApiModelProperty(value ="学号",required = "required")
    private String stuNo;

    @TableField(value = "object_id", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "科目数据的id")
    private String objectId;

    @TableField(value = "object_key", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "科目数据的serviceClassName")
    private String objectKey;

    @TableField(value = "subject_classes_id", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "科目表与班级表的关系id")
    private String subjectClassesId;
}
