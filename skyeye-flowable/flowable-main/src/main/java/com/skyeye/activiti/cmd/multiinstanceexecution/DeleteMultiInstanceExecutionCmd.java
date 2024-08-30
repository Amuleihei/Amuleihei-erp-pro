/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.activiti.cmd.multiinstanceexecution;

import com.skyeye.activiti.cmd.rollback.RollbackConstants;
import com.skyeye.common.constans.ActivitiConstants;
import com.skyeye.common.util.ToolUtil;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.UserTask;
import org.flowable.common.engine.impl.interceptor.Command;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.impl.bpmn.behavior.ParallelMultiInstanceBehavior;
import org.flowable.engine.impl.bpmn.behavior.SequentialMultiInstanceBehavior;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityManager;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.task.api.Task;
import org.flowable.task.service.impl.persistence.entity.TaskEntityImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @ClassName: DeleteMultiInstanceExecutionCmd
 * @Description: 进行会签减签 flowable:org.flowable.engine.impl.cmd.DeleteMultiInstanceExecutionCmd
 * @author: skyeye云系列--卫志强
 * @date: 2021/12/26 20:10
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class DeleteMultiInstanceExecutionCmd extends AbstractCountersignCmd implements Command<String> {

    private static Logger LOGGER = LoggerFactory.getLogger(DeleteMultiInstanceExecutionCmd.class);

    /**
     * 当前任务ID
     */
    private String taskId;

    /**
     * 审核人
     */
    private List<String> assigneeList;

    public DeleteMultiInstanceExecutionCmd(String taskId, List<String> assigneeList) {
        super();
        if (ObjectUtils.isEmpty(assigneeList)) {
            throw new RuntimeException("assigneeList 不能为空!");
        }

        this.taskId = taskId;
        this.assigneeList = assigneeList;
    }

    @Override
    public String execute(CommandContext commandContext) {
        TaskEntityImpl task = (TaskEntityImpl) taskService.createTaskQuery().taskId(taskId).singleResult();

        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        org.flowable.bpmn.model.Process process = bpmnModel.getProcesses().get(0);
        UserTask userTask = (UserTask) process.getFlowElement(task.getTaskDefinitionKey());
        if (userTask.getLoopCharacteristics() == null) {
            LOGGER.info("task:[" + task.getId() + "] 不是会签节任务");
        }
        ExecutionEntityImpl execution = (ExecutionEntityImpl) runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
        ExecutionEntityImpl parentNode = execution.getParent();

        /**
         *  获取任务完成数
         */
        int nrOfInstances = (int) runtimeService.getVariable(parentNode.getId(), RollbackConstants.MultiInstanceConstants.NR_OF_INSTANCE);
        int nrOfCompletedInstances = (int) runtimeService.getVariable(parentNode.getId(), RollbackConstants.MultiInstanceConstants.NR_OF_COMPLETE_INSTANCES);

        /**
         *  转换判断标识
         */
        Set<String> assigneeSet = new HashSet<>(assigneeList);
        ExecutionEntityManager executionEntityManager = CommandContextUtil.getExecutionEntityManager(commandContext);

        Object behavior = userTask.getBehavior();
        /**
         *  进行并行任务 减签
         */
        if (behavior instanceof ParallelMultiInstanceBehavior) {
            LOGGER.info("task:[" + task.getId() + "] 并行会签 减签 任务");

            /**
             *  当前任务列表
             */
            List<Task> taskList = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list();

            for (Task obj : taskList) {
                ExecutionEntityImpl temp = (ExecutionEntityImpl) runtimeService.createExecutionQuery().executionId(obj.getExecutionId()).singleResult();
                String objAssignee = getParallelMultiInstanceBehaviorAssignee(obj, temp);
                if (assigneeSet.contains(objAssignee)) {
                    nrOfCompletedInstances++;
                    executionEntityManager.deleteExecutionAndRelatedData(temp, "会签减签", true);
                }
            }

            /**
             *  修改已完成任务变量,增加被删减任务
             */
            runtimeService.setVariable(parentNode.getId(), RollbackConstants.MultiInstanceConstants.NR_OF_COMPLETE_INSTANCES, nrOfCompletedInstances);
            runtimeService.setVariable(parentNode.getId(), RollbackConstants.MultiInstanceConstants.NR_OF_ACTIVE_INSTANCES, nrOfInstances - nrOfCompletedInstances);
        } else if (behavior instanceof SequentialMultiInstanceBehavior) {
            LOGGER.info("task:[" + task.getId() + "] 串行会签 减签 任务");

            Object obj = parentNode.getVariable(ActivitiConstants.ASSIGNEE_USER_LIST);
            if (obj == null || !(obj instanceof ArrayList)) {
                throw new RuntimeException("没有找到任务执行人列表");
            }

            ArrayList<String> sourceAssigneeList = (ArrayList) obj;
            List<String> newAssigneeList = new ArrayList<>();
            boolean flag = false;
            int loopCounterIndex = -1;
            String newAssignee = "";
            for (String temp : sourceAssigneeList) {
                if (!assigneeSet.contains(temp)) {
                    newAssigneeList.add(temp);
                }

                if (flag) {
                    newAssignee = temp;
                    flag = false;
                }

                if (temp.equals(task.getAssignee())) {
                    if (assigneeSet.contains(temp)) {
                        flag = true;
                        loopCounterIndex = newAssigneeList.size();
                    } else {
                        loopCounterIndex = newAssigneeList.size() - 1;
                    }
                }
            }

            /**
             *  修改计数器变量
             */
            Map<String, Object> variables = new HashMap<>();
            variables.put(RollbackConstants.MultiInstanceConstants.NR_OF_INSTANCE, newAssigneeList.size());
            variables.put(RollbackConstants.MultiInstanceConstants.NR_OF_COMPLETE_INSTANCES, loopCounterIndex > 0 ? loopCounterIndex - 1 : 0);
            variables.put(ActivitiConstants.ASSIGNEE_USER_LIST, newAssigneeList);
            runtimeService.setVariables(parentNode.getId(), variables);

            /**
             *  当前任务需要被删除，需要替换下一个任务审批人
             */
            if (!StringUtils.isEmpty(newAssignee)) {
                taskService.setAssignee(taskId, newAssignee);
                execution.setVariable(RollbackConstants.MultiInstanceConstants.LOOP_COUNTER, loopCounterIndex);
                execution.setVariable(ActivitiConstants.ASSIGNEE_USER, newAssignee);
            }
        }
        return "减签成功";
    }

    private String getParallelMultiInstanceBehaviorAssignee(Task task, ExecutionEntityImpl temp) {
        if (ToolUtil.isBlank(task.getAssignee())) {
            LOGGER.info("delete assignee when get assignee user from executionEntity");
            return String.valueOf(temp.getVariableLocal(ActivitiConstants.ASSIGNEE_USER));
        }
        LOGGER.info("delete assignee when get assignee user from task");
        return task.getAssignee();
    }

}
