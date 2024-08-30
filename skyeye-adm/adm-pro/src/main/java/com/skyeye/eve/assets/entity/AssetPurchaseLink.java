/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.assets.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.features.SkyeyeLinkData;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AssetPurchaseLink
 * @Description: 资产采购申请关联的资产信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/5/3 18:16
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "asset_purchase_goods")
@ApiModel("资产采购申请关联的资产信息实体类")
public class AssetPurchaseLink extends SkyeyeLinkData {

    @TableField("asset_id")
    @ApiModelProperty(value = "资产id", required = "required")
    private String assetId;

    @TableField(exist = false)
    @Property("资产信息")
    private Asset assetMation;

    @TableField("purchase_num")
    @ApiModelProperty(value = "数量", required = "required,num")
    private Integer purchaseNum;

    @TableField("from_id")
    @ApiModelProperty(value = "来源id")
    private String fromId;

    @TableField(exist = false)
    @Property(value = "资产来源信息")
    private Map<String, Object> fromMation;

    @TableField("unit_price")
    @ApiModelProperty(value = "资产单价", required = "required,double")
    private String unitPrice;

    @TableField("amount_of_money")
    @ApiModelProperty(value = "金额")
    private String amountOfMoney;

    @TableField(exist = false)
    @ApiModelProperty(value = "产品条形码编号")
    private String normsCode;

    @TableField(exist = false)
    @Property(value = "产品条形码编号集合")
    private List<String> normsCodeList;

}
