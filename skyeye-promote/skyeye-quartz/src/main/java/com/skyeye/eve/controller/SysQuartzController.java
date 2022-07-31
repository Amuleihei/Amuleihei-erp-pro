/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.ehr.language.SysStaffLanguageQueryDo;
import com.skyeye.eve.entity.quartz.SysQuartzMation;
import com.skyeye.eve.service.SysQuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "定时任务", tags = "定时任务", modelName = "定时任务模块")
public class SysQuartzController {

    @Autowired
    private SysQuartzService sysQuartzService;

    /**
     * 获取系统定时任务列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysQuartzController/querySystemQuartzList")
    public void querySystemQuartzList(InputObject inputObject, OutputObject outputObject) {
        sysQuartzService.querySystemQuartzList(inputObject, outputObject);
    }

    /**
     * 获取我的定时任务列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysQuartzController/queryMyTaskQuartzList")
    public void queryMyTaskQuartzList(InputObject inputObject, OutputObject outputObject) {
        sysQuartzService.queryMyTaskQuartzList(inputObject, outputObject);
    }

    /**
     * 启动系统定时任务
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysQuartzController/runSystemQuartz")
    public void runSystemQuartz(InputObject inputObject, OutputObject outputObject) {
        sysQuartzService.runSystemQuartz(inputObject, outputObject);
    }

    /**
     * 启动定时任务
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "startUpTaskQuartz", value = "启动定时任务", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = SysQuartzMation.class)
    @RequestMapping("/post/SysQuartzController/startUpTaskQuartz")
    public void startUpTaskQuartz(InputObject inputObject, OutputObject outputObject) {
        sysQuartzService.startUpTaskQuartz(inputObject, outputObject);
    }

}
