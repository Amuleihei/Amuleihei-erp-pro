/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.bgimg.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.entity.features.BaseGeneralInfo;
import lombok.Data;

/**
 * @ClassName: BgImage
 * @Description: 背景图片实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/26 13:59
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Data
@RedisCacheField(name = "report:bgImg")
@TableName(value = "report_bg_image", autoResultMap = true)
@ApiModel("背景图片实体类")
public class BgImage extends BaseGeneralInfo {

    @TableField("enabled")
    @ApiModelProperty(value = "状态，参考#EnableEnum", required = "required,num")
    private Integer enabled;

    @TableField("img_path")
    @ApiModelProperty(value = "图片路径", required = "required")
    private String imgPath;

    @TableField(value = "type_id")
    @ApiModelProperty(value = "所属分类ID，数据来自数据字典", required = "required")
    private String typeId;

}
