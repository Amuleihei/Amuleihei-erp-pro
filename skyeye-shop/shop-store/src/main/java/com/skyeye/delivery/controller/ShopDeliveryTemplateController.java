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

import com.skyeye.delivery.entity.ShopDeliveryTemplate;
import com.skyeye.delivery.service.ShopDeliveryTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ShopDeliveryTemplateController
 * @Description: 快递运费模版控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/4 10:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
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
        shopDeliveryTemplateService.deleteByIds(inputObject, outputObject);
    }

    /**
     * 分页查询快递运费模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryShopDeliveryTemplateList", value = "分页查询快递运费模版信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ShopDeliveryTemplateController/queryShopDeliveryTemplateList")
    public void queryShopDeliveryTemplateList(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateService.queryPageList(inputObject, outputObject);
    }

    /**
     * 获取全部已启用的快递运费模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryShopDeliveryTemplate", value = "获取全部快递运费模版信息", method = "POST", allUse = "0")
    @RequestMapping("/post/ShopDeliveryTemplateController/queryShopDeliveryTemplate")
    public void queryShopDeliveryTemplate(InputObject inputObject, OutputObject outputObject) {
        shopDeliveryTemplateService.queryList(inputObject, outputObject);
    }
}
