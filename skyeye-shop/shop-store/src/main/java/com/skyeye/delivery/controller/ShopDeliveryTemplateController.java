package com.skyeye.delivery.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

import com.skyeye.delivery.entity.ShopDeliveryTemplate;
import com.skyeye.delivery.service.ShopDeliveryTemplateService;
import com.skyeye.delivery.vo.ShopDeliveryTemplateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "快递运费模版", tags = "快递运费模版", modelName = "快递运费模版")
public class ShopDeliveryTemplateController {

    @Autowired
    private ShopDeliveryTemplateService shopDeliveryTemplateService;

    /**
     * 新增/编辑快递运费模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeShopDeliveryTemplate", value = "添加/修改快递运费模板", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = ShopDeliveryTemplate.class)
    @RequestMapping("/post/ShopDeliveryTemplateController/writeShopDeliveryTemplate")
    public void writeShopDeliveryTemplate(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 批量删除快递运费模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteShopDeliveryTemplateByIds", value = "批量删除快递运费模版信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "ids", name = "ids", value = "主键id列表，多个id用逗号分隔", required = "required")})
    @RequestMapping("/post/ShopDeliveryTemplateController/deleteShopDeliveryTemplateByIds")
    public void deleteShopDeliveryTemplateByIds(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateService.deleteById(inputObject, outputObject);
    }

    /**
     * 分页查询快递运费模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryShopDeliveryTemplate", value = "分页查询快递运费模版信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ShopDeliveryTemplateController/queryShopDeliveryTemplate")
    public void queryShopDeliveryTemplatePageList(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateService.queryPageList(inputObject, outputObject);
    }

    /**
     * 获取快递运费模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryShopDeliveryTemplateList", value = "获取快递运费模版信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "name", name = "name", value = "模版名称"),
            @ApiImplicitParam(id = "storeId", name = "storeId", value = "门店")})
    @RequestMapping("/post/ShopDeliveryTemplateController/queryShopDeliveryTemplateList")
    public void queryShopDeliveryTemplateList(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateService.queryList(inputObject, outputObject);
    }

    /**
     * 根据id获取快递运费模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "selectShopDeliveryTemplateById", value = "根据id获取快递运费模版信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ShopDeliveryTemplateController/selectShopDeliveryTemplateById")
    public void selectShopDeliveryTemplateById(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateService.selectById(inputObject, outputObject);
    }

    /**
     * 获取精简的快递运费模版信息，主要用于下拉列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "shopDeliveryTemplateAllSimple",value = "获取精简的快递运费模版信息，主要用于下拉列表",method = "GET",allUse = "2")
    @ApiImplicitParams(classBean = ShopDeliveryTemplateVo.class)
    @RequestMapping("/post/ShopDeliveryTemplateController/shopDeliveryTemplateAllSimple")
    public void streamlineShopDeliveryTempList(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateService.shopDeliveryTemplateList(inputObject,outputObject);
    }
}
