/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.activiti.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.skyeye.common.constans.ActivitiConstants;
import com.skyeye.common.util.FileUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.userprocess.entity.ActUserProcess;
import com.skyeye.userprocess.service.ActUserProcessService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 流程撤回所需工具类
 *
 * @author 卫志强
 */
@Component
public class ActivitiService {

    @Value("${IMAGES_PATH}")
    private String tPath;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ActUserProcessService actUserProcessService;

    /**
     * 撤销已经申请的流程
     *
     * @param processInstanceId
     * @param userId
     */
    public void revokeByProcessInstanceId(String processInstanceId, String userId) {
        ActUserProcess userProcess = actUserProcessService.selectByProcessInstanceId(processInstanceId);
        if (ObjectUtil.isEmpty(userProcess)) {
            throw new CustomException("流程信息不存在.");
        }
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        if (task == null) {
            throw new CustomException("该流程未启动或者已结束.");
        }
        String deleteFilePath = String.format("%supload/activiti/%s.png", tPath, processInstanceId);
        FileUtil.deleteFile(deleteFilePath);
        Object o = runtimeService.getVariable(processInstanceId, ActivitiConstants.PROCESSINSTANCEID_TASK_LEAVE_OPINION_LIST_VARABLES);
        boolean edit = true;
        if (o != null) {
            //获取历史审核信息
            List<Map<String, Object>> leaveList = (List<Map<String, Object>>) o;
            for (Map<String, Object> leave : leaveList) {
                if (!userId.equals(leave.get("opId").toString())) {
                    edit = false;//不可编辑
                    break;
                }
            }
        }
        if (!edit) {
            throw new CustomException("流程已有审批，不能撤回.");
        }
        // 可以编辑
        runtimeService.deleteProcessInstance(processInstanceId, "用户撤销");
        // 删除流程历史信息
        historyService.deleteHistoricProcessInstance(processInstanceId);
        // 删除数据库流程信息
        actUserProcessService.deleteByProcessInstanceId(processInstanceId);
    }

}
