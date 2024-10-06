package com.skyeye.store.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.store.entity.ShopAddressLabel;
import com.skyeye.store.service.ShopAddressLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "收件地址标签管理", tags = "收件地址标签管理", modelName = "收件地址标签管理")
public class ShopAddressLabelController {

    @Autowired
    private ShopAddressLabelService shopAddressLabelService;

    @ApiOperation(id = "writeShopAddressLabel", value = "新增/编辑收件地址标签信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = ShopAddressLabel.class)
    @RequestMapping("/post/ShopAddressLabelController/writeShopAddressLabel")
    public void writeShopAddressLabel(InputObject inputObject, OutputObject outputObject) {
        shopAddressLabelService.saveOrUpdateEntity(inputObject, outputObject);
    }

    @ApiOperation(id = "deleteShopAddressLabelByIds", value = "批量删除收件地址标签信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "ids", name = "ids", value = "主键id列表，多个id用逗号分隔", required = "required")})
    @RequestMapping("/post/ShopAddressLabelController/deleteShopAddressLabelByIds")
    public void deleteShopAddressLabelByIds(InputObject inputObject, OutputObject outputObject) {
        shopAddressLabelService.deleteById(inputObject, outputObject);
    }

    @ApiOperation(id = "queryShopAddressLabelList", value = "获取自己的收件地址标签信息", method = "POST", allUse = "2")
    @RequestMapping("/post/ShopAddressLabelController/queryShopAddressLabelList")
    public void queryShopAddressLabelList(InputObject inputObject, OutputObject outputObject) {
        shopAddressLabelService.queryList(inputObject, outputObject);
    }
    @ApiOperation(id = "selectShopAddressLabelById", value = "根据id查询收件地址标签信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ShopAddressLabelController/selectShopAddressLabelById")
    public void selectShopAddressLabelById(InputObject inputObject, OutputObject outputObject) {
        shopAddressLabelService.selectById(inputObject, outputObject);
    }
}