/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.entity.features.BaseGeneralInfo;
import lombok.Data;

/**
 * @ClassName: DomModel
 * @Description: DOM模型实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/25 16:36
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Data
@RedisCacheField(name = "report:dom")
@TableName(value = "report_dom_model", autoResultMap = true)
@ApiModel("DOM模型实体类")
public class DomModel extends BaseGeneralInfo {

    @TableField("enabled")
    @ApiModelProperty(value = "状态，参考#EnableEnum", required = "required,num")
    private Integer enabled;

    @TableField("img_path")
    @ApiModelProperty(value = "图片路径", required = "required")
    private String imgPath;

    @TableField("content")
    @ApiModelProperty(value = "dom脚本", required = "required")
    private String content;

    @TableField(value = "type_id")
    @ApiModelProperty(value = "所属分类ID，数据来自数据字典", required = "required")
    private String typeId;

    @TableField("default_width")
    @ApiModelProperty(value = "默认宽度，单位：px", required = "required,num")
    private Integer defaultWidth;

    @TableField("default_height")
    @ApiModelProperty(value = "默认高度，单位：px", required = "required,num")
    private Integer defaultHeight;

}
