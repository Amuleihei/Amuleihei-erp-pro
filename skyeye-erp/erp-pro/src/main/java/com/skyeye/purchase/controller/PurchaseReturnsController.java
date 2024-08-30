/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.purchase.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.depot.entity.DepotOut;
import com.skyeye.purchase.entity.PurchaseReturn;
import com.skyeye.purchase.service.PurchaseReturnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: PurchaseReturnsController
 * @Description: 采购退货单管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/8 21:14
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "采购退货单", tags = "采购退货单", modelName = "采购模块")
public class PurchaseReturnsController {

    @Autowired
    private PurchaseReturnsService purchaseReturnsService;

    /**
     * 获取采购退货列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "purchasereturns001", value = "获取采购退货列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/PurchaseReturnsController/queryPurchaseReturnsToList")
    public void queryPurchaseReturnsToList(InputObject inputObject, OutputObject outputObject) {
        purchaseReturnsService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑采购退货信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writePurchaseReturn", value = "新增/编辑采购退货信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = PurchaseReturn.class)
    @RequestMapping("/post/PurchaseReturnsController/writePurchaseReturn")
    public void writePurchaseReturn(InputObject inputObject, OutputObject outputObject) {
        purchaseReturnsService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 转仓库出库单时，根据id查询采购退货信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryPurchaseReturnsTransById", value = "转仓库出库单时，根据id查询采购退货信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/PurchaseReturnsController/queryPurchaseReturnsTransById")
    public void queryPurchaseReturnsTransById(InputObject inputObject, OutputObject outputObject) {
        purchaseReturnsService.queryPurchaseReturnsTransById(inputObject, outputObject);
    }

    /**
     * 采购退货单信息转仓库出库单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertPurchaseReturnsToTurnDepot", value = "采购退货单信息转仓库出库单", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = DepotOut.class, value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/PurchaseReturnsController/insertPurchaseReturnsToTurnDepot")
    public void insertPurchaseReturnsToTurnDepot(InputObject inputObject, OutputObject outputObject) {
        purchaseReturnsService.insertPurchaseReturnsToTurnDepot(inputObject, outputObject);
    }

}
