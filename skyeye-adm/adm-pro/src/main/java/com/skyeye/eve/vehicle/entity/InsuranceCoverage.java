/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.vehicle.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName: InsuranceCoverage
 * @Description: 车辆保险关联的险种实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/5/3 18:16
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "vehicle_insurance_coverage")
@ApiModel("车辆保险关联的险种实体类")
public class InsuranceCoverage extends CommonInfo {

    @TableId("id")
    private String id;

    @TableField("parent_id")
    @Property(value = "车辆保险id")
    private String parentId;

    @TableField("coverage_id")
    @ApiModelProperty(value = "险种id", required = "required")
    private String coverageId;

    @TableField(exist = false)
    @Property(value = "险种信息")
    private Map<String, Object> coverageMation;

    @TableField("premium")
    @ApiModelProperty(value = "保费", required = "required,double")
    private String premium;

    @TableField("insured_amount")
    @ApiModelProperty(value = "保额", required = "required,double")
    private String insuredAmount;

    @TableField("remark")
    @ApiModelProperty(value = "描述")
    private String remark;

}
