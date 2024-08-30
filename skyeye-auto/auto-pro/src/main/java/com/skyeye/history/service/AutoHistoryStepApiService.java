/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.history.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.history.entity.AutoHistoryStepApi;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AutoHistoryStepApiService
 * @Description: API执行历史服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/16 22:30
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface AutoHistoryStepApiService extends SkyeyeBusinessService<AutoHistoryStepApi> {

    void saveList(String objectId, List<AutoHistoryStepApi> list);

    void deleteByObjectId(String objectId);

    Map<String, AutoHistoryStepApi> selectByObjectId(String objectId);

}
