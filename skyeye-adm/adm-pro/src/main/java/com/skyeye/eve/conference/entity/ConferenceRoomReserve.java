/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.conference.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.SkyeyeFlowable;
import lombok.Data;

/**
 * @ClassName: ConferenceRoomReserve
 * @Description: 会议室预定申请实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/5/3 18:16
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "assistant:conferenceRoom:reserve", cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@TableName(value = "conference_room_reserve")
@ApiModel("会议室预定申请实体类")
public class ConferenceRoomReserve extends SkyeyeFlowable {

    @TableField("title")
    @ApiModelProperty(value = "标题", required = "required", fuzzyLike = true)
    private String title;

    @TableField("conference_room_id")
    @ApiModelProperty(value = "会议室id", required = "required")
    private String conferenceRoomId;

    @TableField(exist = false)
    @Property(value = "会议室信息")
    private ConferenceRoom conferenceRoomMation;

    @TableField("reserve_reason")
    @ApiModelProperty(value = "使用事由", required = "required")
    private String reserveReason;

    @TableField("start_time")
    @ApiModelProperty(value = "开始时间", required = "required")
    private String startTime;

    @TableField("end_time")
    @ApiModelProperty(value = "结束时间", required = "required")
    private String endTime;

    @TableField("remark")
    @ApiModelProperty(value = "描述")
    private String remark;

}
