/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: SysEveWinThemeColor
 * @Description: win系统主题颜色实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/22 12:50
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField(value = {"colorClass"})
@RedisCacheField(name = "sys:winThemeColor", cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@TableName(value = "sys_eve_win_theme_color")
@ApiModel("win系统主题颜色实体类")
public class SysEveWinThemeColor extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("color_class")
    @ApiModelProperty(value = "颜色属性", required = "required")
    private String colorClass;

}
