/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tms.path.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.tms.path.entity.TransportationPath;
import com.skyeye.tms.path.service.TransportationPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TransportationPathController
 * @Description: 运输路径控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/11 22:15
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "运输路径", tags = "运输路径", modelName = "运输路径")
public class TransportationPathController {

    @Autowired
    private TransportationPathService transportationPathService;

    /**
     * 获取运输路径列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryTransportationPathList", value = "获取运输路径列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/TransportationPathController/queryTransportationPathList")
    public void queryTransportationPathList(InputObject inputObject, OutputObject outputObject) {
        transportationPathService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加或修改运输路径
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeTransportationPath", value = "新增/编辑运输路径", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = TransportationPath.class)
    @RequestMapping("/post/TransportationPathController/writeTransportationPath")
    public void writeTransportationPath(InputObject inputObject, OutputObject outputObject) {
        transportationPathService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除运输路径
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteTransportationPathById", value = "根据ID删除运输路径", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/TransportationPathController/deleteTransportationPathById")
    public void deleteTransportationPathById(InputObject inputObject, OutputObject outputObject) {
        transportationPathService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取已启用的运输路径
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryEnabledTransportationPathList", value = "获取已启用的运输路径", method = "GET", allUse = "2")
    @RequestMapping("/post/TmsTransportationPathTypeController/queryEnabledTransportationPathList")
    public void queryEnabledTransportationPathList(InputObject inputObject, OutputObject outputObject) {
        transportationPathService.queryEnabledTransportationPathList(inputObject, outputObject);
    }

}
