package com.skyeye.delivery.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.delivery.entity.ShopDeliveryCompany;
import com.skyeye.delivery.service.ShopDeliveryCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "快递公司管理", tags = "快递公司管理", modelName = "快递公司管理")
public class ShopDeliveryCompanyController {

    @Autowired
    private ShopDeliveryCompanyService deliveryService;

    /**
     * 获取快递公司信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDeliveryList", value = "获取快递公司信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ShopDeliveryController/queryDeliveryList")
    public void queryMemberLevelList(InputObject inputObject, OutputObject outputObject) {
        deliveryService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加/编辑快递公司
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeDelivery", value = "添加/编辑快递公司", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = ShopDeliveryCompany.class)
    @RequestMapping("/post/ShopDeliveryController/writeDelivery")
    public void writeDelivery(InputObject inputObject, OutputObject outputObject) {
        deliveryService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id删除快递公司信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteDeliveryById", value = "根据id删除快递公司信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({@ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ShopDeliveryController/deleteDeliveryById")
    public void deleteDeliveryById(InputObject inputObject, OutputObject outputObject) {
        deliveryService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据ID获取快递公司
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "getDelivery", value = "根据ID获取快递公司", method = "GET", allUse = "2")
    @ApiImplicitParams({@ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ShopDeliveryController/getDelivery")
    public void queryDeliveryByIds(InputObject inputObject, OutputObject outputObject) {
        deliveryService.selectById(inputObject, outputObject);
    }

    /**
     * 获取精简的快递公司信息，主要用于下拉列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deliveryListAllSimple",value = "获取精简的快递公司信息，主要用于下拉列表",method = "GET",allUse = "2")
    @RequestMapping("/post/ShopDeliveryController/deliveryListAllSimple")
    public void streamlineDeliveryList(InputObject inputObject, OutputObject outputObject) {
        deliveryService.streamlineDeliveryList(inputObject,outputObject);
    }
}
