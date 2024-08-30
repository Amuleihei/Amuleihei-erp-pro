/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.machinprocedure.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.farm.service.FarmService;
import com.skyeye.machin.service.MachinService;
import com.skyeye.machinprocedure.classenum.MachinProcedureFarmState;
import com.skyeye.machinprocedure.classenum.MachinProcedureState;
import com.skyeye.machinprocedure.dao.MachinProcedureFarmDao;
import com.skyeye.machinprocedure.entity.MachinProcedureAccept;
import com.skyeye.machinprocedure.entity.MachinProcedureFarm;
import com.skyeye.machinprocedure.service.MachinProcedureAcceptService;
import com.skyeye.machinprocedure.service.MachinProcedureFarmService;
import com.skyeye.machinprocedure.service.MachinProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: MachinProcedureFarmServiceImpl
 * @Description: 车间任务服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/24 19:08
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "车间任务", groupName = "车间任务")
public class MachinProcedureFarmServiceImpl extends SkyeyeBusinessServiceImpl<MachinProcedureFarmDao, MachinProcedureFarm> implements MachinProcedureFarmService {

    @Autowired
    private FarmService farmService;

    @Autowired
    private MachinService machinService;

    @Autowired
    private MachinProcedureService machinProcedureService;

    @Autowired
    private MachinProcedureAcceptService machinProcedureAcceptService;

    @Override
    public QueryWrapper<MachinProcedureFarm> getQueryWrapper(CommonPageInfo commonPageInfo) {
        if (StrUtil.isEmpty(commonPageInfo.getType())) {
            throw new CustomException("type不能为空");
        }
        QueryWrapper<MachinProcedureFarm> queryWrapper = super.getQueryWrapper(commonPageInfo);
        if (StrUtil.equals(commonPageInfo.getType(), "farm")) {
            // 指定车间的任务
            queryWrapper.eq(MybatisPlusUtil.toColumns(MachinProcedureFarm::getFarmId), commonPageInfo.getObjectId());
        } else if (StrUtil.equals(commonPageInfo.getType(), "department")) {
            // 我所在部门的所有车间的任务
            String departmentId = InputObject.getLogParamsStatic().get("departmentId").toString();
            List<String> farmIdList = farmService.queryFarmIdListByDepartmentId(departmentId);
            queryWrapper.in(MybatisPlusUtil.toColumns(MachinProcedureFarm::getFarmId), farmIdList);
        } else if (StrUtil.equals(commonPageInfo.getType(), "all")) {
            // 所有任务
        }
        return queryWrapper;
    }

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        machinService.setMachinMationByFromId(beans, "machinId", "machinMation");
        farmService.setMationForMap(beans, "farmId", "farmMation");
        machinProcedureService.setMationForMap(beans, "machinProcedureId", "machinProcedureMation");
        return beans;
    }

    @Override
    public MachinProcedureFarm selectById(String id) {
        MachinProcedureFarm machinProcedureFarm = super.selectById(id);
        farmService.setDataMation(machinProcedureFarm, MachinProcedureFarm::getFarmId);
        machinService.setDataMation(machinProcedureFarm, MachinProcedureFarm::getMachinId);
        machinProcedureService.setDataMation(machinProcedureFarm, MachinProcedureFarm::getMachinProcedureId);
        return machinProcedureFarm;
    }

    @Override
    public List<MachinProcedureFarm> selectByIds(String... ids) {
        List<MachinProcedureFarm> machinProcedureFarms = super.selectByIds(ids);
        farmService.setDataMation(machinProcedureFarms, MachinProcedureFarm::getFarmId);
        return machinProcedureFarms;
    }

    @Override
    public Map<String, List<MachinProcedureFarm>> queryMachinProcedureFarmMapByMachinId(String machinId) {
        QueryWrapper<MachinProcedureFarm> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(MachinProcedureFarm::getMachinId), machinId);
        List<MachinProcedureFarm> machinProcedureFarmList = list(queryWrapper);
        if (CollectionUtil.isEmpty(machinProcedureFarmList)) {
            return MapUtil.newHashMap();
        }
        // 设置车间的工序验收单信息
        List<String> ids = machinProcedureFarmList.stream().map(MachinProcedureFarm::getId).collect(Collectors.toList());
        Map<String, List<MachinProcedureAccept>> machinProcedureAcceptMap = machinProcedureAcceptService
            .queryMachinProcedureAcceptByMachinProcedureFarmId(ids.toArray(new String[]{}));
        machinProcedureFarmList.forEach(machinProcedureFarm -> {
            machinProcedureFarm.setMachinProcedureAcceptList(machinProcedureAcceptMap.get(machinProcedureFarm.getId()));
        });
        Map<String, List<MachinProcedureFarm>> machinProcedureFarmMap = machinProcedureFarmList.stream()
            .collect(Collectors.groupingBy(MachinProcedureFarm::getMachinProcedureId));
        return machinProcedureFarmMap;
    }

    @Override
    public void receiveMachinProcedureFarm(InputObject inputObject, OutputObject outputObject) {
        String id = inputObject.getParams().get("id").toString();
        MachinProcedureFarm machinProcedureFarm = selectById(id);
        if (StrUtil.equals(machinProcedureFarm.getState(), MachinProcedureFarmState.WAIT_RECEIVE.getKey())) {
            // 待接收的车间任务可以进行接收
            editStateById(id, MachinProcedureFarmState.WAIT_EXECUTED.getKey());
        }
    }

    @Override
    public void receptionReceiveMachinProcedureFarm(InputObject inputObject, OutputObject outputObject) {
        String id = inputObject.getParams().get("id").toString();
        MachinProcedureFarm machinProcedureFarm = selectById(id);
        if (StrUtil.equals(machinProcedureFarm.getState(), MachinProcedureFarmState.WAIT_EXECUTED.getKey())) {
            // 待执行的车间任务可以进行反接收
            editStateById(id, MachinProcedureFarmState.WAIT_RECEIVE.getKey());
        }
    }

    @Override
    public void editStateById(String id, String state) {
        UpdateWrapper<MachinProcedureFarm> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, id);
        updateWrapper.set(MybatisPlusUtil.toColumns(MachinProcedureFarm::getState), state);
        update(updateWrapper);
        if (StrUtil.equals(state, MachinProcedureFarmState.PARTIAL_COMPLETION.getKey())) {
            // 部分完成
            MachinProcedureFarm machinProcedureFarm = selectById(id);
            machinProcedureService.editStateById(machinProcedureFarm.getMachinProcedureId(), MachinProcedureState.PARTIAL_COMPLETION.getKey());
        } else if (StrUtil.equals(state, MachinProcedureFarmState.ALL_COMPLETED.getKey())
            || StrUtil.equals(state, MachinProcedureFarmState.EXCESS_COMPLETED.getKey())) {
            // 全部完成/超额完成
            MachinProcedureFarm machinProcedureFarm = selectById(id);
            // 查询该工序下是否存在未接收/未执行/部分完成的车间任务
            List<String> stateList = Arrays.asList(new String[]{MachinProcedureFarmState.WAIT_RECEIVE.getKey(), MachinProcedureFarmState.WAIT_EXECUTED.getKey(),
                MachinProcedureFarmState.PARTIAL_COMPLETION.getKey()});
            QueryWrapper<MachinProcedureFarm> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(MybatisPlusUtil.toColumns(MachinProcedureFarm::getMachinProcedureId), machinProcedureFarm.getMachinProcedureId());
            queryWrapper.in(MybatisPlusUtil.toColumns(MachinProcedureFarm::getState), stateList);
            long count = count(queryWrapper);
            if (count == 0) {
                machinProcedureService.editStateById(machinProcedureFarm.getMachinProcedureId(), MachinProcedureState.ALL_COMPLETED.getKey());
            } else {
                machinProcedureService.editStateById(machinProcedureFarm.getMachinProcedureId(), MachinProcedureState.PARTIAL_COMPLETION.getKey());
            }
        }
    }

    @Override
    public void deleteMachinProcedureFarmByMachinProcedureId(String machinProcedureId) {
        // 只删除未接收的
        QueryWrapper<MachinProcedureFarm> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(MachinProcedureFarm::getMachinProcedureId), machinProcedureId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(MachinProcedureFarm::getState), MachinProcedureFarmState.WAIT_RECEIVE.getKey());
        remove(queryWrapper);
    }

    @Override
    public List<MachinProcedureFarm> queryMachinProcedureFarmByMachinProcedureId(String machinProcedureId) {
        QueryWrapper<MachinProcedureFarm> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(MachinProcedureFarm::getMachinProcedureId), machinProcedureId);
        queryWrapper.ne(MybatisPlusUtil.toColumns(MachinProcedureFarm::getState), MachinProcedureFarmState.WAIT_RECEIVE.getKey());
        List<MachinProcedureFarm> machinProcedureFarmList = list(queryWrapper);
        return machinProcedureFarmList;
    }

    @Override
    public List<MachinProcedureFarm> queryAllMachinProcedureFarmByMachinProcedureId(String machinProcedureId) {
        QueryWrapper<MachinProcedureFarm> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(MachinProcedureFarm::getMachinProcedureId), machinProcedureId);
        List<MachinProcedureFarm> machinProcedureFarmList = list(queryWrapper);
        farmService.setDataMation(machinProcedureFarmList, MachinProcedureFarm::getFarmId);
        machinProcedureFarmList.forEach(machinProcedureFarm -> {
            machinProcedureFarm.setStateMation(MachinProcedureFarmState.getMation(machinProcedureFarm.getState()));
        });
        return machinProcedureFarmList;
    }

}
