/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.ScheduleDay;

/**
 * @ClassName: ScheduleDayService
 * @Description: 日程管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/24 17:42
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public interface ScheduleDayService extends SkyeyeBusinessService<ScheduleDay> {

    void queryTodayScheduleDayByUserId(InputObject inputObject, OutputObject outputObject);

    void editScheduleDayById(InputObject inputObject, OutputObject outputObject);

    void insertScheduleByOtherModule(InputObject inputObject, OutputObject outputObject);

    void deleteScheduleMationByObjectId(InputObject inputObject, OutputObject outputObject);

    void judgeISHoliday(InputObject inputObject, OutputObject outputObject);

    void deleteHolidayScheduleByThisYear(InputObject inputObject, OutputObject outputObject);

    void addSchedule(InputObject inputObject, OutputObject outputObject);

    /**
     * 根据日程id修改日程状态
     *
     * @param id    日程id
     * @param state 日程状态
     * @return
     */
    void editScheduleStateById(String id, Integer state);

    void downloadScheduleTemplate(InputObject inputObject, OutputObject outputObject);

    void exploreScheduleTemplate(InputObject inputObject, OutputObject outputObject);

    void addHolidayScheduleRemind(InputObject inputObject, OutputObject outputObject);

    void deleteHolidayScheduleRemind(InputObject inputObject, OutputObject outputObject);

    void queryHolidayScheduleListBySys(InputObject inputObject, OutputObject outputObject);

    void queryMyAgencyList(InputObject inputObject, OutputObject outputObject);

    void queryScheduleDayByPointHms(InputObject inputObject, OutputObject outputObject);
}
