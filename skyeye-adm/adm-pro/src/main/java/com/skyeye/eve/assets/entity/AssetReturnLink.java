/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.assets.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.features.SkyeyeLinkData;
import lombok.Data;

/**
 * @ClassName: AssetReturnLink
 * @Description: 资产归还申请关联的资产信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/5/3 18:16
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "asset_return_goods")
@ApiModel("资产归还申请关联的资产信息实体类")
public class AssetReturnLink extends SkyeyeLinkData {

    @TableField("asset_id")
    @Property(value = "资产id")
    private String assetId;

    @TableField(exist = false)
    @Property("资产信息")
    private Asset assetMation;

    @TableField("asset_report_id")
    @ApiModelProperty(value = "资产明细id", required = "required")
    private String assetReportId;

    @TableField(exist = false)
    @Property("资产明细信息")
    private AssetReport assetReportMation;

}
