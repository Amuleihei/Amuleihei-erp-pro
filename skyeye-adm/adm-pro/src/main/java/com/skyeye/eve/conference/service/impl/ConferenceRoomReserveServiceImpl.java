/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.conference.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeFlowableServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.conference.dao.ConferenceRoomReserveDao;
import com.skyeye.eve.conference.entity.ConferenceRoomReserve;
import com.skyeye.eve.conference.service.ConferenceRoomReserveService;
import com.skyeye.eve.conference.service.ConferenceRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ConferenceRoomReserveServiceImpl
 * @Description: 会议室预定申请服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/1 14:19
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "会议室预定", groupName = "会议室模块", flowable = true)
public class ConferenceRoomReserveServiceImpl extends SkyeyeFlowableServiceImpl<ConferenceRoomReserveDao, ConferenceRoomReserve> implements ConferenceRoomReserveService {

    @Autowired
    private ConferenceRoomService conferenceRoomService;

    @Override
    public QueryWrapper<ConferenceRoomReserve> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<ConferenceRoomReserve> queryWrapper = super.getQueryWrapper(commonPageInfo);
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ConferenceRoomReserve::getCreateId), userId);
        return queryWrapper;
    }

    @Override
    public ConferenceRoomReserve selectById(String id) {
        ConferenceRoomReserve conferenceRoomReserve = super.selectById(id);
        // 获取会议室信息
        conferenceRoomService.setDataMation(conferenceRoomReserve, ConferenceRoomReserve::getConferenceRoomId);
        return conferenceRoomReserve;
    }

}
