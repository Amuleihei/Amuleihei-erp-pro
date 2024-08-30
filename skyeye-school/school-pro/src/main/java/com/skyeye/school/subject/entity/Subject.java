/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.subject.entity;

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

import java.util.Map;

/**
 * @ClassName: Subject
 * @Description: 科目实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/20 15:39
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField(value = "no")
@RedisCacheField(name = "school:subject", cacheTime = RedisConstants.HALF_A_YEAR_SECONDS)
@TableName(value = "school_subject")
@ApiModel(value = "科目实体类")
public class Subject extends BaseGeneralInfo {

    @TableField("img")
    @ApiModelProperty(value = "科目图片", required = "required")
    private String img;

    @TableField("no")
    @ApiModelProperty(value = "科目编号", required = "required", fuzzyLike = true)
    private String no;

    @TableField(value = "owner_id")
    @Property("拥有者id")
    private String ownerId;

    @TableField(exist = false)
    @Property(value = "拥有者信息")
    private Map<String, Object> ownerMation;

}
