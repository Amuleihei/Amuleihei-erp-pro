/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.variable.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.variable.entity.AutoVariable;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AutoVariableDao
 * @Description: 变量管理数据接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/26 9:03
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface AutoVariableDao extends SkyeyeBaseMapper<AutoVariable> {
    List<Map<String, Object>> queryAutoVariableList(CommonPageInfo commonPageInfo);

}
