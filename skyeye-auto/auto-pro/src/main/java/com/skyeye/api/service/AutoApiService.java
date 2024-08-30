/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.api.service;

import com.skyeye.api.entity.AutoApi;
import com.skyeye.base.business.service.SkyeyeTeamAuthService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.history.entity.AutoHistoryStepApi;

import java.util.Map;

/**
 * @ClassName: AutoApiService
 * @Description: 接口管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/26 8:31
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface AutoApiService extends SkyeyeTeamAuthService<AutoApi> {

    void apiTest(InputObject inputObject, OutputObject outputObject);

    void apiTestById(InputObject inputObject, OutputObject outputObject);

    Map<String, Object> apiTest(String id);

    Map<String, Object> apiTest(AutoApi autoApi, AutoHistoryStepApi autoHistoryStepApi);
}
