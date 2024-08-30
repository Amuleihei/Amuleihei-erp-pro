/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.history.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.history.dao.AutoHistoryStepCaseDao;
import com.skyeye.history.entity.AutoHistoryStepCase;
import com.skyeye.history.service.AutoHistoryStepCaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName: AutoHistoryStepCaseServiceImpl
 * @Description: 步骤为用例的执行历史服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/17 9:04
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "步骤为用例的执行历史管理", groupName = "步骤为用例的执行历史管理", manageShow = false)
public class AutoHistoryStepCaseServiceImpl extends SkyeyeBusinessServiceImpl<AutoHistoryStepCaseDao, AutoHistoryStepCase> implements AutoHistoryStepCaseService {

    @Override
    public void saveList(String objectId, List<AutoHistoryStepCase> list) {
        if (CollectionUtil.isNotEmpty(list)) {
            for (AutoHistoryStepCase autoHistoryStepCase : list) {
                autoHistoryStepCase.setHistoryCaseId(objectId);
            }
            createEntity(list, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByObjectId(String objectId) {
        QueryWrapper<AutoHistoryStepCase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AutoHistoryStepCase::getHistoryCaseId), objectId);
        remove(queryWrapper);
    }

    @Override
    public Map<String, AutoHistoryStepCase> selectByObjectId(String objectId) {
        QueryWrapper<AutoHistoryStepCase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AutoHistoryStepCase::getHistoryCaseId), objectId);
        List<AutoHistoryStepCase> list = list(queryWrapper);
        return list.stream().collect(Collectors.toMap(AutoHistoryStepCase::getHistoryStepId, Function.identity(), (v1, v2) -> v1));
    }
}
