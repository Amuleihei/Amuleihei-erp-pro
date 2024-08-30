/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.deploy.entity.deploy;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @ClassName: JavaWebDeployInfo
 * @Description: 项目工程实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/20 9:36
 *
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("项目工程实体类")
public class JavaWebDeployInfo implements Serializable {

    private String uuid;

    @ApiModelProperty(value = "项目名称", required = "required")
    private String name;

    @ApiModelProperty(value = "版本控制工具类型(1.SVN;2.GIT)", required = "required")
    private int type;

    @ApiModelProperty(value = "svn/git地址", required = "required")
    private String url;

    @ApiModelProperty(value = "Web项目contextPath", required = "required", defaultValue = "/")
    private String contextPath;

    @ApiModelProperty(value = "项目端口", required = "required")
    private int port;

    @ApiModelProperty(value = "Maven profile")
    private String profile;

    @ApiModelProperty(value = "模块名称,多模块工程时，为主jar包所在的模块名称")
    private String module;

    @ApiModelProperty(value = "git分支")
    private String branch;

    @ApiModelProperty(value = "启动参数")
    private String startParams;

    @ApiModelProperty(value = "如果一个git地址上有多个项目，则为目录节点")
    private String gitFolderName;

    @ApiModelProperty(value = "如果一个git地址上有多个项目，当有目录节点时，重新生成子节点id")
    private String gitFolderId;

}
