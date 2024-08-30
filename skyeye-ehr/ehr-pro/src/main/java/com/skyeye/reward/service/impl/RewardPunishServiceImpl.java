/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.reward.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.reward.dao.RewardPunishDao;
import com.skyeye.reward.entity.RewardPunish;
import com.skyeye.reward.service.RewardPunishService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RewardPunishServiceImpl
 * @Description: 员工奖惩管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:41
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "员工奖惩信息", groupName = "员工奖惩信息", teamAuth = true)
public class RewardPunishServiceImpl extends SkyeyeBusinessServiceImpl<RewardPunishDao, RewardPunish> implements RewardPunishService {

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryRewardPunishList(commonPageInfo);
        return beans;
    }

    @Override
    public RewardPunish selectById(String id) {
        RewardPunish rewardPunish = super.selectById(id);
        iSysDictDataService.setDataMation(rewardPunish, RewardPunish::getTypeId);
        return rewardPunish;
    }

}
