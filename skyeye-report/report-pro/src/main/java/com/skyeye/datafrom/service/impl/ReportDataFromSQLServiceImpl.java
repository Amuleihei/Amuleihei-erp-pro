/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.datafrom.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.datafrom.dao.ReportDataFromSQLDao;
import com.skyeye.datafrom.entity.ReportDataFromSQL;
import com.skyeye.datafrom.entity.ReportDataFromSQLAnalysis;
import com.skyeye.datafrom.service.ReportDataFromSQLAnalysisService;
import com.skyeye.datafrom.service.ReportDataFromSQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ReportDataFromSQLServiceImpl
 * @Description: SQL格式的数据来源服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/4 8:36
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "SQL格式的数据来源", groupName = "SQL格式的数据来源", manageShow = false)
public class ReportDataFromSQLServiceImpl extends SkyeyeBusinessServiceImpl<ReportDataFromSQLDao, ReportDataFromSQL> implements ReportDataFromSQLService {

    @Autowired
    private ReportDataFromSQLAnalysisService reportDataFromSQLAnalysisService;

    @Override
    public void writePostpose(ReportDataFromSQL entity, String userId) {
        super.writePostpose(entity, userId);
        reportDataFromSQLAnalysisService.saveList(entity.getId(), entity.getAnalysisList());
    }

    @Override
    public void deletePostpose(String id) {
        reportDataFromSQLAnalysisService.deleteByObjectId(id);
    }

    @Override
    public void deleteByFromId(String fromId) {
        QueryWrapper<ReportDataFromSQL> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportDataFromSQL::getFromId), fromId);
        ReportDataFromSQL dataFromSQL = getOne(queryWrapper, false);
        if (ObjectUtil.isNotEmpty(dataFromSQL)) {
            deleteById(dataFromSQL.getId());
        }
    }

    @Override
    public ReportDataFromSQL getByFromId(String fromId) {
        QueryWrapper<ReportDataFromSQL> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportDataFromSQL::getFromId), fromId);
        ReportDataFromSQL dataFromSQL = getOne(queryWrapper, false);
        if (ObjectUtil.isNotEmpty(dataFromSQL)) {
            List<ReportDataFromSQLAnalysis> analysisList = reportDataFromSQLAnalysisService.selectByObjectId(dataFromSQL.getId());
            dataFromSQL.setAnalysisList(analysisList);
        }
        return dataFromSQL;
    }
}
