/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.history.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.history.dao.AutoHistoryStepDao;
import com.skyeye.history.entity.*;
import com.skyeye.history.service.*;
import com.skyeye.usercase.classenum.AutoStepTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: AutoHistoryStepServiceImpl
 * @Description: 步骤执行历史服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/16 20:37
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "步骤执行历史管理", groupName = "步骤执行历史管理", manageShow = false)
public class AutoHistoryStepServiceImpl extends SkyeyeBusinessServiceImpl<AutoHistoryStepDao, AutoHistoryStep> implements AutoHistoryStepService {

    @Autowired
    private AutoHistoryStepApiService autoHistoryStepApiService;

    @Autowired
    private AutoHistoryStepAssertService autoHistoryStepAssertService;

    @Autowired
    private AutoHistoryStepDatabaseService autoHistoryStepDatabaseService;

    @Autowired
    private AutoHistoryStepCaseService autoHistoryStepCaseService;

    @Override
    public void writePostpose(List<AutoHistoryStep> autoStepList, String userId) {
        if (CollectionUtil.isEmpty(autoStepList)) {
            return;
        }
        Map<Integer, List<AutoHistoryStep>> listMap = autoStepList.stream()
            .collect(Collectors.groupingBy(AutoHistoryStep::getType));
        List<AutoHistoryStepAssert> autoHistoryStepAsserts = new ArrayList<>();
        List<AutoHistoryStepApi> autoHistoryStepApis = new ArrayList<>();
        List<AutoHistoryStepCase> autoHistoryStepCases = new ArrayList<>();
        List<AutoHistoryStepDatabase> autoHistoryStepDatabases = new ArrayList<>();
        listMap.forEach((key, value) -> {
            if (key.equals(AutoStepTypeEnum.STEP.getKey())) {
                value.forEach(step -> {
                    step.getAutoHistoryStepApi().setHistoryStepId(step.getId());
                    autoHistoryStepApis.add(step.getAutoHistoryStepApi());
                });
            } else if (key.equals(AutoStepTypeEnum.CASE.getKey())) {
                value.forEach(step -> {
                    step.getAutoHistoryStepCase().setHistoryStepId(step.getId());
                    autoHistoryStepCases.add(step.getAutoHistoryStepCase());
                });
            } else if (key.equals(AutoStepTypeEnum.DATABASE.getKey())) {
                value.forEach(step -> {
                    step.getAutoHistoryStepDatabase().setHistoryStepId(step.getId());
                    autoHistoryStepDatabases.add(step.getAutoHistoryStepDatabase());
                });
            }
            value.forEach(bean -> {
                // 断言
                if (CollectionUtil.isNotEmpty(bean.getAutoHistoryStepAssertList())) {
                    bean.getAutoHistoryStepAssertList().forEach(item -> {
                        item.setHistoryStepId(bean.getId());
                    });
                    autoHistoryStepAsserts.addAll(bean.getAutoHistoryStepAssertList());
                }
            });
        });

        String historyCaseId = autoStepList.stream().findFirst().orElse(new AutoHistoryStep()).getHistoryCaseId();
        autoHistoryStepApiService.saveList(historyCaseId, autoHistoryStepApis);
        autoHistoryStepCaseService.saveList(historyCaseId, autoHistoryStepCases);
        autoHistoryStepDatabaseService.saveList(historyCaseId, autoHistoryStepDatabases);
        autoHistoryStepAssertService.saveList(historyCaseId, autoHistoryStepAsserts);
    }

    @Override
    public List<AutoHistoryStep> queryAutoStepListByCaseId(String historyCaseId) {
        QueryWrapper<AutoHistoryStep> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AutoHistoryStep::getHistoryCaseId), historyCaseId);
        queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(AutoHistoryStep::getOrderBy));
        List<AutoHistoryStep> autoHistoryStepList = list(queryWrapper);

        Map<String, AutoHistoryStepApi> autoHistoryStepApiMap = autoHistoryStepApiService.selectByObjectId(historyCaseId);
        Map<String, AutoHistoryStepCase> autoHistoryStepCaseMap = autoHistoryStepCaseService.selectByObjectId(historyCaseId);
        Map<String, AutoHistoryStepDatabase> autoHistoryStepDatabaseMap = autoHistoryStepDatabaseService.selectByObjectId(historyCaseId);
        Map<String, List<AutoHistoryStepAssert>> autoHistoryStepAssertMap = autoHistoryStepAssertService.selectByObjectId(historyCaseId);
        autoHistoryStepList.forEach(autoHistoryStep -> {
            if (autoHistoryStep.getType().equals(AutoStepTypeEnum.STEP.getKey())) {
                autoHistoryStep.setAutoHistoryStepApi(autoHistoryStepApiMap.get(autoHistoryStep.getId()));
            } else if (autoHistoryStep.getType().equals(AutoStepTypeEnum.CASE.getKey())) {
                autoHistoryStep.setAutoHistoryStepCase(autoHistoryStepCaseMap.get(autoHistoryStep.getId()));
            } else if (autoHistoryStep.getType().equals(AutoStepTypeEnum.DATABASE.getKey())) {
                autoHistoryStep.setAutoHistoryStepDatabase(autoHistoryStepDatabaseMap.get(autoHistoryStep.getId()));
            }
            // 断言
            autoHistoryStep.setAutoHistoryStepAssertList(autoHistoryStepAssertMap.get(autoHistoryStep.getId()));
        });
        return autoHistoryStepList;
    }

    @Override
    public void deleteByObjectId(String objectId) {
        autoHistoryStepApiService.deleteByObjectId(objectId);
        autoHistoryStepCaseService.deleteByObjectId(objectId);
        autoHistoryStepDatabaseService.deleteByObjectId(objectId);
        autoHistoryStepAssertService.deleteByObjectId(objectId);
        QueryWrapper<AutoHistoryStep> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AutoHistoryStep::getHistoryCaseId), objectId);
        remove(queryWrapper);
    }
}
