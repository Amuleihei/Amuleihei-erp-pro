/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.activiti.controller;

import com.skyeye.activiti.service.ActivitiProcessService;
import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.flowable.entity.FlowableSubData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ActivitiProcessController
 * @Description: 工作流流程相关操作
 * @author: skyeye云系列--卫志强
 * @date: 2021/12/2 21:28
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "工作流流程相关操作", tags = "工作流流程相关操作", modelName = "工作流模块")
public class ActivitiProcessController {

    @Autowired
    private ActivitiProcessService activitiProcessService;

    /**
     * 流程挂起
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ActivitiProcessController/updateProcessToHangUp")
    public void updateProcessToHangUp(InputObject inputObject, OutputObject outputObject) {
        activitiProcessService.updateProcessToHangUp(inputObject, outputObject);
    }

    /**
     * 流程激活
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ActivitiProcessController/updateProcessToActivation")
    public void updateProcessToActivation(InputObject inputObject, OutputObject outputObject) {
        activitiProcessService.updateProcessToActivation(inputObject, outputObject);
    }

    /**
     * 流程撤回
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ActivitiProcessController/editProcessInstanceWithDraw")
    public void editProcessInstanceWithDraw(InputObject inputObject, OutputObject outputObject) {
        activitiProcessService.editProcessInstanceWithDraw(inputObject, outputObject);
    }

    /**
     * 刷新流程图
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ActivitiProcessController/editProcessInstancePicToRefresh")
    public void editProcessInstancePicToRefresh(InputObject inputObject, OutputObject outputObject) {
        activitiProcessService.editProcessInstancePicToRefresh(inputObject, outputObject);
    }

    /**
     * 获取流程下一个节点的审批人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ActivitiProcessController/nextPrcessApprover")
    public void nextPrcessApprover(InputObject inputObject, OutputObject outputObject) {
        activitiProcessService.nextPrcessApprover(inputObject, outputObject);
    }

    /**
     * 启动流程时获取流程下一个用户节点的审批人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "activitiProcess002", value = "启动流程时获取流程下一个用户节点的审批人", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "actKey", name = "actKey", value = "工作流模型的key", required = "required"),
        @ApiImplicitParam(id = "businessData", name = "businessData", value = "业务数据,根据不同的业务数据走不同的工作流")})
    @RequestMapping("/post/ActivitiProcessController/nextPrcessApproverByProcessDefinitionKey")
    public void nextPrcessApproverByProcessDefinitionKey(InputObject inputObject, OutputObject outputObject) {
        activitiProcessService.nextPrcessApproverByProcessDefinitionKey(inputObject, outputObject);
    }

    /**
     * 启动流程--基础服务
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "startProcess", value = "启动流程", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = FlowableSubData.class)
    @RequestMapping("/post/ActivitiProcessController/startProcess")
    public void startProcess(InputObject inputObject, OutputObject outputObject) {
        activitiProcessService.startProcess(inputObject, outputObject);
    }

    /**
     * 撤销流程
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "revokeProcess", value = "撤销流程", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "processInstanceId", name = "processInstanceId", value = "流程实例id", required = "required")})
    @RequestMapping("/post/ActivitiProcessController/revokeProcess")
    public void revokeProcess(InputObject inputObject, OutputObject outputObject) {
        activitiProcessService.revokeProcess(inputObject, outputObject);
    }

    /**
     * 根据流程实例id获取流程信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryProcessInstance", value = "根据流程实例id获取流程信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "processInstanceId", name = "processInstanceId", value = "流程实例id", required = "required")})
    @RequestMapping("/post/ActivitiProcessController/queryProcessInstance")
    public void queryProcessInstance(InputObject inputObject, OutputObject outputObject) {
        activitiProcessService.queryProcessInstance(inputObject, outputObject);
    }

}
