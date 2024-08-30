/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.checkwork.dao;

import com.skyeye.checkwork.entity.CheckWork;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CheckWorkDao
 * @Description: 考勤打卡管理数据接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/4/6 15:02
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface CheckWorkDao extends SkyeyeBaseMapper<CheckWork> {

    Map<String, Object> queryStartWorkTime(@Param("timeId") String timeId, @Param("staffId") String staffId);

    CheckWork queryisAlreadyCheck(@Param("checkDate") String checkDate, @Param("createId") String createId,
                                  @Param("timeId") String timeId);

    List<Map<String, Object>> queryCheckWorkList(CommonPageInfo pageInfo);

    /**
     * 获取所有昨天没有打卡的用户
     *
     * @param timeId        班次id
     * @param yesterdayTime 日期信息,格式为：yyyy-MM-dd
     * @return
     */
    List<Map<String, Object>> queryNotCheckMember(@Param("timeId") String timeId, @Param("yesterdayTime") String yesterdayTime);

    /**
     * 新增打卡信息
     *
     * @param listBeans
     * @return
     */
    int insertCheckWorkBySystem(List<Map<String, Object>> listBeans);

    /**
     * 获取所有昨天没有打下班卡的用户
     *
     * @param timeId        班次id
     * @param yesterdayTime 日期信息,格式为：yyyy-MM-dd
     * @return
     */
    List<Map<String, Object>> queryNotCheckEndWorkId(@Param("timeId") String timeId, @Param("yesterdayTime") String yesterdayTime);

    /**
     * 填充下班卡信息
     *
     * @param item
     * @return
     */
    int editCheckWorkBySystem(Map<String, Object> item);

    List<Map<String, Object>> queryCheckWorkIdByAppealType(Map<String, Object> map);

    /**
     * 获取指定员工指定班次在指定月份的所有打卡信息
     *
     * @param userId 用户id
     * @param timeId 班次id
     * @param months 月份集合
     * @return
     */
    List<Map<String, Object>> queryCheckWorkMationByMonth(@Param("userId") String userId,
                                                          @Param("timeId") String timeId, @Param("months") List<String> months);

    List<Map<String, Object>> queryCheckWorkReport(Map<String, Object> map);

    Map<String, Object> queryCheckWorkEcharts(Map<String, Object> beans);

    List<Map<String, Object>> queryReportDetail(Map<String, Object> map);

    /**
     * 获取指定月份的假期(type=3)
     *
     * @param months 指定月，格式["2020-04", "2020-05"...]
     * @return 指定月中的所有节假日
     */
    List<Map<String, Object>> queryHolidayScheduleDayMation(@Param("list") List<String> months);

    List<Map<String, Object>> queryStaffCheckWorkTimeRelationByStaffId(@Param("staffId") String staffId);

}
