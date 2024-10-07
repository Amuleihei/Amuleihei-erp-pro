/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.level.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: ShopMemberLevel
 * @Description: 会员级别实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/4 10:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField
@TableName(value = "shop_member_level")
@ApiModel("会员级别实体类")
public class ShopMemberLevel extends OperatorUserInfo {
    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "等级名称", required = "required",fuzzyLike = true)
    private String name;

    @TableField(value = "`level`")
    @ApiModelProperty(value = "等级", required = "required")
    private Integer level;

    @TableField(value = "`experience`")
    @ApiModelProperty(value = "升级经验", required = "required")
    private Integer experience;

    @TableField(value = "`discount_percent`")
    @ApiModelProperty(value = "享受折扣")
    private String discountPercent;

    @TableField(value = "`icon`")
    @ApiModelProperty(value = "等级图标", required = "required")
    private String icon;

    @TableField(value = "`background_url`")
    @ApiModelProperty(value = "等级背景图", required = "required")
    private String backgroundUrl;

    @TableField(value = "`enabled`")
    @ApiModelProperty(value = "状态", required = "required")
    private Integer enabled;
}
