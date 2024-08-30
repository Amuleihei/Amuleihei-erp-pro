/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.entity.LightAppType;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: LightAppTypeDao
 * @Description: 轻应用类型管理数据接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/10/11 8:49
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public interface LightAppTypeDao extends SkyeyeBaseMapper<LightAppType> {

    List<Map<String, Object>> queryLightAppTypeList(CommonPageInfo commonPageInfo);

}
