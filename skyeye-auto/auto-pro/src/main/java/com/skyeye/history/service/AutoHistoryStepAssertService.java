/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.history.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.history.entity.AutoHistoryStepAssert;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AutoHistoryStepAssertService
 * @Description: 断言执行历史服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/17 8:21
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface AutoHistoryStepAssertService extends SkyeyeBusinessService<AutoHistoryStepAssert> {

    void saveList(String objectId, List<AutoHistoryStepAssert> list);

    void deleteByObjectId(String objectId);

    Map<String, List<AutoHistoryStepAssert>> selectByObjectId(String objectId);

}
