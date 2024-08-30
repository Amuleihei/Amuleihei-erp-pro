/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.leave.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeLinkDataServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.leave.dao.LeaveTimeSlotDao;
import com.skyeye.leave.entity.LeaveTimeSlot;
import com.skyeye.leave.service.LeaveTimeSlotService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: LeaveTimeSlotServiceImpl
 * @Description: 请假申请请假时间段服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/4/5 8:42
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "请假申请请假时间段", groupName = "请假申请", manageShow = false)
public class LeaveTimeSlotServiceImpl extends SkyeyeLinkDataServiceImpl<LeaveTimeSlotDao, LeaveTimeSlot> implements LeaveTimeSlotService {

    @Override
    public void editStateById(String id, String state, Integer useYearHoliday) {
        UpdateWrapper<LeaveTimeSlot> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, id);
        updateWrapper.set(MybatisPlusUtil.toColumns(LeaveTimeSlot::getState), state);
        updateWrapper.set(MybatisPlusUtil.toColumns(LeaveTimeSlot::getUseYearHoliday), useYearHoliday);
        update(updateWrapper);
    }
}
