/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tms.address.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.tms.address.entity.BillingAddress;
import com.skyeye.tms.address.service.BillingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: BillingAddressController
 * @Description: 计费地址控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/9 15:36
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "计费地址", tags = "计费地址", modelName = "计费地址")
public class BillingAddressController {

    @Autowired
    private BillingAddressService billingAddressService;

    /**
     * 获取计费地址列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryBillingAddressList", value = "获取计费地址列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/BillingAddressController/queryBillingAddressList")
    public void queryBillingAddressList(InputObject inputObject, OutputObject outputObject) {
        billingAddressService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加或修改计费地址
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeBillingAddress", value = "新增/编辑计费地址", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = BillingAddress.class)
    @RequestMapping("/post/BillingAddressController/writeBillingAddress")
    public void writeBillingAddress(InputObject inputObject, OutputObject outputObject) {
        billingAddressService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除计费地址
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteBillingAddressById", value = "根据ID删除计费地址", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/BillingAddressController/deleteBillingAddressById")
    public void deleteBillingAddressById(InputObject inputObject, OutputObject outputObject) {
        billingAddressService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取已启用的计费地址
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryEnabledBillingAddressList", value = "获取已启用的计费地址", method = "GET", allUse = "2")
    @RequestMapping("/post/TmsBillingAddressTypeController/queryEnabledBillingAddressList")
    public void queryEnabledBillingAddressList(InputObject inputObject, OutputObject outputObject) {
        billingAddressService.queryEnabledBillingAddressList(inputObject, outputObject);
    }

}
