/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.personnel.entity.SysEveUserInstall;

/**
 * @ClassName: SysEveUserInstallService
 * @Description: 用户个人配置信息服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 12:09
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysEveUserInstallService extends SkyeyeBusinessService<SysEveUserInstall> {

    void editUserInstallWinBgPic(InputObject inputObject, OutputObject outputObject);

    void editUserInstallWinLockBgPic(InputObject inputObject, OutputObject outputObject);

    void editUserInstallThemeColor(InputObject inputObject, OutputObject outputObject);

    void editUserInstallWinStartMenuSize(InputObject inputObject, OutputObject outputObject);

    void editUserInstallWinTaskPosition(InputObject inputObject, OutputObject outputObject);

    void editUserInstallVagueBgSrc(InputObject inputObject, OutputObject outputObject);

    void editUserInstallLoadMenuIconById(InputObject inputObject, OutputObject outputObject);
}
