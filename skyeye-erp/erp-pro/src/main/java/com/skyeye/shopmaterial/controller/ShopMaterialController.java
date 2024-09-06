/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.shopmaterial.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.shopmaterial.entity.ShopMaterial;
import com.skyeye.shopmaterial.service.ShopMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ShopMaterialController
 * @Description: 商城商品控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/4 17:57
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "商城商品", tags = "商城商品", modelName = "商城商品")
public class ShopMaterialController {

    @Autowired
    private ShopMaterialService shopMaterialService;

    @ApiOperation(id = "queryTransMaterialById", value = "根据id获取商品信息，用于上架商城", method = "GET", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "商品id", required = "required")})
    @RequestMapping("/post/ShopMaterialController/queryTransMaterialById")
    public void queryTransMaterialById(InputObject inputObject, OutputObject outputObject) {
        shopMaterialService.queryTransMaterialById(inputObject, outputObject);
    }

    @ApiOperation(id = "saveShopMaterial", value = "根据id获取商品信息，用于上架商城", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = ShopMaterial.class)
    @RequestMapping("/post/ShopMaterialController/saveShopMaterial")
    public void saveShopMaterial(InputObject inputObject, OutputObject outputObject) {
        shopMaterialService.saveOrUpdateEntity(inputObject, outputObject);
    }

}
