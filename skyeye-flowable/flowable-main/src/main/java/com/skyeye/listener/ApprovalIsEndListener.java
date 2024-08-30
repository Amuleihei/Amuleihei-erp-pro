/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.listener;

import cn.hutool.core.util.ObjectUtil;
import com.skyeye.common.util.SpringUtils;
import com.skyeye.exception.CustomException;
import com.skyeye.sdk.data.entity.ApprovalEnd;
import com.skyeye.sdk.data.service.IDataService;
import com.skyeye.userprocess.entity.ActUserProcess;
import com.skyeye.userprocess.service.ActUserProcessService;
import lombok.SneakyThrows;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @ClassName: ApprovalIsEndListener
 * @Description: 工作流审批结束的监听类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/4 17:33
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class ApprovalIsEndListener implements JavaDelegate {

    /**
     * 值为pass，则通过，为nopass，则不通过
     */
    private Expression state;

    private IDataService iDataService;

    private ActUserProcessService actUserProcessService;

    public ApprovalIsEndListener() {
        iDataService = SpringUtils.getBean(IDataService.class);
        actUserProcessService = SpringUtils.getBean(ActUserProcessService.class);
    }

    @Override
    @SneakyThrows(value = {Exception.class})
    public void execute(DelegateExecution execution) {
        String processInstanceId = execution.getProcessInstanceId();
        // 审批结果
        String result = (String) state.getValue(execution);

        ActUserProcess actUserProcess = actUserProcessService.selectByProcessInstanceId(processInstanceId);
        if (ObjectUtil.isEmpty(actUserProcess)) {
            throw new CustomException("流程信息不存在.");
        }

        // 调用审批结果处理的函数
        ApprovalEnd approvalEnd = new ApprovalEnd();
        approvalEnd.setProcessInstanceId(processInstanceId);
        approvalEnd.setResult(result);
        approvalEnd.setServiceClassName(actUserProcess.getObjectKey());
        approvalEnd.setAppId(actUserProcess.getAppId());
        iDataService.approvalEnd(approvalEnd);
    }

}
