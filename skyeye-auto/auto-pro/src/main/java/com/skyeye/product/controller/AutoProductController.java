/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.product.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.product.entity.AutoProduct;
import com.skyeye.product.service.AutoProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AutoProductController
 * @Description: 产品管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/26 8:56
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "产品管理", tags = "产品管理", modelName = "产品管理")
public class AutoProductController {

    @Autowired
    private AutoProductService autoProductService;

    /**
     * 获取产品信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAutoProductList", value = "获取产品信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/AutoProductController/queryAutoProductList")
    public void queryAutoProductList(InputObject inputObject, OutputObject outputObject) {
        autoProductService.queryPageList(inputObject, outputObject);
    }

    /**
     * 获取所有产品信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAllAutoProductList", value = "获取所有产品信息列表", method = "GET", allUse = "2")
    @RequestMapping("/post/AutoProductController/queryAllAutoProductList")
    public void queryAllAutoProductList(InputObject inputObject, OutputObject outputObject) {
        autoProductService.queryAllAutoProductList(inputObject, outputObject);
    }

    /**
     * 添加或修改产品信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeAutoProduct", value = "新增/编辑产品信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = AutoProduct.class)
    @RequestMapping("/post/AutoProductController/writeAutoProduct")
    public void writeAutoProduct(InputObject inputObject, OutputObject outputObject) {
        autoProductService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除产品信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteAutoProductById", value = "根据ID删除产品信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AutoProductController/deleteAutoProductById")
    public void deleteAutoProductById(InputObject inputObject, OutputObject outputObject) {
        autoProductService.deleteById(inputObject, outputObject);
    }

    /**
     * 修改产品为进行中
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "editAutoProductToProgressById", value = "修改产品为进行中", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AutoProductController/editAutoProductToProgressById")
    public void editAutoProductToProgressById(InputObject inputObject, OutputObject outputObject) {
        autoProductService.editAutoProductToProgressById(inputObject, outputObject);
    }
}
