/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.xxljob;

import com.skyeye.common.client.ExecuteFeignClient;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.constans.WagesConstant;
import com.skyeye.common.enumeration.OvertimeSettlementType;
import com.skyeye.common.util.CalculationUtil;
import com.skyeye.eve.centerrest.entity.wages.WagesStaffWorkTimeMationRest;
import com.skyeye.eve.centerrest.user.SysEveUserStaffCapitalService;
import com.skyeye.eve.centerrest.wages.WagesStaffMationService;
import com.skyeye.checkwork.dao.CheckWorkDao;
import com.skyeye.checkwork.service.CheckWorkService;
import com.skyeye.overtime.classenum.OvertimeSoltSettleState;
import com.skyeye.overtime.service.OverTimeSlotService;
import com.skyeye.worktime.entity.CheckWorkTime;
import com.skyeye.worktime.service.CheckWorkTimeService;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: CalcStaffWaitPayWages
 * @Description: 定时统计员工待结算其他奖金的数据
 * 1. 加班结算
 * @author: skyeye云系列--卫志强
 * @date: 2021/9/2 15:11
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Component
public class CalcStaffWaitPayWages {

    private static Logger log = LoggerFactory.getLogger(CalcStaffWaitPayWages.class);

    @Autowired
    private CheckWorkService checkWorkService;

    @Autowired
    private OverTimeSlotService overTimeSlotService;

    @Autowired
    private CheckWorkTimeService checkWorkTimeService;

    @Autowired
    private CheckWorkDao checkWorkDao;

    @Autowired
    private WagesStaffMationService wagesStaffMationService;

    @Autowired
    private SysEveUserStaffCapitalService sysEveUserStaffCapitalService;

    /**
     * 定时统计员工待结算其他奖金的数据 凌晨一点半执行
     */
    @XxlJob("calcStaffWaitPayWages")
    public void handler() {
        log.info("定时统计员工待结算薪资的数据定时任务开始执行");
        try {
            calcWaitWages();
        } catch (Exception e) {
            log.warn("CalcStaffWaitPayWages error.", e);
        }
        log.info("定时统计员工待结算薪资的数据定时任务执行完成");
    }

    private void calcWaitWages() {
        // 指定年月的考勤信息的缓存
        Map<String, List<CheckWorkTime>> pointMonthCheckWorkTimeCache = new HashMap<>();
        // 获取所有待结算的加班信息
        List<Map<String, Object>> overTimeWaitSettlementList = checkWorkService.queryCheckWorkOvertimeWaitSettlement();
        log.info("overTimeWaitSettlementList size is: {}", overTimeWaitSettlementList.size());
        Map<String, List<Map<String, Object>>> overTimeWaitSettlementByStaffId = overTimeWaitSettlementList.stream()
            .collect(Collectors.groupingBy(map -> map.get("staffId").toString() + map.get("overtimeMonth").toString()));
        for (Map.Entry<String, List<Map<String, Object>>> entry : overTimeWaitSettlementByStaffId.entrySet()) {
            List<Map<String, Object>> overTimeList = entry.getValue();
            // 员工薪资
            String actWages = overTimeList.get(0).get("actWages").toString();
            String overtimeMonth = overTimeList.get(0).get("overtimeMonth").toString();
            String staffId = overTimeList.get(0).get("staffId").toString();
            String hourWages = getStaffHourWages(pointMonthCheckWorkTimeCache, actWages, overtimeMonth, staffId);
            String resultMoney = getAllOverTimeMoneyThisMonth(overTimeList, hourWages);

            Map<String, Object> params = new HashMap<>();
            params.put("staffId", overTimeList.get(0).get("staffId").toString());
            params.put("companyId", overTimeList.get(0).get("companyId").toString());
            params.put("departmentId", overTimeList.get(0).get("departmentId").toString());
            params.put("monthTime", overtimeMonth);
            params.put("type", 1);
            params.put("money", resultMoney);
            sysEveUserStaffCapitalService.addMonthMoney2StaffCapital(params);
        }
    }

    private String getAllOverTimeMoneyThisMonth(List<Map<String, Object>> overTimeList, String hourWages) {
        String allOverTimeHourThisMonth = "0";
        for (Map<String, Object> bean : overTimeList) {
            // 加班工时
            String overtimeHour = bean.get("overtimeHour").toString();
            // 结算类型
            int overtimeSettlementType = Integer.parseInt(bean.get("overtimeSettlementType").toString());
            String money = "0";
            if (overtimeSettlementType == OvertimeSettlementType.SINGLE_SALARY_SETTLEMENT.getKey()) {
                // 单倍薪资结算
                money = CalculationUtil.multiply(CommonNumConstants.NUM_TWO, overtimeHour, hourWages, "1");
            } else if (overtimeSettlementType == OvertimeSettlementType.ONE_POINT_FIVE_SALARY_SETTLEMENT.getKey()) {
                // 1.5倍薪资结算
                money = CalculationUtil.multiply(CommonNumConstants.NUM_TWO, overtimeHour, hourWages, "1.5");
            } else if (overtimeSettlementType == OvertimeSettlementType.DOUBLE_SALARY_SETTLEMENT.getKey()) {
                // 双倍薪资结算
                money = CalculationUtil.multiply(CommonNumConstants.NUM_TWO, overtimeHour, hourWages, "2");
            }
            allOverTimeHourThisMonth = CalculationUtil.add(allOverTimeHourThisMonth, money, 2);
            // 修改加班电子流的结算状态为已计入统计
            overTimeSlotService.editSettleStateById(bean.get("id").toString(), OvertimeSoltSettleState.RECORDED_IN_STATISTICS.getKey());
        }
        return allOverTimeHourThisMonth;
    }

    /**
     * 获取员工每小时的工资
     *
     * @param pointMonthCheckWorkTimeCache 指定年月的考勤信息的缓存
     * @param actWages                     员工信息
     * @param overtimeMonth                加班年月，格式为：yyyy-MM
     * @param staffId                      员工id
     * @return hourWages
     */
    private String getStaffHourWages(Map<String, List<CheckWorkTime>> pointMonthCheckWorkTimeCache, String actWages,
                                     String overtimeMonth, String staffId) {
        // 考勤日期
        List<CheckWorkTime> workTime = getPointMonthCheckWorkTime(pointMonthCheckWorkTimeCache, overtimeMonth);
        // 1.获取该员工拥有的考勤班次id集合
        List<Map<String, Object>> staffTimeIdMation = checkWorkDao.queryStaffCheckWorkTimeRelationByStaffId(staffId);
        List<String> userTimeIds = staffTimeIdMation.stream()
            .map(p -> p.get("timeId").toString()).collect(Collectors.toList());
        List<CheckWorkTime> staffWorkTime = workTime.stream()
            .filter(bean -> userTimeIds.contains(bean.getId()))
            .collect(Collectors.toList());
        // 2.获取应出勤的班次以及小时
        WagesStaffWorkTimeMationRest wagesStaffWorkTimeMationRest = new WagesStaffWorkTimeMationRest();
        wagesStaffWorkTimeMationRest.setStaffWorkTime(staffWorkTime);
        wagesStaffWorkTimeMationRest.setLastMonthDate(overtimeMonth);
        Map<String, Object> staffModelFieldMap =
            ExecuteFeignClient.get(() -> wagesStaffMationService.setLastMonthBe(wagesStaffWorkTimeMationRest)).getBean();
        // 获取每小时的工资
        String hourWages = CalculationUtil.divide(actWages,
            staffModelFieldMap.get(WagesConstant.DEFAULT_WAGES_FIELD_TYPE.LAST_MONTH_BE_HOUR.getKey()).toString(), 2);
        return hourWages;
    }

    private List<CheckWorkTime> getPointMonthCheckWorkTime(Map<String, List<CheckWorkTime>> cache, String pointMonthDate) {
        if (cache.containsKey(pointMonthDate)) {
            return cache.get(pointMonthDate);
        }
        // 所有的考勤班次信息
        List<CheckWorkTime> workTime = checkWorkTimeService.getAllCheckWorkTime(pointMonthDate);
        cache.put(pointMonthDate, workTime);
        return workTime;
    }

}
