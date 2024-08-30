/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.features.BaseGeneralInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: UserNotice
 * @Description: 用户内部消息的实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/7 14:19
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "sys_eve_user_notice")
@ApiModel("用户内部消息的实体类")
public class UserMessage extends BaseGeneralInfo {

    @TableField("content")
    @ApiModelProperty(value = "通知内容", required = "required")
    private String content;

    @TableField("state")
    @Property("是否已读，参考#WhetherEnum")
    private Integer state;

    @TableField("read_time")
    @Property("消息阅读时间")
    private String readTime;

    @TableField("receive_id")
    @ApiModelProperty(value = "消息接收人", required = "required")
    private String receiveId;

    @TableField("type")
    @ApiModelProperty(value = "消息类型，参考#NoticeUserMessageTypeEnum", required = "required,num")
    private Integer type;

    @TableField(exist = false)
    @ApiModelProperty(value = "传递过来的创建id，用来设置创建人", required = "required")
    private String createUserId;

    @TableField(value = "delete_flag")
    private Integer deleteFlag;

}
