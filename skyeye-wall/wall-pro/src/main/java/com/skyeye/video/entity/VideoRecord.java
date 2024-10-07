package com.skyeye.video.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

@Data
@TableName(value = "wall_comment_tasn_record")
@ApiModel(value = "收藏点赞记录实体类")
public class VideoRecord extends CommonInfo {

    @TableId("id")
    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("video_id")
    @ApiModelProperty(value = "视频id", required = "required")
    private String videoId;

    @TableField("ct_flag")
    @ApiModelProperty(value = "点赞收藏标识，1，点赞，2，收藏", required = "required")
    private int ctFlag;

    @TableField("user_id")
    @ApiModelProperty(value = "点赞人/收藏人id", required = "required")
    private String userId;

    @TableField("create_time")
    @ApiModelProperty(value = "点赞/收藏时间", required = "required")
    private String createTime;
}
