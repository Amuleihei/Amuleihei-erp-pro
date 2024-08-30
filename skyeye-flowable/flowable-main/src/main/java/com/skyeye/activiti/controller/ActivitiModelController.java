/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.activiti.controller;

import com.skyeye.activiti.service.ActivitiModelService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ActivitiModelController
 * @Description: 工作流模型操作
 * @author: skyeye云系列--卫志强
 * @date: 2021/12/2 21:37
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
public class ActivitiModelController {

    @Autowired
    private ActivitiModelService activitiModelService;

    /**
     * 发布模型为流程定义
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ActivitiModelController/editActivitiModelToDeploy")
    public void editActivitiModelToDeploy(InputObject inputObject, OutputObject outputObject) {
        activitiModelService.editActivitiModelToDeploy(inputObject, outputObject);
    }

    /**
     * 取消发布
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ActivitiModelController/deleteReleasedActivitiModelById")
    public void deleteReleasedActivitiModelById(InputObject inputObject, OutputObject outputObject) {
        activitiModelService.deleteReleasedActivitiModelById(inputObject, outputObject);
    }

    /**
     * 导出model的xml文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ActivitiModelController/editApprovalActivitiTaskListByUserId")
    public void editApprovalActivitiTaskListByUserId(InputObject inputObject, OutputObject outputObject) {
        activitiModelService.editApprovalActivitiTaskListByUserId(inputObject, outputObject);
    }

}
