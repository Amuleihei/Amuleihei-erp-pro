/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ShopDeliveryTemplateChargeController
 * @Description: 快递运费费用模版控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/4 10:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "快递运费费用模版", tags = "快递运费费用模版", modelName = "快递运费费用模版")
public class ShopDeliveryTemplateChargeController {

    @Autowired
    private ShopDeliveryTemplateChargeService shopDeliveryTemplateChargeService;

    /**
     * 新增/编辑快递运费费用模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeShopDeliveryTemplateCharge", value = "添加/修改快递运费模板", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = ShopDeliveryTemplateCharge.class)
    @RequestMapping("/post/shopDeliveryTemplateChargeController/writeShopDeliveryTemplateCharge")
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
    @RequestMapping("/post/shopDeliveryTemplateChargeController/deleteShopDeliveryTemplateChargeByIds")
    public void deleteShopDeliveryTemplateChargeByIds(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateChargeService.deleteByIds(inputObject, outputObject);
    }

    /**
     * 分页查询快递运费费用模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryShopDeliveryTemplateChargeList", value = "分页查询快递运费模版信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/shopDeliveryTemplateChargeController/queryShopDeliveryTemplateChargeList")
    public void queryShopDeliveryTemplateChargeList(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateChargeService.queryPageList(inputObject, outputObject);
    }

    /**
     * 获取已启用的快递运费费用模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryShopDeliveryTemplateCharge", value = "获取全部快递运费费用模版信息", method = "POST", allUse = "0")
    @RequestMapping("/post/shopDeliveryTemplateChargeController/queryShopDeliveryTemplateCharge")
    public void queryShopDeliveryTemplateCharge(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateChargeService.queryList(inputObject, outputObject);
    }

    /**
     * 根据id获取快递运费模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryShopDeliveryTemplateChargeById", value = "根据id获取快递运费模版信息", method = "POST", allUse = "2")
    @ApiImplicitParams({@ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/shopDeliveryTemplateChargeController/queryShopDeliveryTemplateChargeById")
    public void queryShopDeliveryTemplateChargeById(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateChargeService.selectById(inputObject, outputObject);
    }
}
