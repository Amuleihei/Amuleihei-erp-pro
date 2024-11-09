/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.upload.entity;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.entity.features.BaseGeneralInfo;
import com.skyeye.framework.file.core.client.FileClientConfig;
import com.skyeye.framework.file.core.client.db.DBFileClientConfig;
import com.skyeye.framework.file.core.client.ftp.FtpFileClientConfig;
import com.skyeye.framework.file.core.client.local.LocalFileClientConfig;
import com.skyeye.framework.file.core.client.s3.S3FileClientConfig;
import com.skyeye.framework.file.core.client.sftp.SftpFileClientConfig;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName: FileConfig
 * @Description: 文件配置实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/18 9:35
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "skyeye:fileConfig")
@TableName(value = "skyeye_file_config", autoResultMap = true)
@ApiModel("文件配置实体类")
public class FileConfig extends BaseGeneralInfo {

    @TableField(value = "is_default")
    @ApiModelProperty(value = "是否默认，参考#IsDefaultEnum", required = "required,num")
    private Integer isDefault;

    @TableField(value = "storage")
    @ApiModelProperty(value = "存储器，参考#FileStorageEnum", required = "required,num")
    private Integer storage;

    @TableField(value = "config", typeHandler = FileClientConfigTypeHandler.class)
    @ApiModelProperty(value = "配置信息", required = "required,json")
    private FileClientConfig config;

    public static class FileClientConfigTypeHandler extends AbstractJsonTypeHandler<Object> {

        @Override
        public Object parse(String json) {
            Map<String, Object> config = JSONUtil.toBean(json, null);
            if (CollectionUtil.isEmpty(config)) {
                return null;
            }
            // 兼容老版本的包路径
            String className = StrUtil.subAfter(config.get("@class").toString(), ".", true);
            switch (className) {
                case "DBFileClientConfig":
                    return JSONUtil.toBean(json, DBFileClientConfig.class);
                case "FtpFileClientConfig":
                    return JSONUtil.toBean(json, FtpFileClientConfig.class);
                case "LocalFileClientConfig":
                    return JSONUtil.toBean(json, LocalFileClientConfig.class);
                case "SftpFileClientConfig":
                    return JSONUtil.toBean(json, SftpFileClientConfig.class);
                case "S3FileClientConfig":
                    return JSONUtil.toBean(json, S3FileClientConfig.class);
                default:
                    throw new IllegalArgumentException("未知的 FileClientConfig 类型：" + json);
            }
        }

        @Override
        public String toJson(Object obj) {
            return JSONUtil.toJsonStr(obj);
        }

    }

}
