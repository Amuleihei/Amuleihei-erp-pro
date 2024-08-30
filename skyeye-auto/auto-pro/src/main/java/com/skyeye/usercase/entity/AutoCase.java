/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.usercase.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.BaseGeneralInfo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: AutoCase
 * @Description: 用例实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/28 12:54
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Data
@UniqueField(value = {"objectId", "name"})
@RedisCacheField(name = "auto:case", cacheTime = RedisConstants.HALF_A_YEAR_SECONDS)
@TableName(value = "auto_case")
@ApiModel(value = "用例实体类")
public class AutoCase extends BaseGeneralInfo {

    @TableField(value = "object_id", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "所属第三方业务数据id", required = "required")
    private String objectId;

    @TableField(value = "object_key", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "所属第三方业务数据的key", required = "required")
    private String objectKey;

    @TableField("module_id")
    @ApiModelProperty(value = "模块id", required = "required")
    private String moduleId;

    @TableField(value = "result_key", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "结果的key", required = "required")
    private String resultKey;

    @TableField(exist = false)
    @ApiModelProperty(value = "步骤信息", required = "required,json")
    private List<AutoStep> stepList;


}
