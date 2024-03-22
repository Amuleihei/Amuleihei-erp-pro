/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.authority.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.authority.service.AuthorityService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AuthorityController
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/22 14:20
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "权限管理", tags = "权限管理", modelName = "权限管理")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    /**
     * 获取角色拥有的PC端菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "getRoleHasMenuListByRoleId", value = "获取角色拥有的PC端菜单", method = "GET", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "roleId", name = "roleId", value = "角色id", required = "required")})
    @RequestMapping("/post/ApiController/getRoleHasMenuListByRoleId")
    public void getRoleHasMenuListByRoleId(InputObject inputObject, OutputObject outputObject) {
        authorityService.getRoleHasMenuListByRoleId(inputObject, outputObject);
    }

    /**
     * 获取角色拥有的APP端菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "getRoleHasAppMenuByRoleId", value = "获取角色拥有的APP端菜单", method = "GET", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "roleId", name = "roleId", value = "角色id", required = "required")})
    @RequestMapping("/post/ApiController/getRoleHasAppMenuByRoleId")
    public void getRoleHasAppMenuByRoleId(InputObject inputObject, OutputObject outputObject) {
        authorityService.getRoleHasAppMenuByRoleId(inputObject, outputObject);
    }

    /**
     * 获取角色拥有的PC端权限
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "getRoleHasAuthPointsByRoleId", value = "获取角色拥有的PC端权限", method = "GET", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "roleId", name = "roleId", value = "角色id", required = "required")})
    @RequestMapping("/post/ApiController/getRoleHasAuthPointsByRoleId")
    public void getRoleHasAuthPointsByRoleId(InputObject inputObject, OutputObject outputObject) {
        authorityService.getRoleHasAuthPointsByRoleId(inputObject, outputObject);
    }

    /**
     * 获取角色拥有的PC端权限
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "getRoleHasAppAuthPointsByRoleId", value = "获取角色拥有的PC端权限", method = "GET", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "roleId", name = "roleId", value = "角色id", required = "required")})
    @RequestMapping("/post/ApiController/getRoleHasAppAuthPointsByRoleId")
    public void getRoleHasAppAuthPointsByRoleId(InputObject inputObject, OutputObject outputObject) {
        authorityService.getRoleHasAppAuthPointsByRoleId(inputObject, outputObject);
    }

}
