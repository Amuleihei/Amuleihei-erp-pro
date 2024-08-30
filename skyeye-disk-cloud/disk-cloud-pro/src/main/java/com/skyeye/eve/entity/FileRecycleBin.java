/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity;

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
 * @ClassName: FileRecycleBin
 * @Description: 回收站实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/17 21:56
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Data
@TableName(value = "file_catelog_recycle_bin")
@RedisCacheField(name = "file:recycleBin", cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@ApiModel("回收站实体类")
public class FileRecycleBin extends OperatorUserInfo {

    @TableId("id")
    @Property(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "file_id")
    @ApiModelProperty(value = "文件或文件夹id", required = "required")
    private String fileId;

    @TableField(value = "file_type")
    @Property(value = "文件类型，参考#DickCloudType")
    private String fileType;

    @TableField(value = "file_name")
    @Property(value = "文件或文件夹的名称")
    private String fileName;

}
