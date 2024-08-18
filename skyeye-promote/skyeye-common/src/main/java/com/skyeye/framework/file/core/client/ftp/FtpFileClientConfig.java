/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.framework.file.core.client.ftp;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.framework.file.core.client.FileClientConfig;
import lombok.Data;

/**
 * @ClassName: FtpFileClientConfig
 * @Description: Ftp 文件客户端的配置类
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/18 11:10
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("Ftp 文件客户端的配置类")
public class FtpFileClientConfig implements FileClientConfig {

    @ApiModelProperty(value = "基础路径", required = "required")
    private String basePath;

    @ApiModelProperty(value = "自定义域名", required = "required")
    private String domain;

    @ApiModelProperty(value = "主机地址", required = "required")
    private String host;

    @ApiModelProperty(value = "主机端口", required = "required")
    private Integer port;

    @ApiModelProperty(value = "用户名", required = "required")
    private String username;

    @ApiModelProperty(value = "密码", required = "required")
    private String password;

    /**
     * 连接模式
     * <p>
     * 使用 {@link  cn.hutool.extra.ftp.FtpMode} 对应的字符串
     */
    @ApiModelProperty(value = "连接模式", required = "required")
    private String mode;

}
