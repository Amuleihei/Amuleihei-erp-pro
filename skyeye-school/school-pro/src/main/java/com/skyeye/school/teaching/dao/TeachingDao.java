/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.teaching.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.school.teaching.entity.Teaching;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TeachingDao
 * @Description: 授课信息数据层
 * @author: xqz
 * @date: 2023/8/29 17:41
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface TeachingDao extends SkyeyeBaseMapper<Teaching> {

    List<Map<String, Object>> queryTeachingList(CommonPageInfo commonPageInfo);

}
