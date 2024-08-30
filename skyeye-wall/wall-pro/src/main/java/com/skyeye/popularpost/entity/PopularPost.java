/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.popularpost.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: PopularPost
 * @Description: 热门帖子实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/9 14:31
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "wall_popular_post")
@ApiModel(value = "帖子实体类")
public class PopularPost extends CommonInfo {

    @TableId("id")
    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("post_id")
    @ApiModelProperty(value = "被评论的帖子id", required = "required")
    private String postId;

    @TableField("`top`")
    @ApiModelProperty(value = "排名", required = "required")
    private Integer top;

    @TableField("upvote_num")
    @ApiModelProperty(value = "点赞次数", required = "required")
    private Integer upvoteNum;

    @TableField("comment_num")
    @ApiModelProperty(value = "评论次数", required = "required")
    private Integer commentNum;

    @TableField("view_num")
    @ApiModelProperty(value = "浏览次数", required = "required")
    private Integer viewNum;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", required = "required")
    private String createTime;
}
