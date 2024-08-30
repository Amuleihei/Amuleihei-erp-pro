/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.follow.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.base.handler.enclosure.bean.Enclosure;
import com.skyeye.common.base.handler.enclosure.bean.EnclosureFace;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.SkyeyeTeamAuth;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName: FollowUp
 * @Description: 客户回访实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/2 10:33
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "crm:follow", cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@TableName(value = "crm_follow_up")
@ApiModel("客户回访实体类")
public class FollowUp extends SkyeyeTeamAuth implements EnclosureFace {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("odd_number")
    @Property(value = "回访编号", fuzzyLike = true)
    private String oddNumber;

    @TableField(value = "follow_time")
    @ApiModelProperty(value = "回访时间", required = "required")
    private String followTime;

    @TableField(value = "follow_user_id")
    @ApiModelProperty(value = "回访人ID", required = "required")
    private String followUserId;

    @TableField(exist = false)
    @Property(value = "回访人")
    private Map<String, Object> followUserMation;

    @TableField(value = "type_id")
    @ApiModelProperty(value = "回访形式，参考数据字典")
    private String typeId;

    @TableField(value = "contacts")
    @ApiModelProperty(value = "联系人ID", required = "required")
    private String contacts;

    @TableField(exist = false)
    @Property(value = "联系人")
    private Map<String, Object> contactsMation;

    @TableField(value = "contract_id")
    @ApiModelProperty(value = "合同ID", required = "required")
    private String contractId;

    @TableField(exist = false)
    @Property(value = "合同")
    private Map<String, Object> contractMation;

    @TableField(value = "satisfaction")
    @ApiModelProperty(value = "客户满意度，参考数据字典")
    private String satisfaction;

    @TableField(value = "content")
    @ApiModelProperty(value = "反馈内容")
    private String content;

    @TableField(exist = false)
    @ApiModelProperty(value = "附件", required = "json")
    private Enclosure enclosureInfo;

}
