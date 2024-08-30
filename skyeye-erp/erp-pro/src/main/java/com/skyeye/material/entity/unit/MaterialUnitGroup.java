/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.entity.unit;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: MaterialUnitGroup
 * @Description: 计量单位分组实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/23 15:58
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField
@RedisCacheField(name = "erp:unitGroup")
@TableName(value = "erp_unit_group")
@ApiModel("计量单位分组实体类")
public class MaterialUnitGroup extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "组名称", required = "required")
    private String name;

    @TableField(value = "delete_flag")
    private Integer deleteFlag;

    @TableField(exist = false)
    @ApiModelProperty(value = "计量单位信息", required = "required,json")
    private List<MaterialUnit> unitList;

}
