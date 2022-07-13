/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sys.quartz;

import cn.hutool.json.JSONUtil;
import com.skyeye.common.client.ExecuteFeignClient;
import com.skyeye.common.constans.QuartzConstants;
import com.skyeye.common.util.DateAfterSpacePointTime;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.entity.checkwork.CheckWorkMationRest;
import com.skyeye.eve.entity.quartz.SysQuartzRunHistory;
import com.skyeye.eve.rest.checkwork.CheckWorkLeaveService;
import com.skyeye.eve.rest.checkwork.CheckWorkService;
import com.skyeye.eve.rest.checkwork.CheckWorkTimeService;
import com.skyeye.eve.service.SysQuartzRunHistoryService;
import com.skyeye.eve.service.SysScheduleCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: CheckWorkQuartz
 * @Description: 定时器填充打卡信息, 每天凌晨一点执行一次
 * @author: skyeye云系列--卫志强
 * @date: 2021/4/25 21:15
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Component
public class CheckWorkQuartz {

    private static Logger log = LoggerFactory.getLogger(CheckWorkQuartz.class);

    private static final String QUARTZ_ID = QuartzConstants.SysQuartzMateMationJobType.CHECK_WORK_QUARTZ.getQuartzId();

    @Autowired
    private CheckWorkService checkWorkService;

    @Autowired
    private CheckWorkTimeService checkWorkTimeService;

    @Autowired
    private CheckWorkLeaveService checkWorkLeaveService;

    @Autowired
    private SysScheduleCommonService sysScheduleCommonService;

    @Autowired
    private SysQuartzRunHistoryService sysQuartzRunHistoryService;

    /**
     * 定时器填充打卡信息,每天凌晨一点执行一次
     *
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void editCheckWorkMation() {
        String historyId = sysQuartzRunHistoryService.startSysQuartzRun(QUARTZ_ID);
        log.info("填充打卡信息定时任务执行");
        try {
            // 1.获取所有的考勤班次信息
            List<Map<String, Object>> workTime = getAllCheckWorkTime();
            // 得到昨天的时间
            String yesterdayTime = DateAfterSpacePointTime.getSpecifiedTime(
                DateAfterSpacePointTime.ONE_DAY.getType(), DateUtil.getTimeAndToString(), DateUtil.YYYY_MM_DD, DateAfterSpacePointTime.AroundType.BEFORE);
            if (workTime != null && !workTime.isEmpty() && !sysScheduleCommonService.judgeISHoliday(yesterdayTime)) {
                // 班次信息不为空，并且昨天不是节假日
                log.info("Fill in the clocking information for timing task execution time is {}", yesterdayTime);
                // 判断昨天的日期是周几
                int weekDay = DateUtil.getWeek(yesterdayTime);
                // 判断昨天的日期是单周还是双周
                int weekType = DateUtil.getWeekType(yesterdayTime);
                // 2.获取昨天应该打卡的班次信息
                List<Map<String, Object>> shouldCheckTime = getShouldCheckTime(weekDay, weekType, workTime);
                if (!shouldCheckTime.isEmpty()) {
                    shouldCheckTime.forEach(bean -> {
                        try {
                            // 3.1 处理所有昨天只打早卡没有打晚卡的记录id
                            handleNotCheckWorkEndMember(yesterdayTime, bean.get("timeId").toString());
                            // 3.2 处理所有昨天没有打卡的用户
                            handleNotCheckWorkMember(yesterdayTime, bean.get("timeId").toString());
                        } catch (Exception e) {
                            log.info("Handling abnormal attendance information, message is {}.", e);
                        }
                    });
                }
            }
            // 4 处理所有昨天加班只打早卡没有打晚卡的记录id
            handleNotCheckWorkEndMember(yesterdayTime, "-");
        } catch (Exception e) {
            sysQuartzRunHistoryService.endSysQuartzRun(historyId, SysQuartzRunHistory.State.START_ERROR.getState());
            log.warn("CheckWorkQuartz error.", e);
        }
        log.info("填充打卡信息定时任务 end");
        sysQuartzRunHistoryService.endSysQuartzRun(historyId, SysQuartzRunHistory.State.START_SUCCESS.getState());
    }

    /**
     * 获取昨天应该打卡的班次信息
     *
     * @param weekDay  周几
     * @param weekType 1是双周，0是单周
     * @param workTime 考勤班次
     * @return
     */
    private List<Map<String, Object>> getShouldCheckTime(int weekDay, int weekType, List<Map<String, Object>> workTime) {
        List<Map<String, Object>> shouldCheckTime = new ArrayList<>();
        for (Map<String, Object> bean : workTime) {
            // 该班次中上班的天数
            List<Map<String, Object>> days = (List<Map<String, Object>>) bean.get("days");
            List<Map<String, Object>> simpleDay = days.stream().filter(item -> Integer.parseInt(item.get("day").toString()) == weekDay).collect(Collectors.toList());
            if (simpleDay != null && !simpleDay.isEmpty()) {
                // 在该班次中找到了指定日期的上班时间
                int datType = Integer.parseInt(simpleDay.get(0).get("type").toString());
                if (weekType == 0 && datType == 2) {
                    // 单周
                    shouldCheckTime.add(bean);
                } else {
                    shouldCheckTime.add(bean);
                }
            }
        }
        log.info("shouldCheckTime is {}", JSONUtil.toJsonStr(shouldCheckTime));
        return shouldCheckTime;
    }

    /**
     * 获取所有的考勤班次信息
     *
     * @return
     */
    private List<Map<String, Object>> getAllCheckWorkTime() {
        List<Map<String, Object>> workTime =
            ExecuteFeignClient.get(() -> checkWorkTimeService.queryAllStaffCheckWorkTime()).getRows();
        for (Map<String, Object> bean : workTime) {
            List<Map<String, Object>> days =
                ExecuteFeignClient.get(() -> checkWorkTimeService.queryWeekDayByTimeId(bean.get("timeId").toString())).getRows();
            bean.put("days", days);
        }
        return workTime;
    }

    /**
     * 处理所有昨天只打早卡没有打晚卡的记录id
     *
     * @param yesterdayTime
     * @param timeId
     */
    private void handleNotCheckWorkEndMember(String yesterdayTime, String timeId) {
        List<Map<String, Object>> beans =
            ExecuteFeignClient.get(() -> checkWorkService.queryNotCheckEndWorkId(timeId, yesterdayTime)).getRows();
        if (!beans.isEmpty()) {
            for (Map<String, Object> b : beans) {
                CheckWorkMationRest checkWorkMation = new CheckWorkMationRest();
                checkWorkMation.setId(b.get("id").toString());
                checkWorkMation.setState("5");
                checkWorkMation.setClockOutState("3");
                checkWorkMation.setWorkHours("0:0:0");
                // 填充打晚卡信息
                ExecuteFeignClient.get(() -> checkWorkService.editCheckWorkBySystem(checkWorkMation));
            }
        }
    }

    /**
     * 处理所有昨天没有打卡的用户
     *
     * @param yesterdayTime 昨天的日期,格式为：yyyy-MM-dd
     * @param timeId        班次id
     */
    private void handleNotCheckWorkMember(String yesterdayTime, String timeId) {
        // 获取所有昨天没有打卡的用户
        List<Map<String, Object>> beans =
            ExecuteFeignClient.get(() -> checkWorkService.queryNotCheckMember(timeId, yesterdayTime)).getRows();
        if (!beans.isEmpty()) {
            List<Map<String, Object>> listBeans = new ArrayList<>();
            for (Map<String, Object> b : beans) {
                String createId = b.get("createId").toString();
                // 判断昨天是否有请假记录,如果有，则不填充这条记录
                Map<String, Object> params = new HashMap<>();
                params.put("timeId", timeId);
                params.put("createId", createId);
                params.put("leaveDay", yesterdayTime);
                Map<String, Object> leaveMation =
                    ExecuteFeignClient.get(() -> checkWorkLeaveService.queryCheckWorkLeaveByMation(params)).getBean();
                if (leaveMation == null || leaveMation.isEmpty()) {
                    // 找不到该员工这个班次在这一天的请假记录，则记为旷工
                    listBeans.add(getNoCheckWorkObject(timeId, createId, yesterdayTime));
                }
            }
            if (!listBeans.isEmpty()) {
                ExecuteFeignClient.get(() -> checkWorkService.insertCheckWorkBySystem(JSONUtil.toJsonStr(listBeans)));
            }
        }
    }

    private Map<String, Object> getNoCheckWorkObject(String timeId, String createId, String yesterdayTime) {
        Map<String, Object> item = new HashMap<>();
        item.put("id", ToolUtil.getSurFaceId());
        item.put("createId", createId);
        item.put("checkDate", yesterdayTime);
        item.put("state", "2");
        item.put("clockInState", "3");
        item.put("clockOutState", "3");
        item.put("timeId", timeId);
        item.put("workHours", "0:0:0");
        return item;
    }

}
