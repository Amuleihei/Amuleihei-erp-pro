/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.operate.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.operate.entity.Operate;
import com.skyeye.operate.service.OperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: OperateController
 * @Description: 操作管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2023/1/29 18:06
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "操作管理", tags = "操作管理", modelName = "系统公共模块")
public class OperateController {

    @Autowired
    private OperateService operateService;

    /**
     * 获取操作列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryOperateList", value = "获取操作列表", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "appId", name = "appId", value = "服务的appId", required = "required"),
        @ApiImplicitParam(id = "className", name = "className", value = "service的className", required = "required")})
    @RequestMapping("/post/OperateController/queryOperateList")
    public void queryOperateList(InputObject inputObject, OutputObject outputObject) {
        operateService.queryOperateList(inputObject, outputObject);
    }

    /**
     * 新增/编辑操作
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeOperate", value = "新增/编辑操作", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Operate.class)
    @RequestMapping("/post/OperateController/writeOperate")
    public void writeOperate(InputObject inputObject, OutputObject outputObject) {
        operateService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id查看操作信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryOperateById", value = "根据id查看操作信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "操作信息的id", required = "required")})
    @RequestMapping("/post/OperateController/queryOperateById")
    public void queryOperateById(InputObject inputObject, OutputObject outputObject) {
        operateService.selectById(inputObject, outputObject);
    }

    /**
     * 根据id删除操作信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteOperateById", value = "根据id删除操作信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "操作信息的id", required = "required")})
    @RequestMapping("/post/OperateController/deleteOperateById")
    public void deleteOperateById(InputObject inputObject, OutputObject outputObject) {
        operateService.deleteById(inputObject, outputObject);
    }

}
