/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.activiti.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.ActFlowMation;
import com.skyeye.eve.flowable.entity.FlowableSubData;
import org.flowable.task.api.Task;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ActivitiModelService
 * @Description: 工作流模型操作
 * @author: skyeye云系列--卫志强
 * @date: 2021/12/2 21:37
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ActivitiModelService {

    String insertNewActivitiModel(String modelName, String modelKey);

    void setActivitiModelList(List<Map<String, Object>> actFlowList);

    void editActivitiModelToDeploy(InputObject inputObject, OutputObject outputObject);

    void deleteActivitiModelById(String modelId);

    void deleteReleasedActivitiModelById(InputObject inputObject, OutputObject outputObject);

    void editApprovalActivitiTaskListByUserId(InputObject inputObject, OutputObject outputObject);

    /**
     * 获取指定任务节点的变换信息
     *
     * @param approvedId   审批人id
     * @param approvedName 审批人名字
     * @param opinion      审批意见
     * @param flag         该节点是否审批通过，true:通过，false:不通过
     * @param task         任务
     * @param type
     * @return
     */
    List<Map<String, Object>> getUpLeaveList(String approvedId, String approvedName, String opinion, Boolean flag, Task task, int type);

    String startProcess(FlowableSubData flowableSubData, ActFlowMation actFlowMation);

    /**
     * 流程图高亮显示
     *
     * @param processInstanceId
     */
    void queryProHighLighted(String processInstanceId);

    void editModelByModelId(String modelId, String modelName, String modelKey);
}
