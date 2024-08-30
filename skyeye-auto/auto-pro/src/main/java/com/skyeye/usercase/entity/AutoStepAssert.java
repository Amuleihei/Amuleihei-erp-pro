/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.usercase.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: AutoStepAssert
 * @Description: 用例步骤关联的断言实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/28 12:54
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Data
@TableName(value = "auto_step_assert")
@ApiModel(value = "用例步骤关联的断言实体类")
public class AutoStepAssert extends CommonInfo {

    @TableId("id")
    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("step_id")
    @Property(value = "步骤id")
    private String stepId;

    @TableField("`key`")
    @ApiModelProperty(value = "断言的取值参数", required = "required")
    private String key;

    @TableField("operator")
    @ApiModelProperty(value = "操作符", required = "required")
    private String operator;

    @TableField("value_from")
    @ApiModelProperty(value = "值的数据来源，参考#AutoValueFromTypeEnum", required = "required")
    private Integer valueFrom;

    @TableField("value")
    @ApiModelProperty(value = "断言对比得值")
    private String value;

    @TableField("order_by")
    @ApiModelProperty(value = "排序", required = "required")
    private Integer orderBy;

    @TableField("case_id")
    @Property(value = "所属用例")
    private String caseId;
}
