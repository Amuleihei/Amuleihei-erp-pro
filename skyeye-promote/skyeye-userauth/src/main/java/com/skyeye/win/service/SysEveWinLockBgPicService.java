/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.win.entity.SysEveWinLockBgPic;

import java.util.List;

/**
 * @ClassName: SysEveWinLockBgPicService
 * @Description: win系统桌面锁屏图片服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/20 21:21
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysEveWinLockBgPicService extends SkyeyeBusinessService<SysEveWinLockBgPic> {

    void querySysEveWinLockBgPicCustomList(InputObject inputObject, OutputObject outputObject);

    List<SysEveWinLockBgPic> querySystemSysEveWinLockBgPicList();

}
