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
import com.skyeye.history.dao.AutoHistoryStepApiDao;
import com.skyeye.history.entity.AutoHistoryStepApi;
import com.skyeye.history.service.AutoHistoryStepApiService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName: AutoHistoryStepApiServiceImpl
 * @Description: API执行历史服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/16 22:31
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "API执行历史管理", groupName = "API执行历史管理", manageShow = false)
public class AutoHistoryStepApiServiceImpl extends SkyeyeBusinessServiceImpl<AutoHistoryStepApiDao, AutoHistoryStepApi> implements AutoHistoryStepApiService {

    @Override
    public void saveList(String objectId, List<AutoHistoryStepApi> list) {
        if (CollectionUtil.isNotEmpty(list)) {
            for (AutoHistoryStepApi apis : list) {
                apis.setHistoryCaseId(objectId);
            }
            createEntity(list, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByObjectId(String objectId) {
        QueryWrapper<AutoHistoryStepApi> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AutoHistoryStepApi::getHistoryCaseId), objectId);
        remove(queryWrapper);
    }

    @Override
    public Map<String, AutoHistoryStepApi> selectByObjectId(String objectId) {
        QueryWrapper<AutoHistoryStepApi> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AutoHistoryStepApi::getHistoryCaseId), objectId);
        List<AutoHistoryStepApi> list = list(queryWrapper);
        return list.stream().collect(Collectors.toMap(AutoHistoryStepApi::getHistoryStepId, Function.identity(), (v1, v2) -> v1));
    }
}
