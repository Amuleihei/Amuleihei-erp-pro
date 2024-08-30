/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.history.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.history.entity.AutoHistoryStep;

import java.util.List;

/**
 * @ClassName: AutoHistoryStepService
 * @Description: 步骤执行历史服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/16 20:36
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface AutoHistoryStepService extends SkyeyeBusinessService<AutoHistoryStep> {

    List<AutoHistoryStep> queryAutoStepListByCaseId(String historyCaseId);

    void deleteByObjectId(String objectId);

}
