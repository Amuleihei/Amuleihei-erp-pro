/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.assets.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.BaseGeneralInfo;
import lombok.Data;

/**
 * @ClassName: Asset
 * @Description: 资产实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/9 10:22
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "assistant:asset", cacheTime = RedisConstants.HALF_A_YEAR_SECONDS)
@TableName(value = "asset_management")
@ApiModel("资产实体类")
public class Asset extends BaseGeneralInfo {

    @TableField(value = "asset_img")
    @ApiModelProperty(value = "资产图片", required = "required")
    private String assetImg;

    @TableField(value = "type_id")
    @ApiModelProperty(value = "资产所属类型", required = "required")
    private String typeId;

    @TableField(value = "number_prefix")
    @ApiModelProperty(value = "资产编号前缀，不可重复", required = "required")
    private String numberPrefix;

    @TableField(value = "specifications")
    @ApiModelProperty(value = "资产规格")
    private String specifications;

    @TableField(value = "read_price")
    @ApiModelProperty(value = "资产参考价", required = "required,double")
    private String readPrice;

}
