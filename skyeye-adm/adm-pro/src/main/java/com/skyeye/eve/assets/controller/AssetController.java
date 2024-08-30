/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.assets.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.assets.entity.Asset;
import com.skyeye.eve.assets.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AssetController
 * @Description: 资产管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/9 10:15
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "资产管理", tags = "资产管理", modelName = "资产模块")
public class AssetController {

    @Autowired
    private AssetService assetService;

    /**
     * 获取所有的资产
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "asset001", value = "获取所有的资产", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/AssetController/selectAllAssetMation")
    public void queryAssetMationList(InputObject inputObject, OutputObject outputObject) {
        assetService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑资产
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeAssetMation", value = "新增/编辑资产", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = Asset.class)
    @RequestMapping("/post/AssetController/writeAssetMation")
    public void writeAssetMation(InputObject inputObject, OutputObject outputObject) {
        assetService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除资产
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "asset003", value = "删除资产", method = "DELETE", allUse = "1")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AssetController/deleteAssetById")
    public void deleteAssetById(InputObject inputObject, OutputObject outputObject) {
        assetService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id查询资产
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAssetById", value = "根据id查询资产", method = "GET", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AssetController/queryAssetById")
    public void queryAssetById(InputObject inputObject, OutputObject outputObject) {
        assetService.selectById(inputObject, outputObject);
    }

}
