/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.store.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.store.entity.ShopArea;
import com.skyeye.store.service.ShopAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ShopAreaController
 * @Description: 区域管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/4 10:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "区域管理", tags = "区域管理", modelName = "区域管理")
public class ShopAreaController {

    @Autowired
    private ShopAreaService shopAreaService;

    /**
     * 获取区域信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "area001", value = "获取区域信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/AreaController/queryAreaList")
    public void queryAreaList(InputObject inputObject, OutputObject outputObject) {
        shopAreaService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加/编辑区域
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeShopArea", value = "添加/编辑区域", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = ShopArea.class)
    @RequestMapping("/post/AreaController/writeShopArea")
    public void writeShopArea(InputObject inputObject, OutputObject outputObject) {
        shopAreaService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id删除区域信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteAreaById", value = "根据id删除区域信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AreaController/deleteAreaById")
    public void deleteAreaById(InputObject inputObject, OutputObject outputObject) {
        shopAreaService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取区域列表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAllEnabledAreaList", value = "获取区域列表信息", method = "GET", allUse = "2")
    @RequestMapping("/post/AreaController/queryAllEnabledAreaList")
    public void queryAllEnabledAreaList(InputObject inputObject, OutputObject outputObject) {
        shopAreaService.queryAllEnabledAreaList(inputObject, outputObject);
    }

}
