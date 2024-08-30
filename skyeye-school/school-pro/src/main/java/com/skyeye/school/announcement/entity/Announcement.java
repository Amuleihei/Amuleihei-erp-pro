package com.skyeye.school.announcement.entity;

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
 * @ClassName: Announcement
 * @Description: 公告管理实体类
 * @author: skyeye云系列--lqy
 * @date: 2024/7/16 11:01
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */

@Data
@RedisCacheField(name = "school:announcement", cacheTime = RedisConstants.HALF_A_YEAR_SECONDS)
@TableName(value = "school_announcement")
@ApiModel(value = "公告管理实体类")
public class Announcement extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "object_id", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "科目数据的id")
    private String objectId;

    @TableField(value = "object_key", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "科目数据的serviceClassName")
    private String objectKey;

    @TableField(value = "subject_classes_id", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "科目表与班级表的关系id", required = "required")
    private String subjectClassesId;

    @TableField("title")
    @ApiModelProperty(value = "公告标题",required = "required")
    private String title;

    @TableField("content")
    @ApiModelProperty(value = "公告内容",required = "required")
    private String content;

    @TableField("annex")
    @ApiModelProperty(value = "公告附件")
    private String annex;

    @TableField("is_confirm")
    @ApiModelProperty(value = "公告是否需要确认，参考#EnableEnum", required = "required,num")
    private Integer isConfirm;

    @TableField("confirm_num")
    @ApiModelProperty(value = "确认的人数")
    private int confirmNum;

    @TableField("un_confirm_num")
    @ApiModelProperty(value = "未确认的人数")
    private int unConfirmNum;
}
