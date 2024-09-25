/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.store.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.store.entity.ShopAddress;
import com.skyeye.store.service.ShopAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ShopAddressController
 * @Description: 收件地址管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/4 10:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "收件地址管理", tags = "收件地址管理", modelName = "收件地址管理")
public class ShopAddressController {

    @Autowired
    private ShopAddressService shopAddressService;

    /**
     * 新增/编辑收件地址信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeShopAddress", value = "新增/编辑收件地址信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = ShopAddress.class)
    @RequestMapping("/post/ShopAddressController/writeShopAddress")
    public void writeShopAddress(InputObject inputObject, OutputObject outputObject) {
        shopAddressService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 批量删除收件地址信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteShopAddressByIds", value = "批量删除收件地址信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "ids", name = "ids", value = "主键id列表，多个id用逗号分隔", required = "required")})
    @RequestMapping("/post/ShopAddressController/deleteShopAddressByIds")
    public void deleteShopAddressByIds(InputObject inputObject, OutputObject outputObject) {
        shopAddressService.deleteById(inputObject, outputObject);
    }

    /**
     * 分页获取收件地址信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryMyShopAddress", value = "获取收件地址信息", method = "POST", allUse = "2")
    @RequestMapping("/post/ShopAddressController/queryMyShopAddress")
    public void queryMyShopAddress(InputObject inputObject, OutputObject outputObject) {
        shopAddressService.queryList(inputObject, outputObject);
    }

    /**
     * 获取默认收件地址信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDefaultShopAddress", value = "获取默认收件地址信息", method = "POST", allUse = "2")
    @RequestMapping("/post/ShopAddressController/queryDefaultShopAddress")
    public void queryDefaultShopAddress(InputObject inputObject, OutputObject outputObject) {
        shopAddressService.queryDefaultShopAddress(inputObject, outputObject);
    }
}