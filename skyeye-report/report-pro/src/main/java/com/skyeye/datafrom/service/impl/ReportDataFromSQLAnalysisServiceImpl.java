/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.datafrom.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.datafrom.dao.ReportDataFromSQLAnalysisDao;
import com.skyeye.datafrom.entity.ReportDataFromSQLAnalysis;
import com.skyeye.datafrom.service.ReportDataFromSQLAnalysisService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ReportDataFromSQLAnalysisServiceImpl
 * @Description: SQL数据对应的解析信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/4 8:44
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "SQL数据对应的解析信息", groupName = "SQL数据对应的解析信息", manageShow = false)
public class ReportDataFromSQLAnalysisServiceImpl extends SkyeyeBusinessServiceImpl<ReportDataFromSQLAnalysisDao, ReportDataFromSQLAnalysis> implements ReportDataFromSQLAnalysisService {

    @Override
    public void saveList(String objectId, List<ReportDataFromSQLAnalysis> beans) {
        deleteByObjectId(objectId);
        if (CollectionUtil.isNotEmpty(beans)) {
            for (ReportDataFromSQLAnalysis analysis : beans) {
                analysis.setSqlId(objectId);
            }
            createEntity(beans, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByObjectId(String objectId) {
        QueryWrapper<ReportDataFromSQLAnalysis> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportDataFromSQLAnalysis::getSqlId), objectId);
        remove(queryWrapper);
    }

    @Override
    public List<ReportDataFromSQLAnalysis> selectByObjectId(String objectId) {
        QueryWrapper<ReportDataFromSQLAnalysis> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportDataFromSQLAnalysis::getSqlId), objectId);
        List<ReportDataFromSQLAnalysis> list = list(queryWrapper);
        return list;
    }
}
