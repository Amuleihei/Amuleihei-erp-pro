/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menu.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.TableSelectInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.menu.entity.AuthPoint;
import com.skyeye.menu.service.AuthPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @ClassName: AuthPointController
 * @Description: 菜单权限点管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/23 19:24
 *
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "菜单权限点管理", tags = "菜单权限点管理", modelName = "菜单管理")
public class AuthPointController {

    @Autowired
    private AuthPointService authPointService;

    /**
     * 根据菜单id获取菜单权限点列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAuthPointList", value = "根据菜单id获取菜单权限点列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = TableSelectInfo.class)
    @RequestMapping("/post/AuthPointController/queryAuthPointList")
    public void queryAuthPointList(InputObject inputObject, OutputObject outputObject) {
        authPointService.queryList(inputObject, outputObject);
    }

    /**
     * 新增/编辑菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeAuthPoint", value = "新增/编辑菜单权限点", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = AuthPoint.class)
    @RequestMapping("/post/AuthPointController/writeAuthPoint")
    public void writeAuthPoint(InputObject inputObject, OutputObject outputObject) {
        authPointService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id查询菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAuthPointById", value = "根据id查询菜单权限点", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AuthPointController/queryAuthPointById")
    public void queryAuthPointById(InputObject inputObject, OutputObject outputObject) {
        authPointService.selectById(inputObject, outputObject);
    }

    /**
     * 根据id删除菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteAuthPointById", value = "根据id删除菜单权限点", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AuthPointController/deleteAuthPointById")
    public void deleteAuthPointById(InputObject inputObject, OutputObject outputObject) {
        authPointService.deleteById(inputObject, outputObject);
    }

}
