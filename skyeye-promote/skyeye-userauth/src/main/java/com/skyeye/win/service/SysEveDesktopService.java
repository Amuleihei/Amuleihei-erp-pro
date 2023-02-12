/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.win.entity.SysDesktop;

public interface SysEveDesktopService extends SkyeyeBusinessService<SysDesktop> {

    void editSysDesktopMationOrderNumUpById(InputObject inputObject, OutputObject outputObject);

    void editSysDesktopMationOrderNumDownById(InputObject inputObject, OutputObject outputObject);

    void queryAllSysDesktopList(InputObject inputObject, OutputObject outputObject);

    void removeAllSysEveMenuByDesktopId(InputObject inputObject, OutputObject outputObject);

}
