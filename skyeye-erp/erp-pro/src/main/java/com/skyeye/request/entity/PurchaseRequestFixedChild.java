/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.request.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.CommonInfo;
import com.skyeye.material.entity.Material;
import com.skyeye.material.entity.MaterialNorms;
import com.skyeye.supplier.entity.Supplier;
import lombok.Data;

/**
 * @ClassName: PurchaseRequestFixedChild
 * @Description: 采购申请-定价子单据实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/22 11:07
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "erp_purchase_request_fixed_child")
@ApiModel("采购申请-定价子单据实体类")
public class PurchaseRequestFixedChild extends CommonInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("parent_id")
    @Property("单据id")
    private String parentId;

    @TableField("material_id")
    @ApiModelProperty(value = "产品id", required = "required")
    private String materialId;

    @TableField(exist = false)
    @Property(value = "产品信息")
    private Material materialMation;

    @TableField("norms_id")
    @ApiModelProperty(value = "规格id", required = "required")
    private String normsId;

    @TableField(exist = false)
    @Property(value = "规格信息")
    private MaterialNorms normsMation;

    @TableField(value = "unit_price")
    @ApiModelProperty(value = "单价", required = "double", defaultValue = "0")
    private String unitPrice;

    @TableField(value = "all_price")
    @ApiModelProperty(value = "不含税的总金额", defaultValue = "0")
    private String allPrice;

    @TableField(value = "tax_rate")
    @ApiModelProperty(value = "税率", defaultValue = "0")
    private String taxRate;

    @TableField(value = "tax_money")
    @ApiModelProperty(value = "税额", required = "double", defaultValue = "0")
    private String taxMoney;

    @TableField(value = "tax_unit_price")
    @ApiModelProperty(value = "含税单价", required = "double", defaultValue = "0")
    private String taxUnitPrice;

    @TableField(value = "tax_last_money")
    @ApiModelProperty(value = "价税合计", defaultValue = "0")
    private String taxLastMoney;

    @TableField("oper_number")
    @ApiModelProperty(value = "询价数量", required = "required,num")
    private Integer operNumber;

    @TableField("last_supplier_id")
    @ApiModelProperty(value = "最后供应商id")
    private String lastSupplierId;

    @TableField(exist = false)
    @Property(value = "最后供应商信息")
    private Supplier lastSupplierMation;

    @TableField("remark")
    @ApiModelProperty(value = "备注")
    private String remark;

}
