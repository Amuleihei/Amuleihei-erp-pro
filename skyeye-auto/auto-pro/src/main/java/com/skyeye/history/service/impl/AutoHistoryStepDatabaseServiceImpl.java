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
import com.skyeye.history.dao.AutoHistoryStepDatabaseDao;
import com.skyeye.history.entity.AutoHistoryStepDatabase;
import com.skyeye.history.service.AutoHistoryStepDatabaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName: AutoHistoryStepDatabaseServiceImpl
 * @Description: 数据库执行历史服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/17 8:12
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "数据库执行历史管理", groupName = "数据库执行历史管理", manageShow = false)
public class AutoHistoryStepDatabaseServiceImpl extends SkyeyeBusinessServiceImpl<AutoHistoryStepDatabaseDao, AutoHistoryStepDatabase> implements AutoHistoryStepDatabaseService {

    @Override
    public void saveList(String objectId, List<AutoHistoryStepDatabase> list) {
        if (CollectionUtil.isNotEmpty(list)) {
            for (AutoHistoryStepDatabase historyStepDatabase : list) {
                historyStepDatabase.setHistoryCaseId(objectId);
            }
            createEntity(list, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByObjectId(String objectId) {
        QueryWrapper<AutoHistoryStepDatabase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AutoHistoryStepDatabase::getHistoryCaseId), objectId);
        remove(queryWrapper);
    }

    @Override
    public Map<String, AutoHistoryStepDatabase> selectByObjectId(String objectId) {
        QueryWrapper<AutoHistoryStepDatabase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AutoHistoryStepDatabase::getHistoryCaseId), objectId);
        List<AutoHistoryStepDatabase> list = list(queryWrapper);
        return list.stream().collect(Collectors.toMap(AutoHistoryStepDatabase::getHistoryStepId, Function.identity(), (v1, v2) -> v1));
    }
}
