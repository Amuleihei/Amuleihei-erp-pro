/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.util;

import com.skyeye.common.util.SpringUtils;
import com.skyeye.eve.rest.mq.JobMateUpdateMation;
import com.skyeye.eve.service.IJobMateMationService;

/**
 * @ClassName: MqSendUtil
 * @Description: mq消息相关的工具类
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/3 23:24
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class MqSendUtil {

    /**
     * 修改任务信息
     *
     * @param jobId        任务id
     * @param status       状态
     * @param responseBody 放入任务结果集的值
     */
    public static void comMQJobMation(String jobId, String status, String responseBody) {
        JobMateUpdateMation jobMateUpdateMation = new JobMateUpdateMation();
        jobMateUpdateMation.setJobId(jobId);
        jobMateUpdateMation.setStatus(status);
        jobMateUpdateMation.setResponseBody(responseBody);
        IJobMateMationService iJobMateMationService = SpringUtils.getBean(IJobMateMationService.class);
        iJobMateMationService.comMQJobMation(jobMateUpdateMation);
    }

}
