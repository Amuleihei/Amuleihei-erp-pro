/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.articles.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.BaseGeneralInfo;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: Articles
 * @Description: 用品实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/9 9:22
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField
@RedisCacheField(name = "assistant:articles", cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@TableName(value = "asset_articles")
@ApiModel("用品实体类")
public class Articles extends BaseGeneralInfo {

    @TableField(value = "articles_num")
    @Property("用品编号")
    private String articlesNum;

    @TableField(value = "type_id")
    @ApiModelProperty(value = "用品类别", required = "required")
    private String typeId;

    @TableField(exist = false)
    @Property("用品类别名称")
    private String typeName;

    @TableField(value = "specifications")
    @ApiModelProperty(value = "规格")
    private String specifications;

    @TableField(value = "unit_of_measurement")
    @ApiModelProperty(value = "计量单位", required = "required")
    private String unitOfMeasurement;

    @TableField(value = "initial_num", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "初始化数量【不可修改】", required = "num")
    private Integer initialNum;

    @TableField(value = "residual_num")
    @Property("当前剩余数量，新增时和初始化数量(initialNum)保持一致")
    private Integer residualNum;

    @TableField(value = "storage_area")
    @ApiModelProperty(value = "存放区域")
    private String storageArea;

    @TableField(value = "asset_admin")
    @ApiModelProperty(value = "管理人")
    private String assetAdmin;

    @TableField(exist = false)
    @Property("管理人信息")
    private Map<String, Object> assetAdminMation;

}
