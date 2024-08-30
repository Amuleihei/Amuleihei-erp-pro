/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.meal.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.meal.entity.ShopMeal;
import com.skyeye.meal.service.ShopMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ShopMealController
 * @Description: 套餐管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/5 15:11
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "套餐管理", tags = "套餐管理", modelName = "套餐管理")
public class ShopMealController {

    @Autowired
    private ShopMealService shopMealService;

    /**
     * 获取套餐信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "meal001", value = "获取套餐信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ShopMealController/queryMealList")
    public void queryMealList(InputObject inputObject, OutputObject outputObject) {
        shopMealService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加/编辑套餐
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeShopMeal", value = "添加/编辑套餐", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = ShopMeal.class)
    @RequestMapping("/post/ShopMealController/writeShopMeal")
    public void writeShopMeal(InputObject inputObject, OutputObject outputObject) {
        shopMealService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id删除套餐信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteShopMealById", value = "根据id删除套餐信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ShopMealController/deleteShopMealById")
    public void deleteShopMealById(InputObject inputObject, OutputObject outputObject) {
        shopMealService.deleteById(inputObject, outputObject);
    }

    /**
     * 查看套餐详情
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryShopMealById", value = "查看套餐详情", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ShopMealController/queryShopMealById")
    public void queryShopMealById(InputObject inputObject, OutputObject outputObject) {
        shopMealService.selectById(inputObject, outputObject);
    }

    /**
     * 根据门店获取套餐信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryShopMealByStoreId", value = "根据门店获取套餐信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "storeId", name = "storeId", value = "门店id")})
    @RequestMapping("/post/ShopMealController/queryShopMealByStoreId")
    public void queryShopMealByStoreId(InputObject inputObject, OutputObject outputObject) {
        shopMealService.queryShopMealByStoreId(inputObject, outputObject);
    }

}
