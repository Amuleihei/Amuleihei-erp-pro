/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tenant.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.tenant.entity.Tenant;
import com.skyeye.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TenantController
 * @Description: 租户控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/28 20:15
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "租户管理", tags = "租户管理", modelName = "租户管理")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    /**
     * 获取租户列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryTenantList", value = "获取租户列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/TenantController/queryTenantList")
    public void queryTenantList(InputObject inputObject, OutputObject outputObject) {
        tenantService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑租户
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeTenant", value = "新增/编辑租户", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = Tenant.class)
    @RequestMapping("/post/TenantController/writeTenant")
    public void writeTenant(InputObject inputObject, OutputObject outputObject) {
        tenantService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id查询租户信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryTenantById", value = "根据id查询租户信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "租户ID", required = "required")})
    @RequestMapping("/post/TenantController/queryTenantById")
    public void queryTenantById(InputObject inputObject, OutputObject outputObject) {
        tenantService.selectById(inputObject, outputObject);
    }

    /**
     * 删除租户
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteTenantById", value = "删除租户", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "租户ID", required = "required")})
    @RequestMapping("/post/TenantController/deleteTenantById")
    public void deleteTenantById(InputObject inputObject, OutputObject outputObject) {
        tenantService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取所有租户信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAllTenantList", value = "获取所有租户信息", method = "GET", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/TenantController/queryAllTenantList")
    public void queryAllTenantList(InputObject inputObject, OutputObject outputObject) {
        tenantService.queryAllTenantList(inputObject, outputObject);
    }

}
