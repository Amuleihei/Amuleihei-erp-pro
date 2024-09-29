package com.skyeye.delivery.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.delivery.entity.ShopDeliveryTemplateCharge;
import com.skyeye.delivery.service.ShopDeliveryTemplateChargeService;
import com.skyeye.delivery.vo.ShopDeliveryTemplateChargeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "快递运费费用模版", tags = "快递运费费用模版", modelName = "快递运费费用模版")
public class ShopDeliveryTemplateChargeController {

    @Autowired
    private ShopDeliveryTemplateChargeService   shopDeliveryTemplateChargeService;


    /**
     * 新增/编辑快递运费费用模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeShopDeliveryTemplateCharge", value = "添加/修改快递运费模板", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = ShopDeliveryTemplateCharge.class)
    @RequestMapping("/post/DeliveryTemplateController/writeShopDeliveryTemplateCharge")
    public void writeShopDeliveryTemplateCharge(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateChargeService.saveOrUpdateEntity(inputObject, outputObject);
    }


    /**
     * 批量删除快递运费费用模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteShopDeliveryTemplateChargeByIds", value = "批量删除快递运费模版信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "ids", name = "ids", value = "主键id列表，多个id用逗号分隔", required = "required")})
    @RequestMapping("/post/ShopDeliveryTemplateChargeController/deleteShopDeliveryTemplateChargeByIds")
    public void deleteShopDeliveryTemplateChargeByIds(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateChargeService.deleteById(inputObject, outputObject);
    }

    /**
     * 分页查询快递运费费用模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryShopDeliveryTemplateCharge", value = "分页查询快递运费模版信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/shopDeliveryTemplateChargeController/queryShopDeliveryTemplateCharge")
    public void queryShopDeliveryTemplateChargeList(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateChargeService.queryPageList(inputObject, outputObject);
    }

    /**
     * 获取快递运费费用模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryShopDeliveryTemplateChargeList", value = "获取快递运费模版信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "templateId", name = "templateId", value = "模版id"),
            @ApiImplicitParam(id = "areaId", name = "areaId", value = "区域id，集合")})
    @RequestMapping("/post/shopDeliveryTemplateChargeController/queryShopDeliveryTemplateChargeList")
    public void queryAdsenseList(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateChargeService.queryList(inputObject, outputObject);
    }

    /**
     * 根据id获取快递运费费用模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "selectShopDeliveryTemplateChargeById", value = "根据id获取快递运费模版信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/shopDeliveryTemplateChargeController/selectShopDeliveryTemplateChargeById")
    public void selectShopDeliveryTemplateChargeById(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateChargeService.selectById(inputObject, outputObject);
    }


    /**
     * 获取精简的快递运费费用模版信息，主要用于下拉列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "shopDeliveryTemplateChargeAllSimple",value = "获取精简的快递运费模版信息，主要用于下拉列表",method = "GET",allUse = "2")
    @ApiImplicitParams(classBean = ShopDeliveryTemplateChargeVo.class)
    @RequestMapping("/post/shopDeliveryTemplateChargeController/deliveryShopDeliveryTemplateCharge")
    public void streamlineShopDeliveryTemplateChargeList(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateChargeService.shopDeliveryTemplateChargeList(inputObject,outputObject);
    }


}
