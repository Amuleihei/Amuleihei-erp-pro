/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.articles.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.features.SkyeyeLinkData;
import lombok.Data;

/**
 * @ClassName: ArticlesPurchaseLink
 * @Description: 用品采购申请关联的用品信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/12/19 18:16
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "asset_articles_purchase_goods")
@ApiModel("用品采购申请关联的用品信息实体类")
public class ArticlesPurchaseLink extends SkyeyeLinkData {

    @TableField("article_id")
    @ApiModelProperty(value = "用品id", required = "required")
    private String articleId;

    @TableField(exist = false)
    @Property("用品信息")
    private Articles articleMation;

    @TableField("apply_purchase_num")
    @ApiModelProperty(value = "申请采购数量", required = "required,num")
    private Integer applyPurchaseNum;

    @TableField("unit_price")
    @ApiModelProperty(value = "用品单价", required = "required,double")
    private String unitPrice;

    @TableField("amount_of_money")
    @ApiModelProperty(value = "金额")
    private String amountOfMoney;

}
