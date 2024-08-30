package com.skyeye.school.interaction.entity;

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
 * @ClassName: questionCategories
 * @Description: 互动答题题目类别实体类
 * @author: skyeye云系列--lqy
 * @date: 2024/7/17 10:35
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "school:questionCategories", cacheTime = RedisConstants.HALF_A_YEAR_SECONDS)
@TableName(value = "school_question_categories")
@ApiModel(value = "互动答题题目类别实体类")
public class QuestionCategories extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("categories_name")
    @ApiModelProperty(value = "题目类别名称",required = "required")
    private String categoriesName;

    @TableField("description")
    @ApiModelProperty(value = "题目类别描述")
    private String description;
}
