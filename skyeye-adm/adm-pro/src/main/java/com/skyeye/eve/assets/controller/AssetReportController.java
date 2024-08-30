/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.assets.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.assets.entity.AssetReportQueryDo;
import com.skyeye.eve.assets.service.AssetReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AssetReportController
 * @Description: 资产明细控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/9 14:49
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "资产明细管理", tags = "资产明细管理", modelName = "资产模块")
public class AssetReportController {

    @Autowired
    private AssetReportService assetReportService;

    /**
     * 获取资产明细一物一码信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAssetReportList", value = "获取资产明细一物一码信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = AssetReportQueryDo.class)
    @RequestMapping("/post/AssetReportController/queryAssetReportList")
    public void queryAssetReportList(InputObject inputObject, OutputObject outputObject) {
        assetReportService.queryPageList(inputObject, outputObject);
    }

    /**
     * 生成条形码
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertAssetReport", value = "生成条形码", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "list", name = "list", value = "商品信息，必须包含assetId,operNumber", required = "required,json")})
    @RequestMapping("/post/AssetReportController/insertAssetReport")
    public void insertAssetReport(InputObject inputObject, OutputObject outputObject) {
        assetReportService.insertAssetReport(inputObject, outputObject);
    }

    /**
     * 删除商品条形码
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteAssetReportById", value = "删除商品条形码", method = "DELETE", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AssetReportController/deleteAssetReportById")
    public void deleteAssetReportById(InputObject inputObject, OutputObject outputObject) {
        assetReportService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据条件获取条形码信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAssetReportCodeList", value = "根据条件获取条形码信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = AssetReportQueryDo.class)
    @RequestMapping("/post/AssetReportController/queryAssetReportCodeList")
    public void queryAssetReportCodeList(InputObject inputObject, OutputObject outputObject) {
        assetReportService.queryAssetReportCodeList(inputObject, outputObject);
    }

}
