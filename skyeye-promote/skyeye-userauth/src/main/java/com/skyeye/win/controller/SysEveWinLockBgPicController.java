/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.win.entity.SysEveWinLockBgPic;
import com.skyeye.win.service.SysEveWinLockBgPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysEveWinLockBgPicController
 * @Description: win系统桌面锁屏图片控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/20 21:21
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "win系统桌面锁屏图片", tags = "win系统桌面锁屏图片", modelName = "win系统桌面锁屏图片")
public class SysEveWinLockBgPicController {

    @Autowired
    private SysEveWinLockBgPicService sysEveWinLockBgPicService;

    /**
     * 获取win系统锁屏桌面图片列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "querySysEveWinLockBgPicList", value = "获取win系统锁屏桌面图片列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/SysEveWinLockBgPicController/querySysEveWinLockBgPicList")
    public void querySysEveWinLockBgPicList(InputObject inputObject, OutputObject outputObject) {
        sysEveWinLockBgPicService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加win系统锁屏桌面图片信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertSysEveWinLockBgPic", value = "添加win系统锁屏桌面图片信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = SysEveWinLockBgPic.class)
    @RequestMapping("/post/SysEveWinLockBgPicController/insertSysEveWinLockBgPic")
    public void insertSysEveWinLockBgPic(InputObject inputObject, OutputObject outputObject) {
        sysEveWinLockBgPicService.createEntity(inputObject, outputObject);
    }

    /**
     * 删除win系统锁屏桌面图片信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteSysEveWinLockBgPicById", value = "删除win系统锁屏桌面图片信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SysEveWinLockBgPicController/deleteSysEveWinLockBgPicById")
    public void deleteSysEveWinLockBgPicById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinLockBgPicService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取用户自定义的win系统桌面锁屏图片列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "querySysEveWinLockBgPicCustomList", value = "获取用户自定义的win系统桌面锁屏图片列表", method = "POST", allUse = "2")
    @RequestMapping("/post/SysEveWinLockBgPicController/querySysEveWinLockBgPicCustomList")
    public void querySysEveWinLockBgPicCustomList(InputObject inputObject, OutputObject outputObject) {
        sysEveWinLockBgPicService.querySysEveWinLockBgPicCustomList(inputObject, outputObject);
    }

}
