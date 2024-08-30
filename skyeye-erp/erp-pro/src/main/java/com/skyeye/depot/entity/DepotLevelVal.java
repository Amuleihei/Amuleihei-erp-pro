/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.depot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: DepotLevelVal
 * @Description: 仓库级别的值表
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/5 21:53
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField(value = {"depotId", "parentId", "number"})
@TableName(value = "erp_depot_level_val", autoResultMap = true)
@ApiModel("仓库级别的值表")
public class DepotLevelVal extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "number")
    @ApiModelProperty(value = "编号", required = "required", fuzzyLike = true)
    private String number;

    @TableField(value = "parent_id")
    @ApiModelProperty(value = "父节点id", required = "required")
    private String parentId;

    @TableField(exist = false)
    @Property(value = "父节点信息")
    private DepotLevelVal parentMation;

    @TableField(value = "depot_id")
    @ApiModelProperty(value = "仓库id", required = "required")
    private String depotId;

    @TableField(exist = false)
    @Property(value = "仓库信息")
    private Depot depotMation;

    @TableField(value = "depot_level_id")
    @ApiModelProperty(value = "级别id")
    private String depotLevelId;

    @TableField(exist = false)
    @Property(value = "级别信息")
    private DepotLevel depotLevelMation;

}
