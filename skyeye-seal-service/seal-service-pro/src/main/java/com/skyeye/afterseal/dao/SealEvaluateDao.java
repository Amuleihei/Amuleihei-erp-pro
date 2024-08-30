/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.afterseal.dao;

import com.skyeye.afterseal.entity.SealEvaluate;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SealEvaluateDao
 * @Description: 工单服务评价数据接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/15 18:18
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public interface SealEvaluateDao extends SkyeyeBaseMapper<SealEvaluate> {

    List<Map<String, Object>> querySealEvaluateList(CommonPageInfo commonPageInfo);

}
