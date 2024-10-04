package com.skyeye.adsense.entity;

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
@TableName(value = "shop_adsense")
@ApiModel("广告位管理")
public class Adsense extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "标题", required = "required")
    private String name;

    @TableField(value = "`jump_url`")
    @ApiModelProperty(value = "跳转链接")
    private String jumpUrl;

    @TableField(value = "`pc_logo`")
    @ApiModelProperty(value = "pc端Logo")
    private String pcLogo;

    @TableField(value = "`app_logo`")
    @ApiModelProperty(value = "app端Logo")
    private String appLogo;

    @TableField(value = "`enabled`")
    @ApiModelProperty(value = "状态", required = "required")
    private Integer enabled;

    @TableField(value = "`order_by`")
    @ApiModelProperty(value = "序号", required = "required")
    private Integer orderBy;

    @TableField(value = "`remark`")
    @ApiModelProperty(value = "备注")
    private String remark;
}
