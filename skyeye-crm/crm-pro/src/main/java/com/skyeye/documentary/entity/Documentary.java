/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.documentary.entity;

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
import com.skyeye.opportunity.entity.CrmOpportunity;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName: Documentary
 * @Description: 跟单信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/10/27 15:58
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "crm:documentary", cacheTime = RedisConstants.HALF_A_YEAR_SECONDS)
@TableName(value = "crm_documentary")
@ApiModel("客户跟单信息实体类")
public class Documentary extends SkyeyeTeamAuth implements EnclosureFace {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "title")
    @ApiModelProperty(value = "标题", required = "required")
    private String title;

    @TableField(value = "content")
    @ApiModelProperty(value = "详细内容", required = "required")
    private String content;

    @TableField(value = "type_id")
    @ApiModelProperty(value = "跟单分类ID", required = "required")
    private String typeId;

    @TableField(value = "opportunity_id")
    @ApiModelProperty(value = "关联商机id", required = "required")
    private String opportunityId;

    @TableField(exist = false)
    @Property(value = "关联商机信息")
    private CrmOpportunity opportunityMation;

    @TableField(value = "contacts")
    @ApiModelProperty(value = "联系人ID", required = "required")
    private String contacts;

    @TableField(exist = false)
    @Property(value = "联系人")
    private Map<String, Object> contactsMation;

    @TableField(value = "documentary_time")
    @ApiModelProperty(value = "跟单时间", required = "required")
    private String documentaryTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "附件", required = "json")
    private Enclosure enclosureInfo;

}
