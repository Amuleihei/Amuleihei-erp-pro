package com.skyeye.school.topic.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hp.hpl.sparta.Text;
import com.hp.hpl.sparta.xpath.ElementTest;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import com.skyeye.school.topiccomment.entity.TopicComment;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: Topic
 * @Description: 话题实体类
 * @author: lyj
 * @date: 2024/3/9 14:31
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "school:topic", cacheTime = RedisConstants.TOW_MONTH_SECONDS)
@TableName(value = "school_topic")
@ApiModel(value = "话题实体类")
public class Topic extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "object_id", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "科目数据的id", required = "required")
    private String objectId;

    @TableField(value = "object_key", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "科目数据的serviceClassName", required = "required")
    private String objectKey;

    @TableField(value = "subject_classes_id", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "科目表与班级表的关系id", required = "required")
    private String subjectClassesId;

    @TableField("title")
    @ApiModelProperty(value = "话题标题", required = "required")
    private String title;

    @TableField("content")
    @ApiModelProperty(value = "话题内容", required = "required")
    private String content;

    @TableField("comment_num")
    @Property(value = "评论数量")
    private Integer commentNum;

    @TableField("picture")
    @ApiModelProperty(value = "图片")
    private String picture;

    @TableField(exist = false)
    @ApiModelProperty(value = "话题评论列表")
    private List<TopicComment> topicCommentList;
}