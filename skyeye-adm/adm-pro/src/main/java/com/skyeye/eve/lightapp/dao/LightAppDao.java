/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.lightapp.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.eve.lightapp.entity.LightApp;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: LightAppDao
 * @Description: 轻应用管理数据接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/10/12 9:18
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public interface LightAppDao extends SkyeyeBaseMapper<LightApp> {

    List<Map<String, Object>> queryLightAppList(CommonPageInfo commonPageInfo);

}
