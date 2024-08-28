/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.personnel.service.SysEveUserInstallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysEveUserInstallController
 * @Description: 用户个人配置信息控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 12:11
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "用户个人配置信息", tags = "用户个人配置信息", modelName = "用户个人配置信息")
public class SysEveUserInstallController {

    @Autowired
    private SysEveUserInstallService sysEveUserInstallService;

    /**
     * 自定义设置win背景图片
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys025", value = "自定义设置win背景图片", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "winBgPicUrl", name = "winBgPicUrl", value = "图片url链接", required = "required")})
    @RequestMapping("/post/SysEveUserInstallController/editUserInstallWinBgPic")
    public void editUserInstallWinBgPic(InputObject inputObject, OutputObject outputObject) {
        sysEveUserInstallService.editUserInstallWinBgPic(inputObject, outputObject);
    }

    /**
     * 自定义设置win锁屏背景图片
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys026", value = "自定义设置win锁屏背景图片", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "winLockBgPicUrl", name = "winLockBgPicUrl", value = "图片url链接", required = "required")})
    @RequestMapping("/post/SysEveUserInstallController/editUserInstallWinLockBgPic")
    public void editUserInstallWinLockBgPic(InputObject inputObject, OutputObject outputObject) {
        sysEveUserInstallService.editUserInstallWinLockBgPic(inputObject, outputObject);
    }

    /**
     * 自定义设置主题颜色
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys024", value = "自定义设置主题颜色", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "winThemeColor", name = "winThemeColor", value = "颜色的color数字", required = "required")})
    @RequestMapping("/post/SysEveUserInstallController/editUserInstallThemeColor")
    public void editUserInstallThemeColor(InputObject inputObject, OutputObject outputObject) {
        sysEveUserInstallService.editUserInstallThemeColor(inputObject, outputObject);
    }

    /**
     * 自定义设置win开始菜单尺寸
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys027", value = "自定义设置win开始菜单尺寸", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "winStartMenuSize", name = "winStartMenuSize", value = "尺寸size", required = "required")})
    @RequestMapping("/post/SysEveUserInstallController/editUserInstallWinStartMenuSize")
    public void editUserInstallWinStartMenuSize(InputObject inputObject, OutputObject outputObject) {
        sysEveUserInstallService.editUserInstallWinStartMenuSize(inputObject, outputObject);
    }

    /**
     * 自定义设置win任务栏在屏幕的位置
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys028", value = "自定义设置win开始菜单尺寸", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "winTaskPosition", name = "winTaskPosition", value = "位置属性", required = "required")})
    @RequestMapping("/post/SysEveUserInstallController/editUserInstallWinTaskPosition")
    public void editUserInstallWinTaskPosition(InputObject inputObject, OutputObject outputObject) {
        sysEveUserInstallService.editUserInstallWinTaskPosition(inputObject, outputObject);
    }

    /**
     * 自定义设置win雾化
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys029", value = "自定义设置win雾化", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "winBgPicVague", name = "winBgPicVague", value = "背景图片是否雾化", required = "required"),
        @ApiImplicitParam(id = "winBgPicVagueValue", name = "winBgPicVagueValue", value = "雾化值", required = "required")})
    @RequestMapping("/post/SysEveUserInstallController/editUserInstallVagueBgSrc")
    public void editUserInstallVagueBgSrc(InputObject inputObject, OutputObject outputObject) {
        sysEveUserInstallService.editUserInstallVagueBgSrc(inputObject, outputObject);
    }

    /**
     * 自定义设置窗口下面展示的是图标还是图标+文字
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys030", value = "自定义设置窗口下面展示的是图标还是图标+文字", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "winBottomMenuIcon", name = "winBottomMenuIcon", value = "是否只展示图标", required = "required")})
    @RequestMapping("/post/SysEveUserInstallController/editUserInstallLoadMenuIconById")
    public void editUserInstallLoadMenuIconById(InputObject inputObject, OutputObject outputObject) {
        sysEveUserInstallService.editUserInstallLoadMenuIconById(inputObject, outputObject);
    }

}
