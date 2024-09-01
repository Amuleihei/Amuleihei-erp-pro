/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.util;

/**
 * @ClassName: DiskCloudConstants
 * @Description: 文件系统相关的常量类
 * @author: skyeye云系列--卫志强
 * @date: 2021/6/14 11:48
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class DiskCloudConstants {

    /**
     * 文件管理---目录logo图片
     */
    public static final String SYS_FILE_CONSOLE_IS_FOLDER_LOGO_PATH = "../../assets/images/icon/folder-show.png";

    // 文件分享路径
    public static final String getFileShareUrl(String id) {
        return "tpl/shareFile/shareFilepwd.html?id=" + id;
    }

}
