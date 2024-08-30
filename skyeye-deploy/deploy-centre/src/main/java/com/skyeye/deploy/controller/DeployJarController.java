/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.deploy.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.deploy.entity.deploy.JavaWebDeployInfo;
import com.skyeye.deploy.service.DeployJarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DeployJarController
 * @Description: 部署jar控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/20 9:27
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "项目部署管理", tags = "项目部署管理", modelName = "部署平台模块")
public class DeployJarController {

    @Autowired
    private DeployJarService deployJarService;

    /**
     * 添加项目
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "insertDeployProject", value = "添加项目", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = JavaWebDeployInfo.class)
    @RequestMapping("/post/DeployJarController/insertDeployProject")
    public void insertDeployProject(InputObject inputObject, OutputObject outputObject) throws Exception{
        deployJarService.insertDeployProject(inputObject, outputObject);
    }

    /**
     * 项目详情
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "queryDeployProject", value = "项目详情", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "项目id", required = "required")})
    @RequestMapping("/post/DeployJarController/queryDeployProject")
    public void queryDeployProject(InputObject inputObject, OutputObject outputObject) throws Exception{
        deployJarService.queryDeployProject(inputObject, outputObject);
    }

}
