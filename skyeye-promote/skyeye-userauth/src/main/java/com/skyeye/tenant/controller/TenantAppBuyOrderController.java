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
import com.skyeye.tenant.entity.TenantAppBuyOrder;
import com.skyeye.tenant.service.TenantAppBuyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TenantAppBuyOrderController
 * @Description: 订单管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/30 16:26
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "订单管理", tags = "订单管理", modelName = "租户管理")
public class TenantAppBuyOrderController {

    @Autowired
    private TenantAppBuyOrderService tenantAppBuyOrderService;

    /**
     * 获取订单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryTenantAppBuyOrderList", value = "获取订单列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/TenantAppBuyOrderController/queryTenantAppBuyOrderList")
    public void queryTenantAppBuyOrderList(InputObject inputObject, OutputObject outputObject) {
        tenantAppBuyOrderService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑订单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeTenantAppBuyOrder", value = "新增/编辑订单", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = TenantAppBuyOrder.class)
    @RequestMapping("/post/TenantAppBuyOrderController/writeTenantAppBuyOrder")
    public void writeTenantAppBuyOrder(InputObject inputObject, OutputObject outputObject) {
        tenantAppBuyOrderService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 订单提交审批
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "submitTenantAppBuyOrder", value = "订单提交审批", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required"),
        @ApiImplicitParam(id = "approvalId", name = "approvalId", value = "审批人", required = "required")})
    @RequestMapping("/post/TenantAppBuyOrderController/submitToApproval")
    public void submitToApproval(InputObject inputObject, OutputObject outputObject) {
        tenantAppBuyOrderService.submitToApproval(inputObject, outputObject);
    }

    /**
     * 删除订单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteTenantAppBuyOrderById", value = "删除订单", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/TenantAppBuyOrderController/deleteTenantAppBuyOrderById")
    public void deleteTenantAppBuyOrderById(InputObject inputObject, OutputObject outputObject) {
        tenantAppBuyOrderService.deleteById(inputObject, outputObject);
    }

    /**
     * 撤销订单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "revokeTenantAppBuyOrder", value = "撤销订单", method = "PUT", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "processInstanceId", name = "processInstanceId", value = "流程实例id", required = "required")})
    @RequestMapping("/post/TenantAppBuyOrderController/revoke")
    public void revoke(InputObject inputObject, OutputObject outputObject) {
        tenantAppBuyOrderService.revoke(inputObject, outputObject);
    }

}
