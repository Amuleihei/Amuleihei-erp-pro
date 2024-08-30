/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.depot.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.depot.entity.DepotLevel;
import com.skyeye.depot.service.DepotLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DepotLevelController
 * @Description: 仓库级别控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/5 22:02
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "仓库级别管理", tags = "仓库级别管理", modelName = "仓库级别管理")
public class DepotLevelController {

    @Autowired
    private DepotLevelService depotLevelService;

    /**
     * 新增/编辑仓库级别信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeDepotLevel", value = "新增/编辑仓库级别信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = DepotLevel.class)
    @RequestMapping("/post/DepotLevelController/writeDepotLevel")
    public void writeDepotLevel(InputObject inputObject, OutputObject outputObject) {
        depotLevelService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id获取仓库级别信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDepotLevelById", value = "根据id获取仓库级别信息", method = "GET", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/DepotLevelController/queryDepotLevelById")
    public void queryDepotLevelById(InputObject inputObject, OutputObject outputObject) {
        depotLevelService.selectById(inputObject, outputObject);
    }

    /**
     * 删除仓库级别信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteDepotLevelById", value = "删除仓库级别信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/DepotLevelController/deleteDepotLevelById")
    public void deleteDepotLevelById(InputObject inputObject, OutputObject outputObject) {
        depotLevelService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据仓库id查询仓库级别信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDepotLevelByDepotId", value = "根据仓库id查询仓库级别信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "depotId", name = "depotId", value = "仓库id", required = "required")})
    @RequestMapping("/post/DepotLevelController/queryDepotLevelByDepotId")
    public void queryDepotLevelByDepotId(InputObject inputObject, OutputObject outputObject) {
        depotLevelService.queryDepotLevelByDepotId(inputObject, outputObject);
    }

}
