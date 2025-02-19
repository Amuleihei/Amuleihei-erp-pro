/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.activiti.cmd.rollback.impl;

import com.skyeye.activiti.cmd.rollback.AbstractGateWayRollbackOperateStrategy;
import com.skyeye.activiti.cmd.rollback.RollbackConstants;
import com.skyeye.activiti.cmd.rollback.RollbackParamsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.task.api.history.HistoricTaskInstance;

/**
 * @ClassName: DefaultTaskNextGatewayRollbackOperateStrategy
 * @Description: 处理多级网关
 * @author: skyeye云系列--卫志强
 * @date: 2021/12/26 19:45
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Slf4j
public class DefaultTaskNextGatewayRollbackOperateStrategy extends AbstractGateWayRollbackOperateStrategy {

    public DefaultTaskNextGatewayRollbackOperateStrategy(RollbackParamsTemplate paramsTemplate) {
        super(paramsTemplate);
    }

    @Override
    public void createExecution() {
        HistoricTaskInstance hisTask = paramsTemplate.getHisTask();

        // 获取正在执行 execution
        ExecutionEntity executionEntity = getExecutionEntity();

        ExecutionEntity newExecution = CommandContextUtil.getExecutionEntityManager(commandContext)
            .createChildExecution(executionEntity.getParent());
        // 创建新任务
        createExecution(newExecution);
        // 特殊处理并行网关
        processGateway(executionEntity.getParent());

        // 移除历史任务
        removeHisTask(hisTask);

    }

    @Override
    public void setAssignee() {
        // 进行任务执行人配置,之后使用全局监听出发更新
        super.setAssignee();
        String type = RollbackConstants.TASK_TYPE_PREFIX_KEY + paramsTemplate.getHisTask().getProcessInstanceId()
            + paramsTemplate.getHisTask().getTaskDefinitionKey();
        variables.put(type, DefaultTaskNextGatewayRollbackOperateStrategy.class.getSimpleName());
    }

}
