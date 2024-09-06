/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.shopmaterial.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.CacheConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import com.skyeye.material.entity.Material;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: ShopMaterial
 * @Description: 商城商品实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/4 17:16
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = CacheConstants.ERP_SHOP_MATERIAL_CACHE_KEY)
@TableName(value = "erp_shop_material", autoResultMap = true)
@ApiModel("商城商品实体类")
public class ShopMaterial extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "material_id")
    @ApiModelProperty(value = "产品id", required = "required")
    private String materialId;

    @TableField(exist = false)
    @Property(value = "商品信息")
    private Material materialMation;

    @TableField(value = "content")
    @ApiModelProperty(value = "商品详情信息", required = "required")
    private String content;

    @TableField("remark")
    @ApiModelProperty(value = "商品简介")
    private String remark;

    @TableField(value = "logo")
    @ApiModelProperty(value = "商品图片", required = "required")
    private String logo;

    @TableField(value = "carousel_img")
    @ApiModelProperty(value = "商品轮播图，多个逗号隔开", required = "required")
    private String carouselImg;

    @TableField(value = "distribution_type")
    @ApiModelProperty(value = "分销类型，参考#ShopMaterialDistributionType", required = "required,num")
    private Integer distributionType;

    @TableField(value = "delivery_method", typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "配送方式，参考#ShopMaterialDeliveryMethod", required = "required,json")
    private List<String> deliveryMethod;

    @TableField(value = "order_by")
    @ApiModelProperty(value = "排序  值越大越往后", required = "required,num")
    private Integer orderBy;

    @TableField(value = "gift_point")
    @ApiModelProperty(value = "赠送积分", required = "required,num")
    private Integer giftPoint;

    @TableField(value = "virtual_sales")
    @ApiModelProperty(value = "虚拟销量", required = "required")
    private String virtualSales;

    @TableField(value = "real_sales")
    @Property(value = "实际销量")
    private String realSales;

    @TableField(exist = false)
    @ApiModelProperty(value = "上架的规格信息", required = "required,json")
    private List<ShopMaterialNorms> shopMaterialNormsList;

}
