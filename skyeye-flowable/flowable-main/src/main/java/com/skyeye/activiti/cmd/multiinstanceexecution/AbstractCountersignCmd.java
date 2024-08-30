/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.activiti.cmd.multiinstanceexecution;

import com.skyeye.activiti.service.ActivitiModelService;
import com.skyeye.activiti.service.ActivitiTaskService;
import com.skyeye.common.util.SpringUtils;
import org.flowable.engine.ManagementService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;

/**
 * @ClassName: AbstractCountersignCmd
 * @Description: 会签加减签功能公共类
 * @author: skyeye云系列--卫志强
 * @date: 2021/12/26 19:10
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public abstract class AbstractCountersignCmd {

    protected RuntimeService runtimeService;

    protected TaskService taskService;

    protected RepositoryService repositoryService;

    protected ActivitiTaskService activitiTaskService;

    protected ManagementService managementService;

    protected ActivitiModelService activitiModelService;

    public AbstractCountersignCmd() {
        runtimeService = SpringUtils.getBean(RuntimeService.class);
        taskService = SpringUtils.getBean(TaskService.class);
        repositoryService = SpringUtils.getBean(RepositoryService.class);
        activitiTaskService = SpringUtils.getBean(ActivitiTaskService.class);
        managementService = SpringUtils.getBean(ManagementService.class);
        activitiModelService = SpringUtils.getBean(ActivitiModelService.class);
    }

}
