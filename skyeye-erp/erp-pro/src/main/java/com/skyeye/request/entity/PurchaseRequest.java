/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.request.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.SkyeyeFlowable;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: PurchaseRequest
 * @Description: 采购申请实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/22 11:05
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "erp:order:purchaseRequest", cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@TableName(value = "erp_purchase_request")
@ApiModel("采购申请实体类")
public class PurchaseRequest extends SkyeyeFlowable {

    @TableField("title")
    @ApiModelProperty(value = "单据主题", required = "required")
    private String title;

    @TableField("oper_time")
    @ApiModelProperty(value = "单据日期", required = "required")
    private String operTime;

    @TableField("from_type_id")
    @ApiModelProperty(value = "来源单据类型，参考#PurchaseRequestFromType")
    private Integer fromTypeId;

    @TableField("from_id")
    @ApiModelProperty(value = "来源单据id")
    private String fromId;

    @TableField("inquiry_state")
    @Property(value = "询价状态，参考#PurchaseRequestInquiryState")
    private Integer inquiryState;

    @TableField("total_price")
    @ApiModelProperty(value = "合计总金额")
    private String totalPrice;

    @TableField("remark")
    @ApiModelProperty(value = "描述")
    private String remark;

    @TableField("project_id")
    @ApiModelProperty(value = "关联项目id")
    private String projectId;

    @TableField(exist = false)
    @Property(value = "关联项目信息")
    private Map<String, Object> projectMation;

    @TableField("fixed_price_user_id")
    @ApiModelProperty(value = "定价人员id")
    private String fixedPriceUserId;

    @TableField(exist = false)
    @Property(value = "定价人员信息")
    private Map<String, Object> fixedPriceUserMation;

    @TableField(exist = false)
    @ApiModelProperty(value = "采购申请明细信息", required = "required,json")
    private List<PurchaseRequestChild> purchaseRequestChildList;

    @TableField(exist = false)
    @ApiModelProperty(value = "采购申请询价明细信息", required = "json")
    private List<PurchaseRequestInquiryChild> purchaseRequestInquiryChildList;

    @TableField(exist = false)
    @ApiModelProperty(value = "采购申请定价明细信息", required = "json")
    private List<PurchaseRequestFixedChild> purchaseRequestFixedChildList;

}
