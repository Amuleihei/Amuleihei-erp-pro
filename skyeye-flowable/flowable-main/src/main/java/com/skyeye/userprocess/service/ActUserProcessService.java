/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.userprocess.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.userprocess.entity.ActUserProcess;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ActUserProcessService
 * @Description: 用户启动的流程管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/12/18 10:32
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ActUserProcessService extends SkyeyeBusinessService<ActUserProcess> {

    void deleteByProcessInstanceId(String processInstanceId);

    void saveActUserProcess(String processInstanceId, String actFlowId, String objectId, String objectKey, String appId);

    ActUserProcess selectByProcessInstanceId(String processInstanceId);

    Map<String, ActUserProcess> selectByProcessInstanceId(List<String> processInstanceIds);


}
