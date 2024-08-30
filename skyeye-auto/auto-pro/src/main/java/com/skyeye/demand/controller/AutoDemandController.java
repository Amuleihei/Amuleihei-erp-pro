/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye-report
 ******************************************************************************/

package com.skyeye.demand.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.demand.entity.AutoDemand;
import com.skyeye.demand.entity.AutoDemandQueryDo;
import com.skyeye.demand.service.AutoDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AutoDemandController
 * @Description: 需求表控制层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:17
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
@RestController
@Api(value = "需求管理", tags = "需求管理", modelName = "需求管理")
public class AutoDemandController {

    @Autowired
    private AutoDemandService autoDemandService;

    /**
     * 获取需求表列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAutoDemandList", value = "获取需求表列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = AutoDemandQueryDo.class)
    @RequestMapping("/post/AutoDemandController/queryAutoDemandList")
    public void queryAutoDemandList(InputObject inputObject, OutputObject outputObject) {
        autoDemandService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑需求表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeAutoDemand", value = "新增/编辑需求表信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = AutoDemand.class)
    @RequestMapping("/post/AutoDemandController/writeAutoDemand")
    public void writeAutoDemand(InputObject inputObject, OutputObject outputObject) {
        autoDemandService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id删除需求表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteAutoDemandById", value = "根据id删除需求表信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AutoDemandController/deleteAutoDemandById")
    public void deleteAutoDemandById(InputObject inputObject, OutputObject outputObject) {
        autoDemandService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id查询需求表配置信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAutoDemandById", value = "根据id查询需求表配置信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AutoDemandController/queryAutoDemandById")
    public void queryAutoDemandById(InputObject inputObject, OutputObject outputObject) {
        autoDemandService.selectById(inputObject, outputObject);
    }

    /**
     * 更新需求状态
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "updateStateAutoDemandById", value = "更新需求状态", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/UserController/updateStateAutoDemandById")
    public void updateStateAutoDemandById(InputObject inputObject, OutputObject outputObject) {
        autoDemandService.updateStateAutoDemandById(inputObject, outputObject);
    }

    /**
     * 作废需求
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "invalidAutoDemandById", value = "作废需求", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/UserController/invalidAutoDemandById")
    public void invalidAutoDemandById(InputObject inputObject, OutputObject outputObject) {
        autoDemandService.invalidAutoDemandById(inputObject, outputObject);
    }





}

