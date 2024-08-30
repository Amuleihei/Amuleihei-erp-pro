/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.datafrom.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.datafrom.dao.ReportDataFromRestDao;
import com.skyeye.datafrom.entity.ReportDataFromRest;
import com.skyeye.datafrom.entity.ReportDataFromRestAnalysis;
import com.skyeye.datafrom.service.ReportDataFromRestAnalysisService;
import com.skyeye.datafrom.service.ReportDataFromRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ReportDataFromRestServiceImpl
 * @Description: Rest格式的数据来源服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/3 22:21
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "Rest格式的数据来源", groupName = "Rest格式的数据来源", manageShow = false)
public class ReportDataFromRestServiceImpl extends SkyeyeBusinessServiceImpl<ReportDataFromRestDao, ReportDataFromRest> implements ReportDataFromRestService {

    @Autowired
    private ReportDataFromRestAnalysisService reportDataFromRestAnalysisService;

    @Override
    public void writePostpose(ReportDataFromRest entity, String userId) {
        super.writePostpose(entity, userId);
        reportDataFromRestAnalysisService.saveList(entity.getId(), entity.getAnalysisList());
    }

    @Override
    public void deletePostpose(String id) {
        reportDataFromRestAnalysisService.deleteByObjectId(id);
    }

    @Override
    public void deleteByFromId(String fromId) {
        QueryWrapper<ReportDataFromRest> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportDataFromRest::getFromId), fromId);
        ReportDataFromRest dataFromRest = getOne(queryWrapper, false);
        if (ObjectUtil.isNotEmpty(dataFromRest)) {
            deleteById(dataFromRest.getId());
        }
    }

    @Override
    public ReportDataFromRest getByFromId(String fromId) {
        QueryWrapper<ReportDataFromRest> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportDataFromRest::getFromId), fromId);
        ReportDataFromRest dataFromRest = getOne(queryWrapper, false);
        if (ObjectUtil.isNotEmpty(dataFromRest)) {
            List<ReportDataFromRestAnalysis> analysisList = reportDataFromRestAnalysisService.selectByObjectId(dataFromRest.getId());
            dataFromRest.setAnalysisList(analysisList);
        }
        return dataFromRest;
    }
}
