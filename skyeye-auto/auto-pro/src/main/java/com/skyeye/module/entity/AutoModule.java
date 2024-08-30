/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.module.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.SkyeyeTeamAuth;
import lombok.Data;

/**
 * @ClassName: AutoModule
 * @Description: 项目模块管理
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/19 8:21
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "auto:module", cacheTime = RedisConstants.TOW_MONTH_SECONDS)
@TableName(value = "auto_module", autoResultMap = true)
@ApiModel("项目模块管理")
public class AutoModule extends SkyeyeTeamAuth {

    @TableId("id")
    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("`name`")
    @ApiModelProperty(value = "名称", required = "required")
    private String name;

    @TableField("parent_id")
    @ApiModelProperty(value = "所属父id", defaultValue = "0")
    private String parentId;

    @TableField(exist = false)
    @Property(value = "所属父节点")
    private AutoModule parentMation;

}
