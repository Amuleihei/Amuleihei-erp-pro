/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 */

package com.skyeye.farm.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.farm.entity.Farm;
import com.skyeye.farm.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: FarmController
 * @Description: 加工车间管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2020/8/30 14:09
 * @Copyright: 2020 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "车间管理", tags = "车间管理", modelName = "车间管理")
public class FarmController {

    @Autowired
    private FarmService farmService;

    /**
     * 获取车间列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "erpfarm001", value = "获取车间列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/FarmController/queryFarmList")
    public void queryFarmList(InputObject inputObject, OutputObject outputObject) {
        farmService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑车间信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeFarm", value = "新增/编辑车间信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = Farm.class)
    @RequestMapping("/post/FarmController/writeFarm")
    public void writeFarm(InputObject inputObject, OutputObject outputObject) {
        farmService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id获取车间信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryFarmById", value = "根据id获取车间信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/FarmController/queryFarmById")
    public void queryFarmById(InputObject inputObject, OutputObject outputObject) {
        farmService.selectById(inputObject, outputObject);
    }

    /**
     * 根据ID删除车间信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteFarmById", value = "根据ID删除车间信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/FarmController/deleteFarmById")
    public void deleteFarmById(InputObject inputObject, OutputObject outputObject) {
        farmService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取我负责的车间信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryMyChargeFarmList", value = "获取我负责的车间信息", method = "GET", allUse = "2")
    @RequestMapping("/post/FarmController/queryMyChargeFarmList")
    public void queryMyChargeFarmList(InputObject inputObject, OutputObject outputObject) {
        farmService.queryMyChargeFarmList(inputObject, outputObject);
    }

    /**
     * 获取所有启用的车间信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryEnabledFarmList", value = "获取所有启用的车间信息", method = "GET", allUse = "2")
    @RequestMapping("/post/FarmController/queryEnabledFarmList")
    public void queryEnabledFarmList(InputObject inputObject, OutputObject outputObject) {
        farmService.queryEnabledFarmList(inputObject, outputObject);
    }

}
