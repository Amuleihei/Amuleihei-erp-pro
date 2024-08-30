/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.machinprocedure.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: MachinProcedureAcceptChildCode
 * @Description: 加工单子单据工序验收耗材表关联的条形码编号
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/23 16:19
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "erp_machin_procedure_accept_child_code")
@ApiModel("加工单子单据工序验收耗材表关联的条形码编号")
public class MachinProcedureAcceptChildCode extends CommonInfo {

    @TableId("id")
    private String id;

    @TableField("order_id")
    @Property(value = "订单id")
    private String orderId;

    @TableField("parent_id")
    @Property(value = "子单据id")
    private String parentId;

    @TableField("material_id")
    @Property(value = "产品id")
    private String materialId;

    @TableField("norms_id")
    @Property(value = "规格id")
    private String normsId;

    @TableField("norms_code")
    @Property(value = "条形码编号")
    private String normsCode;

}
