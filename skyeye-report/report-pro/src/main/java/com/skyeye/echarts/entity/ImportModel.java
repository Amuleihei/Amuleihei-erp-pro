/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.echarts.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.entity.features.BaseGeneralInfo;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName: ImportModel
 * @Description: Echarts模型实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/2 8:46
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "report:echarts")
@TableName(value = "report_import_model", autoResultMap = true)
@ApiModel("Echarts模型实体类")
public class ImportModel extends BaseGeneralInfo {

    @TableField("model_code")
    @ApiModelProperty(value = "模型code", required = "required", fuzzyLike = true)
    private String modelCode;

    @TableField(value = "type_id")
    @ApiModelProperty(value = "所属分类ID，数据来自数据字典", required = "required")
    private String typeId;

    @TableField(exist = false)
    @ApiModelProperty(value = "所属分类名称，数据来自数据字典")
    private String typeName;

    @TableField(exist = false)
    @Property(value = "属性的map")
    private Map<String, ReportModelAttr> attr;

    @TableField(exist = false)
    @Property(value = "最新的模型信息")
    private ReportModel reportModel;

    @TableField(exist = false)
    @ApiModelProperty(value = "占位符1")
    private String placeholder1;

    @TableField(exist = false)
    @ApiModelProperty(value = "占位符2")
    private String placeholder2;

    @TableField(exist = false)
    @ApiModelProperty(value = "占位符3")
    private String placeholder3;

    @TableField(exist = false)
    @ApiModelProperty(value = "占位符4")
    private String placeholder4;

    @TableField(exist = false)
    @ApiModelProperty(value = "占位符5")
    private String placeholder5;

    @TableField(exist = false)
    @ApiModelProperty(value = "占位符6")
    private String placeholder6;

    @TableField(exist = false)
    @ApiModelProperty(value = "占位符7")
    private String placeholder7;

    @TableField(exist = false)
    @ApiModelProperty(value = "占位符8")
    private String placeholder8;

}
