/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.invoice.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.invoice.entity.InvoiceHeader;
import com.skyeye.invoice.service.InvoiceHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: InvoiceHeaderController
 * @Description: 发票抬头控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/3 14:38
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "发票抬头管理", tags = "发票抬头管理", modelName = "发票抬头管理")
public class InvoiceHeaderController {

    @Autowired
    private InvoiceHeaderService invoiceHeaderService;

    /**
     * 获取发票抬头列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryInvoiceHeaderList", value = "获取发票抬头列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/InvoiceHeaderController/queryInvoiceHeaderList")
    public void queryInvoiceHeaderList(InputObject inputObject, OutputObject outputObject) {
        invoiceHeaderService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑发票抬头信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeCrmInvoiceHeader", value = "新增/编辑发票抬头信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = InvoiceHeader.class)
    @RequestMapping("/post/InvoiceHeaderController/writeCrmInvoiceHeader")
    public void writeCrmInvoiceHeader(InputObject inputObject, OutputObject outputObject) {
        invoiceHeaderService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除发票抬头信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteInvoiceHeaderById", value = "删除发票抬头信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/InvoiceHeaderController/deleteInvoiceHeaderById")
    public void deleteInvoiceHeaderById(InputObject inputObject, OutputObject outputObject) {
        invoiceHeaderService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据客户id获取发票抬头列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryInvoiceHeaderByObjectId", value = "根据客户id获取发票抬头列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "objectId", name = "objectId", value = "所属第三方业务数据id")})
    @RequestMapping("/post/InvoiceHeaderController/queryInvoiceHeaderByObjectId")
    public void queryInvoiceHeaderByObjectId(InputObject inputObject, OutputObject outputObject) {
        invoiceHeaderService.queryInvoiceHeaderByObjectId(inputObject, outputObject);
    }

}
