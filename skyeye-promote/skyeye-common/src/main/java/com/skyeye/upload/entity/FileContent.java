/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.upload.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;
import org.apache.ibatis.type.ByteArrayTypeHandler;

/**
 * @ClassName: FileContent
 * @Description: 文件内容实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/18 20:14
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "skyeye_file_content", autoResultMap = true)
@ApiModel("文件内容实体类")
public class FileContent extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "config_id")
    @ApiModelProperty(value = "文件配置id")
    private String configId;

    @TableField(value = "path")
    @ApiModelProperty(value = "文件路径", required = "required")
    private String path;

    @TableField(value = "path", typeHandler = ByteArrayTypeHandler.class)
    @ApiModelProperty(value = "文件内容", required = "required")
    private byte[] content;

}
