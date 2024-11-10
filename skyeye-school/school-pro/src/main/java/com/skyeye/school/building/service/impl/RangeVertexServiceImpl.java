package com.skyeye.school.building.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.school.building.dao.RangeVertexDao;
import com.skyeye.school.building.entity.RangeVertex;
import com.skyeye.school.building.service.RangeVertexService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: RangeVertexServiceImpl
 * @Description: 范围顶点管理实现层
 * @author: skyeye云系列--卫志强
 * @date: 2023/9/5 17:15
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "范围顶点管理", groupName = "范围顶点管理")
public class RangeVertexServiceImpl extends SkyeyeBusinessServiceImpl<RangeVertexDao, RangeVertex> implements RangeVertexService {
}
