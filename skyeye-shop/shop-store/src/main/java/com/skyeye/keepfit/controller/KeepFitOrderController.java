/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.keepfit.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.keepfit.entity.KeepFitOrder;
import com.skyeye.keepfit.service.KeepFitOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: KeepFitOrderController
 * @Description: 保养订单管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/8 15:13
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "保养订单管理", tags = "保养订单管理", modelName = "保养订单管理")
public class KeepFitOrderController {

    @Autowired
    private KeepFitOrderService keepFitOrderService;

    /**
     * 获取保养订单信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryKeepFitOrderList", value = "获取保养订单信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/KeepFitOrderController/queryKeepFitOrderList")
    public void queryKeepFitOrderList(InputObject inputObject, OutputObject outputObject) {
        keepFitOrderService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加订单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertKeepFitOrder", value = "添加订单", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = KeepFitOrder.class)
    @RequestMapping("/post/KeepFitOrderController/insertKeepFitOrder")
    public void insertKeepFitOrder(InputObject inputObject, OutputObject outputObject) {
        keepFitOrderService.createEntity(inputObject, outputObject);
    }

    /**
     * 支付订单完成后的回调
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "keepFitOrderNotify", value = "支付订单完成后的回调", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "out_trade_no", name = "outTradeNo", value = "商户订单号", required = "required"),
        @ApiImplicitParam(id = "total_fee", name = "totalFee", value = "实际支付的订单金额:单位 分", required = "required")})
    @RequestMapping("/post/KeepFitOrderController/keepFitOrderNotify")
    public void keepFitOrderNotify(InputObject inputObject, OutputObject outputObject) {
        keepFitOrderService.keepFitOrderNotify(inputObject, outputObject);
    }

    /**
     * 保养订单购买详情
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryKeepFitOrderById", value = "保养订单购买详情", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "保养订单id", required = "required")})
    @RequestMapping("/post/KeepFitOrderController/queryKeepFitOrderById")
    public void queryKeepFitOrderById(InputObject inputObject, OutputObject outputObject) {
        keepFitOrderService.selectById(inputObject, outputObject);
    }

    /**
     * 单据核销
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "verificationKeepFitOrder", value = "单据核销", method = "PUT", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "保养订单id", required = "required")})
    @RequestMapping("/post/KeepFitOrderController/verificationKeepFitOrder")
    public void verificationOrder(InputObject inputObject, OutputObject outputObject) {
        keepFitOrderService.verificationOrder(inputObject, outputObject);
    }

    /**
     * 删除保养订单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteKeepFitOrderById", value = "删除保养订单", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/KeepFitOrderController/deleteKeepFitOrderById")
    public void deleteKeepFitOrderById(InputObject inputObject, OutputObject outputObject) {
        keepFitOrderService.deleteById(inputObject, outputObject);
    }

    /**
     * 完成保养
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "complateKeepFitOrder", value = "完成保养", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "订单id", required = "required"),
        @ApiImplicitParam(id = "serviceTechnicianId", name = "serviceTechnicianId", value = "维修技师id(员工id)", required = "required"),
        @ApiImplicitParam(id = "nextServiceMileage", name = "nextServiceMileage", value = "下次保养公里数", required = "required,num"),
        @ApiImplicitParam(id = "nextServiceTime", name = "nextServiceTime", value = "下次保养时间   格式为yyyy-MM-dd", required = "required")})
    @RequestMapping("/post/KeepFitOrderController/complateKeepFitOrder")
    public void complateKeepFitOrder(InputObject inputObject, OutputObject outputObject) {
        keepFitOrderService.complateKeepFitOrder(inputObject, outputObject);
    }

}
