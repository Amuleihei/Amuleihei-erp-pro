/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.features.BaseGeneralInfo;
import lombok.Data;

/**
 * @ClassName: CodeModelGroup
 * @Description: 模板分组实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/10/9 20:55
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField
@TableName(value = "code_model_group")
@ApiModel("模板分组实体类")
public class CodeModelGroup extends BaseGeneralInfo {

    @TableField(value = "code_num", updateStrategy = FieldStrategy.NEVER)
    @Property(value = "分组编码", fuzzyLike = true)
    private String codeNum;

}
