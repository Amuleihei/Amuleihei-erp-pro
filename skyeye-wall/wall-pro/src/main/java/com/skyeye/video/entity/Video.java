package com.skyeye.video.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

@Data
@TableName(value = "wall_video")
@ApiModel(value = "视频实体类")
public class Video extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("topic")
    @ApiModelProperty(value = "标题", required = "required")
    private String topic;

    @TableField("content")
    @ApiModelProperty(value = "内容", required = "required")
    private String content;

    @TableField("video_src")
    @ApiModelProperty(value = "视频地址", required = "required")
    private String videoSrc;

    @TableField("collection_num")
    @ApiModelProperty(value = "收藏数量，默认0")
    private int collectionNum;

    @TableField("visit_num")
    @ApiModelProperty(value = "收藏数量，默认0")
    private int visitNum;

    @TableField("tasn_num")
    @ApiModelProperty(value = "收藏数量，默认0")
    private int tasnNum;

    @TableField("remark_num")
    @ApiModelProperty(value = "收藏数量，默认0")
    private int remarkNum;

}
