package com.skyeye.jdeploy.domain;

import lombok.Data;

@Data
public class JavaWebDeployInfo {

    private String uuid;

    private String name;

    private int type;

    private String url;

    private String contextPath;

    private int port;

    private String profile;

    private String module;

    private String branch;

    /**
     * 启动参数
     */
    private String startParams;

    /**
     * 如果一个git地址上有多个项目，则为目录节点
     */
    private String gitFolderName;

    /**
     * 如果一个git地址上有多个项目，当有目录节点时，重新生成子节点id
     */
    private String gitFolderId;

}
