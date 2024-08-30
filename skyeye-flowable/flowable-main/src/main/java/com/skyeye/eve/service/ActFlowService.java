/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.eve.entity.ActFlowMation;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ActFlowService
 * @Description: 流程模型管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/10/4 22:52
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ActFlowService extends SkyeyeBusinessService<ActFlowMation> {

    Map<String, ActFlowMation> actIdToFlowNameByIds(List<String> ids);

    /**
     * 根据服务类名获取流程模型信息
     *
     * @param serviceClassName 服务类名
     * @return
     */
    ActFlowMation getActFlow(String serviceClassName);

}
