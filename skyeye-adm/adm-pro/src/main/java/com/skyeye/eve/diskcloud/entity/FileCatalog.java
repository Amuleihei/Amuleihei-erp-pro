/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.diskcloud.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: FileCatalog
 * @Description: 文件夹实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/17 11:29
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Data
@TableName(value = "file_catalog")
@RedisCacheField(name = "file:catalog", cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@ApiModel("文件夹实体类")
public class FileCatalog extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "文件夹名称", required = "required")
    private String name;

    @TableField(value = "parent_id")
    @ApiModelProperty(value = "所属目录", required = "required")
    private String parentId;

    @TableField(value = "source_id")
    @ApiModelProperty(value = "来源id")
    private String sourceId;

    @TableField(value = "delete_flag")
    private Integer deleteFlag;

    @TableField(exist = false)
    @Property("文件夹logo")
    private String logoPath;

    @TableField(exist = false)
    @Property("文件夹")
    private String type;

    @TableField(exist = false)
    @Property("转换后的文件/文件夹的大小")
    private String turnSize;

}
