/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.userprocess.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.userprocess.entity.ActUserProcess;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ActUserProcessDao
 * @Description: 用户启动的流程管理数据接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/12/18 10:32
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ActUserProcessDao extends SkyeyeBaseMapper<ActUserProcess> {

    List<Map<String, Object>> queryStartProcessNotSubByUserId(CommonPageInfo pageInfo);

}
