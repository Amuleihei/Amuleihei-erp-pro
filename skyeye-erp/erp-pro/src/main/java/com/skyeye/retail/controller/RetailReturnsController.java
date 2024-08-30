/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.retail.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.depot.entity.DepotPut;
import com.skyeye.retail.entity.RetailReturns;
import com.skyeye.retail.service.RetailReturnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: RetailReturnsController
 * @Description: 零售退货单管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/8 21:15
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "零售退货单", tags = "零售退货单", modelName = "零售模块")
public class RetailReturnsController {

    @Autowired
    private RetailReturnsService retailReturnsService;

    /**
     * 获取零售退货单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "retailreturns001", value = "获取零售退货单列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/RetailReturnsController/queryRetailReturnsList")
    public void queryRetailReturnsList(InputObject inputObject, OutputObject outputObject) {
        retailReturnsService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑零售退货单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeRetailReturns", value = "新增/编辑零售退货单", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = RetailReturns.class)
    @RequestMapping("/post/RetailReturnsController/writeRetailReturns")
    public void writeRetailReturns(InputObject inputObject, OutputObject outputObject) {
        retailReturnsService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 转仓库入库单时，根据id查询零售退货信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryRetailReturnsTransById", value = "转仓库入库单时，根据id查询零售退货信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/RetailReturnsController/queryRetailReturnsTransById")
    public void queryRetailReturnsTransById(InputObject inputObject, OutputObject outputObject) {
        retailReturnsService.queryRetailReturnsTransById(inputObject, outputObject);
    }

    /**
     * 零售退货单信息转仓库入库单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertRetailReturnsToTurnDepot", value = "零售退货单信息转仓库入库单", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = DepotPut.class, value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/RetailReturnsController/insertRetailReturnsToTurnDepot")
    public void insertRetailReturnsToTurnDepot(InputObject inputObject, OutputObject outputObject) {
        retailReturnsService.insertRetailReturnsToTurnDepot(inputObject, outputObject);
    }

}
