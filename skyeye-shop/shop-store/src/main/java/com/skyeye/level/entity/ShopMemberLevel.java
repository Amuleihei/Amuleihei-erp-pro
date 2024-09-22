package com.skyeye.level.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.unique.UniqueField;

import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

@Data
@UniqueField
//@RedisCacheField(name = CacheConstants.SHOP_STORE_CACHE_KEY)
@TableName(value = "shop_member_level")
@ApiModel("会员级别实体类")
public class ShopMemberLevel extends OperatorUserInfo {
    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "等级名称", required = "required")
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
