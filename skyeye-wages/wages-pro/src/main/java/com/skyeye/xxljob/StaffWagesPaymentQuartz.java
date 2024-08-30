/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.xxljob;

import com.skyeye.common.util.DateUtil;
import com.skyeye.eve.payment.classenum.PaymentHistoryState;
import com.skyeye.eve.payment.entity.WagesPaymentHistory;
import com.skyeye.eve.payment.service.WagesPaymentHistoryService;
import com.skyeye.jedis.util.RedisLock;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: StaffWagesPaymentQuartz
 * @Description: 薪资发放定时任务，每月15日上午10:15触发
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/1 21:32
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Component
public class StaffWagesPaymentQuartz {

    private static Logger LOGGER = LoggerFactory.getLogger(StaffWagesPaymentQuartz.class);

    @Autowired
    private WagesPaymentHistoryService wagesPaymentHistoryService;

    /**
     * 定时发放薪资功能，每月15日上午10:15触发
     */
    @XxlJob("staffWagesPaymentQuartz")
    public void staffWagesPayment() {
        LOGGER.info("staff wagesPayment month is start");
        // 获取上个月的年月
        String lastMonthDate = DateUtil.getLastMonthDate();
        String lockKey = String.format("inWagesPaymentStaffRedisKey:%s", lastMonthDate);
        RedisLock lock = new RedisLock(lockKey);
        try {
            if (!lock.lock()) {
                // 加锁失败
                return;
            }
            List<WagesPaymentHistory> wagesPaymentHistories = wagesPaymentHistoryService.queryWagesPaymentHistoryByState(PaymentHistoryState.UNISSUED.getKey());
            wagesPaymentHistories.forEach(wagesPaymentHistory -> {
                paymentStaffWages(wagesPaymentHistory, lastMonthDate);
            });
        } catch (Exception e) {
            LOGGER.warn("StaffWagesPaymentQuartz error.", e);
        } finally {
            lock.unlock();
        }
        LOGGER.info("staff wagesPayment month is end");
    }

    /**
     * 薪资发放
     *
     * @param staffWages    员工薪资信息
     * @param lastMonthDate 上个月的年月
     */
    private void paymentStaffWages(WagesPaymentHistory staffWages, String lastMonthDate) {
        String staffId = staffWages.getStaffId();
        // todo 这里处理薪资发放的信息


        wagesPaymentHistoryService.editWagesPaymentHistoryState(staffId, lastMonthDate, PaymentHistoryState.ISSUED.getKey());
    }

}
