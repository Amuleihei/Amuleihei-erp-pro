package com.skyeye.school.interaction.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;


/**
 * @ClassName: questions
 * @Description: 互动答题题目实体类
 * @author: skyeye云系列--lqy
 * @date: 2024/7/17 10:35
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "school:questions", cacheTime = RedisConstants.HALF_A_YEAR_SECONDS)
@TableName(value = "school_questions")
@ApiModel(value = "互动答题题目实体类")
public class Questions extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id,为空时新增，不为空时编辑")
    private String id;

    @TableField("question_text")
    @ApiModelProperty(value = "题目名称",required = "required")
    private String questionText;

    @TableField("categories_id")
    @ApiModelProperty(value = "题目类别id",required = "required")
    private String categoriesId;

    @TableField("categories_name")
    @ApiModelProperty(value = "题目类别名称")
    private String categoriesName;

    @TableField(value = "object_id", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "科目数据的id")
    private String objectId;

    @TableField(value = "object_key", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "科目数据的serviceClassName")
    private String objectKey;

    @TableField(value = "subject_classes_id", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "科目表与班级表的关系id", required = "required")
    private String subjectClassesId;

}
