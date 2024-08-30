/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.machinprocedure.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.machinprocedure.service.MachinProcedureFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MachinProcedureFarmController
 * @Description: 车间任务控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/24 20:15
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "车间任务", tags = "车间任务", modelName = "车间任务")
public class MachinProcedureFarmController {

    @Autowired
    private MachinProcedureFarmService machinProcedureFarmService;

    /**
     * 获取车间任务信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryMachinProcedureFarmList", value = "获取车间任务信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/MachinProcedureFarmController/queryMachinProcedureFarmList")
    public void queryMachinProcedureFarmList(InputObject inputObject, OutputObject outputObject) {
        machinProcedureFarmService.queryPageList(inputObject, outputObject);
    }

    /**
     * 车间任务接收
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "receiveMachinProcedureFarm", value = "车间任务接收", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/MachinProcedureFarmController/receiveMachinProcedureFarm")
    public void receiveMachinProcedureFarm(InputObject inputObject, OutputObject outputObject) {
        machinProcedureFarmService.receiveMachinProcedureFarm(inputObject, outputObject);
    }

    /**
     * 车间任务反接收
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "receptionReceiveMachinProcedureFarm", value = "车间任务反接收", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/MachinProcedureFarmController/receptionReceiveMachinProcedureFarm")
    public void receptionReceiveMachinProcedureFarm(InputObject inputObject, OutputObject outputObject) {
        machinProcedureFarmService.receptionReceiveMachinProcedureFarm(inputObject, outputObject);
    }

}
