/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.activiti.cmd.multiinstanceexecution;

import com.skyeye.activiti.cmd.rollback.RollbackConstants;
import com.skyeye.common.constans.ActivitiConstants;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.UserTask;
import org.flowable.common.engine.impl.interceptor.Command;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.impl.bpmn.behavior.ParallelMultiInstanceBehavior;
import org.flowable.engine.impl.bpmn.behavior.SequentialMultiInstanceBehavior;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.flowable.task.api.Task;
import org.flowable.task.service.impl.persistence.entity.TaskEntityImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: HandlerMultiInstanceExecutionCmd
 * @Description: 任务完成时，完成会签子任务
 * @author: skyeye云系列--卫志强
 * @date: 2022/1/2 13:25
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class HandlerMultiInstanceExecutionCmd extends AbstractCountersignCmd implements Command<Boolean> {

    private static Logger LOGGER = LoggerFactory.getLogger(HandlerMultiInstanceExecutionCmd.class);

    /**
     * 当前任务ID
     */
    private String taskId;

    private Map<String, Object> user;

    private boolean flag;

    private String opinion;

    public HandlerMultiInstanceExecutionCmd(String taskId, Map<String, Object> user, boolean flag, String opinion) {
        super();
        if (ObjectUtils.isEmpty(taskId)) {
            throw new RuntimeException("taskId 不能为空!");
        }

        this.taskId = taskId;
        this.user = user;
        this.flag = flag;
        this.opinion = opinion;
    }

    @Override
    public Boolean execute(CommandContext commandContext) {
        TaskEntityImpl task = (TaskEntityImpl) taskService.createTaskQuery().taskId(taskId).singleResult();

        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        org.flowable.bpmn.model.Process process = bpmnModel.getProcesses().get(0);
        UserTask userTask = (UserTask) process.getFlowElement(task.getTaskDefinitionKey());
        if (userTask.getLoopCharacteristics() == null) {
            LOGGER.info("task:[" + task.getId() + "] 不是会签节任务");
            return true;
        }

        ExecutionEntityImpl execution = (ExecutionEntityImpl) runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
        Object behavior = userTask.getBehavior();
        /**
         *  进行并行任务 完成多余子任务
         */
        if (behavior instanceof ParallelMultiInstanceBehavior) {
            LOGGER.info("task:[" + task.getId() + "] 并行会签 完成多余子任务");
            if (execution.getVariableLocal(ActivitiConstants.PARALLEL_MULTILN_STANCE_EXECTTION_CHILD) != null) {
                // 当前任务是子实例不在往下执行
                LOGGER.info("task id [{}] is child ExecutionEntity", task.getId());
            } else {
                // 当前任务列表
                List<Task> taskList = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list();
                for (Task obj : taskList) {
                    if (!obj.getId().equals(taskId)) {
                        taskService.complete(obj.getId());
                    }
                }
            }
        } else if (behavior instanceof SequentialMultiInstanceBehavior) {
            // 获取父级
            ExecutionEntityImpl parentNode = execution.getParent();
            // 设置其他参数信息
            Map<String, Object> variables = runtimeService.getVariables(parentNode.getId());
            Integer nrOfActiveInstances = Integer.parseInt(variables.get(RollbackConstants.MultiInstanceConstants.NR_OF_ACTIVE_INSTANCES).toString());
            Integer nrOfCompletedInstances = Integer.parseInt(variables.get(RollbackConstants.MultiInstanceConstants.NR_OF_COMPLETE_INSTANCES).toString());
            variables.put(RollbackConstants.MultiInstanceConstants.NR_OF_ACTIVE_INSTANCES, nrOfActiveInstances - 1);
            variables.put(RollbackConstants.MultiInstanceConstants.NR_OF_COMPLETE_INSTANCES, nrOfCompletedInstances + 1);
            runtimeService.setVariables(parentNode.getId(), variables);
            // 获取设置的会签人集合
            List<String> assigneeList =
                (List<String>) runtimeService.getVariable(parentNode.getId(), ActivitiConstants.ASSIGNEE_USER_LIST);
            String assignee = task.getAssignee();
            int index = getCurrentTaskAssigneeIndex(assigneeList, assignee);
            if (index != assigneeList.size() - 1) {
                // 修改审批意见
                List<Map<String, Object>> leaveList = activitiModelService.getUpLeaveList(user.get("id").toString(),
                    user.get("userName").toString(), opinion, flag, task, ActivitiConstants.LeaveType.APPROVAL_COMMENTS.getType());
                runtimeService.setVariable(task.getProcessInstanceId(), ActivitiConstants.PROCESSINSTANCEID_TASK_LEAVE_OPINION_LIST_VARABLES, leaveList);
                taskService.setAssignee(task.getId(), assigneeList.get(index + 1));
                return false;
            }
        }

        return true;
    }

    private int getCurrentTaskAssigneeIndex(List<String> assigneeStrList, String assignee) {
        for (int i = 0; i < assigneeStrList.size(); i++) {
            if (assignee.equals(assigneeStrList.get(i))) {
                return i;
            }
        }
        return 0;
    }

}
