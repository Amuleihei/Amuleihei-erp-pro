/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.activiti.cmd.multiinstanceexecution;

import com.skyeye.common.constans.ActivitiConstants;
import com.skyeye.common.util.ToolUtil;
import org.flowable.common.engine.impl.interceptor.Command;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @ClassName: NewMultiInstanceExecutionSetAssignee
 * @Description: flowable 新增会签节点后，task的审批人为空的解决办法
 * @author: skyeye云系列--卫志强
 * @date: 2022/1/1 11:55
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class NewMultiInstanceExecutionSetAssignee extends AbstractCountersignCmd implements Command<String> {

    private static Logger LOGGER = LoggerFactory.getLogger(NewMultiInstanceExecutionSetAssignee.class);

    /**
     * 当前任务ID
     */
    private String taskId;

    /**
     * 审核人
     */
    private List<String> assigneeList;

    public NewMultiInstanceExecutionSetAssignee(String taskId, List<String> assigneeList) {
        super();
        if (ObjectUtils.isEmpty(assigneeList)) {
            throw new RuntimeException("assigneeList 不能为空!");
        }
        this.taskId = taskId;
        this.assigneeList = assigneeList;
    }

    @Override
    public String execute(CommandContext commandContext) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list();
        for (Task obj : taskList) {
            if (ToolUtil.isBlank(obj.getAssignee())) {
                ExecutionEntityImpl temp = (ExecutionEntityImpl) runtimeService.createExecutionQuery().executionId(obj.getExecutionId()).singleResult();
                if (assigneeList.contains(temp.getVariableLocal(ActivitiConstants.ASSIGNEE_USER))) {
                    obj.setAssignee(temp.getVariableLocal(ActivitiConstants.ASSIGNEE_USER).toString());
                    taskService.saveTask(obj);
                }
            }
        }
        return null;
    }
}
