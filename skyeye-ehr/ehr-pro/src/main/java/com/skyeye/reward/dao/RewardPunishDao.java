/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.reward.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.reward.entity.RewardPunish;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RewardPunishDao
 * @Description: 员工奖惩管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 23:41
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface RewardPunishDao extends SkyeyeBaseMapper<RewardPunish> {

    List<Map<String, Object>> queryRewardPunishList(CommonPageInfo commonPageInfo);

}
