/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/
package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveWinLockBgPicService {

    public void querySysEveWinLockBgPicList(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void insertSysEveWinLockBgPicMation(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void deleteSysEveWinLockBgPicMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void insertSysEveWinBgPicMationByCustom(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void querySysEveWinBgPicCustomList(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void deleteSysEveWinBgPicMationCustomById(InputObject inputObject, OutputObject outputObject) throws Exception;

}
