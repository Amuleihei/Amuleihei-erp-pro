/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.knowledge.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.features.BaseGeneralInfo;
import com.skyeye.school.chapter.entity.Chapter;
import lombok.Data;

/**
 * @ClassName: KnowledgePoints
 * @Description: 知识点实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/23 11:40
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField(value = {"objectId", "name"})
@RedisCacheField(name = "school:knowledge")
@TableName(value = "school_knowledge_points")
@ApiModel(value = "知识点实体类")
public class KnowledgePoints extends BaseGeneralInfo {

    @TableField("object_id")
    @ApiModelProperty(value = "科目数据的id", required = "required")
    private String objectId;

    @TableField("object_key")
    @ApiModelProperty(value = "科目数据的serviceClassName", required = "required")
    private String objectKey;

    @TableField("subject_classes_id")
    @ApiModelProperty(value = "科目表与班级表的关系id", required = "required")
    private String subjectClassesId;

    @TableField("chapter_id")
    @ApiModelProperty(value = "所属章节id", required = "required")
    private String chapterId;

    @TableField(exist = false)
    @Property(value = "所属章节信息")
    private Chapter chapterMation;

    @TableField("content")
    @ApiModelProperty(value = "内容", required = "required")
    private String content;

}
