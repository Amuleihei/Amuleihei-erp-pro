/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.enumeration.UserStaffState;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.CalculationUtil;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.dao.WagesFieldTypeDao;
import com.skyeye.exception.CustomException;
import com.skyeye.organization.service.CompanyDepartmentService;
import com.skyeye.organization.service.CompanyJobScoreService;
import com.skyeye.organization.service.CompanyJobService;
import com.skyeye.organization.service.CompanyMationService;
import com.skyeye.personnel.classenum.StaffWagesStateEnum;
import com.skyeye.personnel.classenum.UserLockState;
import com.skyeye.personnel.dao.SysEveUserStaffDao;
import com.skyeye.personnel.entity.SysEveUserStaff;
import com.skyeye.personnel.entity.SysEveUserStaffQuery;
import com.skyeye.personnel.service.SysEveUserService;
import com.skyeye.personnel.service.SysEveUserStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: SysEveUserStaffServiceImpl
 * @Description: 员工管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 12:02
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "员工管理", groupName = "员工管理")
public class SysEveUserStaffServiceImpl extends SkyeyeBusinessServiceImpl<SysEveUserStaffDao, SysEveUserStaff> implements SysEveUserStaffService {

    @Autowired
    private SysEveUserStaffDao sysEveUserStaffDao;

    @Autowired
    private SysEveUserService sysEveUserService;

    @Autowired
    private WagesFieldTypeDao wagesFieldTypeDao;

    @Value("${skyeye.jobNumberPrefix}")
    private String jobNumberPrefix;

    @Autowired
    private CompanyMationService companyMationService;

    @Autowired
    private CompanyDepartmentService companyDepartmentService;

    @Autowired
    private CompanyJobService companyJobService;

    @Autowired
    private CompanyJobScoreService companyJobScoreService;

    @Override
    public void getQueryWrapper(InputObject inputObject, QueryWrapper<SysEveUserStaff> wrapper) {
        SysEveUserStaffQuery sysEveUserStaffQuery = inputObject.getParams(SysEveUserStaffQuery.class);
        if (sysEveUserStaffQuery.getDesignWages() != null) {
            wrapper.eq(MybatisPlusUtil.toColumns(SysEveUserStaff::getDesignWages), sysEveUserStaffQuery.getDesignWages());
        }
    }

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        beans.forEach(bean -> {
            bean.put(CommonConstants.NAME, bean.get("jobNumber").toString() + "_" + bean.get("userName").toString());
        });
        // 设置组织信息
        companyMationService.setNameMationForMap(beans, "companyId", "companyName", StrUtil.EMPTY);
        companyDepartmentService.setNameMationForMap(beans, "departmentId", "departmentName", StrUtil.EMPTY);
        companyJobService.setNameMationForMap(beans, "jobId", "jobName", StrUtil.EMPTY);
        companyJobScoreService.setNameMationForMap(beans, "jobScoreId", "jobScoreName", StrUtil.EMPTY);
        return beans;
    }

    @Override
    public void createPrepose(SysEveUserStaff entity) {
        // 设置新的工号
        QueryWrapper<SysEveUserStaff> queryWrapper = new QueryWrapper<>();
        String jobNumberKey = MybatisPlusUtil.toColumns(SysEveUserStaff::getJobNumber);
        queryWrapper.select("max(0 + RIGHT(" + jobNumberKey + ", 6)) AS " + jobNumberKey);
        SysEveUserStaff sysEveUserStaff = getOne(queryWrapper, false);
        entity.setJobNumber(jobNumberPrefix + CalculationUtil.add(sysEveUserStaff.getJobNumber(), CommonNumConstants.NUM_ONE.toString(), CommonNumConstants.NUM_ZERO));
        entity.setActWages(CommonNumConstants.NUM_ZERO.toString());
        entity.setAnnualLeave(CommonNumConstants.NUM_ZERO.toString());
        entity.setHolidayNumber(CommonNumConstants.NUM_ZERO.toString());
        entity.setRetiredHolidayNumber(CommonNumConstants.NUM_ZERO.toString());
        entity.setDesignWages(StaffWagesStateEnum.WAIT_DESIGN_WAGES.getKey());
    }

    @Override
    public void createPostpose(SysEveUserStaff entity, String userId) {
        // 新增员工薪资字段信息
        createUserStaffWagesFieldType(entity.getId());
    }

    @Override
    public void updatePrepose(SysEveUserStaff entity) {
        SysEveUserStaff oldData = selectById(entity.getId());
        entity.setUserId(oldData.getUserId());
        entity.setQuitTime(oldData.getQuitTime());
        entity.setQuitReason(oldData.getQuitReason());
        entity.setBecomeWorkerTime(oldData.getBecomeWorkerTime());
        entity.setType(oldData.getType());
        entity.setDesignWages(oldData.getDesignWages());
        entity.setActWages(oldData.getActWages());
        entity.setAnnualLeave(oldData.getAnnualLeave());
        entity.setAnnualLeaveStatisTime(oldData.getAnnualLeaveStatisTime());
        entity.setHolidayNumber(oldData.getHolidayNumber());
        entity.setHolidayStatisTime(oldData.getHolidayStatisTime());
        entity.setRetiredHolidayNumber(oldData.getRetiredHolidayNumber());
        entity.setRetiredHolidayStatisTime(oldData.getRetiredHolidayStatisTime());
        entity.setInterviewArrangementId(oldData.getInterviewArrangementId());
    }

    @Override
    public void updatePostpose(SysEveUserStaff entity, String userId) {
        SysEveUserStaff oldData = selectById(entity.getId());
        if (!StrUtil.equals(oldData.getDepartmentId(), entity.getDepartmentId())) {
            // 新旧部门不一致，删除用户在redis中存储的好友组信息(旧的部门)
            jedisClientService.delKeys(Constants.getSysTalkGroupUserListMationById(oldData.getDepartmentId()) + "*");
            // 删除用户在redis中存储的好友组信息(新的部门)
            jedisClientService.delKeys(Constants.getSysTalkGroupUserListMationById(entity.getDepartmentId()) + "*");
        }
    }

    @Override
    public void writePostpose(SysEveUserStaff entity, String userId) {
        super.writePostpose(entity, userId);
        // 员工考勤时间段
        saveUserStaffCheckWorkTime(entity.getTimeIdList(), entity.getId());
    }

    /**
     * 新增员工薪资字段信息
     *
     * @param staffId
     */
    private void createUserStaffWagesFieldType(String staffId) {
        List<Map<String, Object>> fieldType = wagesFieldTypeDao.queryAllWagesFieldTypeList();
        if (fieldType != null && !fieldType.isEmpty()) {
            fieldType.stream().forEach(bean -> {
                bean.put("id", staffId);
            });
            wagesFieldTypeDao.insertWagesFieldTypeKeyToStaff(fieldType);
        }
    }

    /**
     * 新增员工考勤时间段
     *
     * @param timeIdList 班次id
     * @param staffId    员工id
     */
    private void saveUserStaffCheckWorkTime(List<String> timeIdList, String staffId) {
        // 删除员工考勤时间段信息再重新添加
        sysEveUserStaffDao.deleteStaffCheckWorkTimeRelationByStaffId(staffId);
        // 逗号隔开的多班次考勤
        if (CollectionUtil.isNotEmpty(timeIdList)) {
            // 校验多班次考勤是否有重复时间段
            boolean repeat = judgeRepeatShift(timeIdList);
            if (repeat) {
                // 存在冲突的工作时间段
                throw new CustomException("Conflicting working hours.");
            }
            List<Map<String, Object>> staffTimeMation = new ArrayList<>();
            timeIdList.stream().forEach(timeId -> {
                if (!ToolUtil.isBlank(timeId)) {
                    Map<String, Object> bean = new HashMap<>();
                    bean.put("staffId", staffId);
                    bean.put("timeId", timeId);
                    staffTimeMation.add(bean);
                }
            });
            if (!staffTimeMation.isEmpty()) {
                sysEveUserStaffDao.insertStaffCheckWorkTimeRelation(staffTimeMation);
            }
        }
    }

    private boolean judgeRepeatShift(List<String> timeIds) {
        // 1.获取班次的上下班打卡时间
        List<Map<String, Object>> timeMation = sysEveUserStaffDao.queryCheckTimeMationByTimeIds(timeIds);
        // 2.获取班次的工作日
        List<Map<String, Object>> timeDayMation = sysEveUserStaffDao.queryCheckTimeDaysMationByTimeIds(timeIds);
        timeMation.forEach(bean -> {
            List<Map<String, Object>> thisDayMation = timeDayMation.stream()
                .filter(item -> item.get("timeId").toString().equals(bean.get("timeId").toString()))
                .collect(Collectors.toList());
            bean.put("days", thisDayMation);
        });
        // 3.校验工作日是否冲突
        return judgeRepeatWorking(timeMation);
    }

    private boolean judgeRepeatWorking(List<Map<String, Object>> timeMation) {
        if (timeMation.size() > 1) {
            for (int i = 0; i < timeMation.size(); i++) {
                for (int j = (i + 1); j < timeMation.size(); j++) {
                    List<String> times = new ArrayList<>();
                    times.add(timeMation.get(i).get("startTime").toString() + "-"
                        + timeMation.get(i).get("endTime").toString());
                    times.add(timeMation.get(j).get("startTime").toString() + "-"
                        + timeMation.get(j).get("endTime").toString());
                    // 1.首先判断每天的工作日的开始时间和结束时间是否有重复
                    boolean flag = DateUtil.checkOverlap(times);
                    if (flag) {
                        // 开始时间和结束时间是否有重复
                        List<Map<String, Object>> iDayMation = (List<Map<String, Object>>) timeMation.get(i)
                            .get("days");
                        List<Map<String, Object>> jDayMation = (List<Map<String, Object>>) timeMation.get(j)
                            .get("days");
                        // 求这两个班次的工作日冲突的天数，根据类型和工作日(周几)判断
                        int size = iDayMation.stream()
                            .map(t -> jDayMation.stream()
                                .filter(s -> (Objects.equals(t.get("type").toString(), s.get("type").toString())
                                    || Objects.equals(t.get("type").toString(), "1")
                                    || Objects.equals(s.get("type").toString(), "1"))
                                    && Objects.equals(t.get("day").toString(), s.get("day").toString()))
                                .findAny().orElse(null)).filter(Objects::nonNull).collect(Collectors.toList()).size();
                        if (size > 0) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

    @Override
    public SysEveUserStaff selectById(String id) {
        SysEveUserStaff sysEveUserStaff = super.selectById(id);
        sysEveUserStaff.setStateName(UserStaffState.getNameByState(sysEveUserStaff.getState()));
        // 员工考勤时间段信息
        List<Map<String, Object>> staffTimeMation = sysEveUserStaffDao.queryStaffCheckWorkTimeRelationNameByStaffId(id);
        sysEveUserStaff.setTimeList(staffTimeMation);
        // 设置组织信息
        companyMationService.setNameDataMation(sysEveUserStaff, SysEveUserStaff::getCompanyId, StrUtil.EMPTY);
        companyDepartmentService.setNameDataMation(sysEveUserStaff, SysEveUserStaff::getDepartmentId, StrUtil.EMPTY);
        companyJobService.setNameDataMation(sysEveUserStaff, SysEveUserStaff::getJobId, StrUtil.EMPTY);
        companyJobScoreService.setNameDataMation(sysEveUserStaff, SysEveUserStaff::getJobScoreId, StrUtil.EMPTY);
        return sysEveUserStaff;
    }

    /**
     * 员工离职
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void editSysUserStaffState(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("state", UserStaffState.QUIT.getKey());
        sysEveUserStaffDao.editSysUserStaffState(map);
        String staffId = map.get("id").toString();
        SysEveUserStaff staffMation = selectById(staffId);
        if (!ToolUtil.isBlank(staffMation.getUserId())) {
            // 删除redis中缓存的单位下的用户
            jedisClientService.delKeys(Constants.getSysTalkGroupUserListMationById(staffMation.getDepartmentId()) + "*");
            // 锁定帐号
            sysEveUserService.editUserLockState(staffMation.getUserId(), UserLockState.SYS_USER_LOCK_STATE_ISLOCK.getKey());
            // 退出登录
            sysEveUserService.removeLogin(staffMation.getUserId());
        }
    }

    /**
     * 修改员工类型
     *
     * @param id   员工id
     * @param type 参考#UserStaffType
     */
    @Override
    public void updateStaffType(String id, Integer type) {
        UpdateWrapper<SysEveUserStaff> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, id);
        updateWrapper.set(MybatisPlusUtil.toColumns(SysEveUserStaff::getType), type);
        update(updateWrapper);
    }

    /**
     * 获取当前登录员工的信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysUserStaffLogin(InputObject inputObject, OutputObject outputObject) {
        String staffId = inputObject.getLogParams().get("staffId").toString();
        SysEveUserStaff sysEveUserStaff = selectById(staffId);
        outputObject.setBean(sysEveUserStaff);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 根据用户ids/员工ids获取员工信息集合
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryUserMationList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String userIds = params.get("userIds").toString();
        String staffIds = params.get("staffIds").toString();
        List<Map<String, Object>> beans = new ArrayList<>();
        // 用户id和员工id只要有一个不为空就进行查询
        if (!ToolUtil.isBlank(userIds) || !ToolUtil.isBlank(staffIds)) {
            beans = sysEveUserStaffDao.queryUserMationList(userIds, staffIds);
            // 设置组织信息
            companyMationService.setNameMationForMap(beans, "companyId", "companyName", StrUtil.EMPTY);
            companyDepartmentService.setNameMationForMap(beans, "departmentId", "departmentName", StrUtil.EMPTY);
            companyJobService.setNameMationForMap(beans, "jobId", "jobName", StrUtil.EMPTY);
            companyJobScoreService.setNameMationForMap(beans, "jobScoreId", "jobScoreName", StrUtil.EMPTY);
        }
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    /**
     * 修改员工剩余年假信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void editSysUserStaffAnnualLeaveById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String staffId = map.get("staffId").toString();
        String quarterYearHour = map.get("quarterYearHour").toString();
        String annualLeaveStatisTime = map.get("annualLeaveStatisTime").toString();
        sysEveUserStaffDao.editSysUserStaffAnnualLeaveById(staffId, quarterYearHour, annualLeaveStatisTime);
    }

    /**
     * 修改员工的补休池剩余补休信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void updateSysUserStaffHolidayNumberById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String staffId = map.get("staffId").toString();
        String holidayNumber = map.get("holidayNumber").toString();
        String holidayStatisTime = map.get("holidayStatisTime").toString();
        sysEveUserStaffDao.updateSysUserStaffHolidayNumberById(staffId, holidayNumber, holidayStatisTime);
    }

    /**
     * 修改员工的补休池已休补休信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void updateSysUserStaffRetiredHolidayNumberById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String staffId = map.get("staffId").toString();
        String retiredHolidayNumber = map.get("retiredHolidayNumber").toString();
        String retiredHolidayStatisTime = map.get("retiredHolidayStatisTime").toString();
        sysEveUserStaffDao.updateSysUserStaffRetiredHolidayNumberById(staffId, retiredHolidayNumber, retiredHolidayStatisTime);
    }

    /**
     * 根据员工id获取该员工的考勤时间段
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryStaffCheckWorkTimeRelationNameByStaffId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String staffId = map.get("staffId").toString();
        // 员工考勤时间段信息
        List<Map<String, Object>> staffTimeMation = sysEveUserStaffDao.queryStaffCheckWorkTimeRelationNameByStaffId(staffId);
        outputObject.setBeans(staffTimeMation);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    @Override
    public void editSysUserStaffBindUserId(String staffId, String userId) {
        UpdateWrapper<SysEveUserStaff> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, staffId);
        updateWrapper.set(MybatisPlusUtil.toColumns(SysEveUserStaff::getUserId), userId);
        update(updateWrapper);
    }

    @Override
    public void queryAllSysUserIsIncumbency(InputObject inputObject, OutputObject outputObject) {
        List<Integer> list = new ArrayList<>();
        list.add(UserStaffState.ON_THE_JOB.getKey());
        list.add(UserStaffState.PROBATION.getKey());
        list.add(UserStaffState.PROBATION_PERIOD.getKey());

        QueryWrapper<SysEveUserStaff> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(SysEveUserStaff::getState), list);
        String userIdKey = MybatisPlusUtil.toColumns(SysEveUserStaff::getUserId);
        queryWrapper.isNotNull(userIdKey).ne(userIdKey, StrUtil.EMPTY);
        List<SysEveUserStaff> userStaffList = list(queryWrapper);
        List<Map<String, Object>> mapList = userStaffList.stream()
            .map(userStaff -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", userStaff.getUserId());
                map.put("name", userStaff.getUserName());
                map.put("email", userStaff.getEmail());
                return map;
            }).collect(Collectors.toList());
        outputObject.setBeans(mapList);
        outputObject.settotal(mapList.size());
    }

}
