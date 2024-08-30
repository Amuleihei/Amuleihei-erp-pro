/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.upvote.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: Upvote
 * @Description: 点赞实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/6 14:31
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "wall_upvote")
@ApiModel(value = "点赞实体类")
public class Upvote extends CommonInfo {

    @TableField("user_id")
    @Property(value = "点赞人id")
    private String userId;

    @TableField("object_id")
    @ApiModelProperty(value = "第三方业务数据id", required = "required")
    private String objectId;

    @TableField("object_key")
    @ApiModelProperty(value = "第三方业务数据key", required = "required")
    private String objectKey;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间")
    private String createTime;
}