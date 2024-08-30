/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.usercase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.usercase.classenum.AutoStepTypeEnum;
import com.skyeye.usercase.dao.AutoStepDao;
import com.skyeye.usercase.entity.*;
import com.skyeye.usercase.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: AutoStepServiceImpl
 * @Description: 用例步骤管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:20
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
@Service
@SkyeyeService(name = "用例步骤管理", groupName = "用例步骤管理", manageShow = false)
public class AutoStepServiceImpl extends SkyeyeBusinessServiceImpl<AutoStepDao, AutoStep> implements AutoStepService {

    @Autowired
    private AutoStepInputService autoStepInputService;

    @Autowired
    private AutoStepAssertService autoStepAssertService;

    @Autowired
    private AutoStepApiService autoStepApiService;

    @Autowired
    private AutoStepCaseService autoStepCaseService;

    @Autowired
    private AutoStepDatabaseService autoStepDatabaseService;

    @Override
    public void writePostpose(List<AutoStep> autoStepList, String userId) {
        Map<Integer, List<AutoStep>> listMap = autoStepList.stream()
            .collect(Collectors.groupingBy(AutoStep::getType));
        List<AutoStepInput> autoStepInputList = new ArrayList<>();
        List<AutoStepAssert> autoStepAssertList = new ArrayList<>();
        List<AutoStepApi> autoStepApiList = new ArrayList<>();
        List<AutoStepCase> autoStepCaseList = new ArrayList<>();
        List<AutoStepDatabase> autoStepDatabaseList = new ArrayList<>();
        listMap.forEach((key, value) -> {
            if (key.equals(AutoStepTypeEnum.STEP.getKey())) {
                value.forEach(step -> {
                    step.getStepApi().setStepId(step.getId());
                    autoStepApiList.add(step.getStepApi());
                });
            } else if (key.equals(AutoStepTypeEnum.CASE.getKey())) {
                value.forEach(step -> {
                    step.getStepCase().setStepId(step.getId());
                    autoStepCaseList.add(step.getStepCase());
                });
            } else if (key.equals(AutoStepTypeEnum.DATABASE.getKey())) {
                value.forEach(step -> {
                    step.getStepDatabase().setStepId(step.getId());
                    autoStepDatabaseList.add(step.getStepDatabase());
                });
            }
            value.forEach(bean -> {
                // 前置条件
                bean.getStepInputList().forEach(item -> {
                    item.setStepId(bean.getId());
                });
                autoStepInputList.addAll(bean.getStepInputList());
                // 断言
                bean.getStepAssertList().forEach(item -> {
                    item.setStepId(bean.getId());
                });
                autoStepAssertList.addAll(bean.getStepAssertList());
            });
        });
        autoStepApiService.saveList(autoStepList.stream().findFirst().orElse(new AutoStep()).getCaseId(), autoStepApiList);
        autoStepCaseService.saveList(autoStepList.stream().findFirst().orElse(new AutoStep()).getCaseId(), autoStepCaseList);
        autoStepDatabaseService.saveList(autoStepList.stream().findFirst().orElse(new AutoStep()).getCaseId(), autoStepDatabaseList);
        autoStepInputService.saveList(autoStepList.stream().findFirst().orElse(new AutoStep()).getCaseId(), autoStepInputList);
        autoStepAssertService.saveList(autoStepList.stream().findFirst().orElse(new AutoStep()).getCaseId(), autoStepAssertList);
    }

    @Override
    public List<AutoStep> queryAutoStepListByCaseId(String caseId) {
        QueryWrapper<AutoStep> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AutoStep::getCaseId), caseId);
        queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(AutoStep::getOrderBy));
        List<AutoStep> autoStepList = list(queryWrapper);

        Map<String, AutoStepApi> autoStepApiMap = autoStepApiService.selectByObjectId(caseId);
        Map<String, AutoStepCase> autoStepCaseMap = autoStepCaseService.selectByObjectId(caseId);
        Map<String, AutoStepDatabase> autoStepDatabaseMap = autoStepDatabaseService.selectByObjectId(caseId);
        Map<String, List<AutoStepInput>> autoStepMap = autoStepInputService.selectByObjectId(caseId);
        Map<String, List<AutoStepAssert>> autoStepAssertMap = autoStepAssertService.selectByObjectId(caseId);
        autoStepList.forEach(autoStep -> {
            if (autoStep.getType().equals(AutoStepTypeEnum.STEP.getKey())) {
                autoStep.setStepApi(autoStepApiMap.get(autoStep.getId()));
            } else if (autoStep.getType().equals(AutoStepTypeEnum.CASE.getKey())) {
                autoStep.setStepCase(autoStepCaseMap.get(autoStep.getId()));
            } else if (autoStep.getType().equals(AutoStepTypeEnum.DATABASE.getKey())) {
                autoStep.setStepDatabase(autoStepDatabaseMap.get(autoStep.getId()));
            }
            // 前置条件
            autoStep.setStepInputList(autoStepMap.get(autoStep.getId()));
            // 断言
            autoStep.setStepAssertList(autoStepAssertMap.get(autoStep.getId()));
        });
        return autoStepList;
    }

    @Override
    public void deleteByObjectId(String objectId) {
        autoStepCaseService.deleteByObjectId(objectId);
        autoStepDatabaseService.deleteByObjectId(objectId);
        autoStepInputService.deleteByObjectId(objectId);
        autoStepApiService.deleteByObjectId(objectId);
        autoStepAssertService.deleteByObjectId(objectId);
        QueryWrapper<AutoStep> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AutoStep::getCaseId), objectId);
        remove(queryWrapper);
    }
}

