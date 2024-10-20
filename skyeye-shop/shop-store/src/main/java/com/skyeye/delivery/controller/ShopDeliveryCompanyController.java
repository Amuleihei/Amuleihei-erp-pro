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
import com.skyeye.delivery.entity.ShopDeliveryCompany;
import com.skyeye.delivery.service.ShopDeliveryCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ShopDeliveryCompanyController
 * @Description: 快递公司管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/4 10:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "快递公司管理", tags = "快递公司管理", modelName = "快递公司管理")
public class ShopDeliveryCompanyController {

    @Autowired
    private ShopDeliveryCompanyService shopDeliveryCompanyService;

    /**
     * 分页获取快递公司信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDeliveryList", value = "分页获取快递公司信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ShopDeliveryCompanyController/queryDeliveryList")
    public void queryMemberLevelList(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryCompanyService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加/编辑快递公司
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeDelivery", value = "添加/编辑快递公司", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = ShopDeliveryCompany.class)
    @RequestMapping("/post/ShopDeliveryCompanyController/writeDelivery")
    public void writeDelivery(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryCompanyService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 批量删除快递公司信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteDeliveryById", value = "批量删除快递公司信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({@ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ShopDeliveryCompanyController/deleteDeliveryById")
    public void deleteDeliveryByIds(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryCompanyService.deleteByIds(inputObject, outputObject);
    }


    /**
     * 获取全部已启用广告位管理信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDelivery", value = "获取全部已启用广告位管理信息", method = "POST", allUse = "0")
    @RequestMapping("/post/ShopDeliveryCompanyController/queryDelivery")
    public void queryDelivery(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryCompanyService.queryList(inputObject, outputObject);
    }
}
