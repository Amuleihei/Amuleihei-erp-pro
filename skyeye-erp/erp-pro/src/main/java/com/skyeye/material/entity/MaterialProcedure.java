/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.features.OperatorUserInfo;
import com.skyeye.procedure.entity.WorkProcedure;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: MaterialProcedure
 * @Description: ERP商品表与工序管理的关系实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/17 15:58
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "erp_material_procedure")
@ApiModel("ERP商品表与工序管理的关系实体类")
public class MaterialProcedure extends OperatorUserInfo implements Serializable {

    @Property(value = "商品id")
    @TableField(value = "material_id")
    private String materialId;

    @TableField(value = "procedure_id")
    @ApiModelProperty(value = "工序id", required = "required")
    private String procedureId;

    @TableField(exist = false)
    @Property(value = "工序")
    private WorkProcedure procedureMation;

    @TableField("unit_price")
    @ApiModelProperty(value = "商品关联工序的单价", required = "required,double")
    private String unitPrice;

}
