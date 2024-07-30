/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tenant.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: TenantAppBuyOrderYear
 * @Description: 订单-购买应用年限管理实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/29 16:31
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "tenant_app_buy_order_year")
@ApiModel("订单-购买应用年限管理实体类")
public class TenantAppBuyOrderYear extends CommonInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("parent_id")
    @Property(value = "单据ID")
    private String parentId;

    @TableField(value = "app_id")
    @ApiModelProperty(value = "应用id", required = "required")
    private String appId;

    @TableField(exist = false)
    @Property("应用信息")
    private TenantApp appMation;

    @TableField(value = "account_year")
    @ApiModelProperty(value = "购买的年限", required = "required,num")
    private Integer accountYear;

    @TableField(value = "unit_price")
    @ApiModelProperty(value = "单价", required = "required")
    private String unitPrice;

    @TableField(value = "all_price")
    @Property(value = "总价")
    private String allPrice;

}
