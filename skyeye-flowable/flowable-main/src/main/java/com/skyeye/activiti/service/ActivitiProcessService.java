/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.activiti.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.activiti.entity.NextTaskInfo;

import java.util.Map;

/**
 * @ClassName: ActivitiProcessService
 * @Description: 工作流流程相关操作
 * @author: skyeye云系列--卫志强
 * @date: 2021/12/2 21:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ActivitiProcessService {

    void updateProcessToHangUp(InputObject inputObject, OutputObject outputObject);

    void updateProcessToActivation(InputObject inputObject, OutputObject outputObject);

    void editProcessInstanceWithDraw(InputObject inputObject, OutputObject outputObject);

    void editProcessInstancePicToRefresh(InputObject inputObject, OutputObject outputObject);

    void nextPrcessApprover(InputObject inputObject, OutputObject outputObject);

    /**
     * 获取下一个用户任务信息
     *
     * @param taskId 任务Id信息
     * @param map    表单数据
     * @return 下一个用户任务用户组信息
     */
    NextTaskInfo getNextTaskInfo(String taskId, Map<String, Object> map);

    void nextPrcessApproverByProcessDefinitionKey(InputObject inputObject, OutputObject outputObject);

    void startProcess(InputObject inputObject, OutputObject outputObject);

    void revokeProcess(InputObject inputObject, OutputObject outputObject);

    void queryProcessInstance(InputObject inputObject, OutputObject outputObject);
}
