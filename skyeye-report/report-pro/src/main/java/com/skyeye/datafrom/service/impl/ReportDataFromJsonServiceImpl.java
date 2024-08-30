/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.datafrom.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.datafrom.dao.ReportDataFromJsonDao;
import com.skyeye.datafrom.entity.ReportDataFromJson;
import com.skyeye.datafrom.entity.ReportDataFromJsonAnalysis;
import com.skyeye.datafrom.service.ReportDataFromJsonAnalysisService;
import com.skyeye.datafrom.service.ReportDataFromJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ReportDataFromJsonServiceImpl
 * @Description: JSON格式的数据来源服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/3 21:47
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "JSON格式的数据来源", groupName = "JSON格式的数据来源", manageShow = false)
public class ReportDataFromJsonServiceImpl extends SkyeyeBusinessServiceImpl<ReportDataFromJsonDao, ReportDataFromJson> implements ReportDataFromJsonService {

    @Autowired
    private ReportDataFromJsonAnalysisService reportDataFromJsonAnalysisService;

    @Override
    public void writePostpose(ReportDataFromJson entity, String userId) {
        super.writePostpose(entity, userId);
        reportDataFromJsonAnalysisService.saveList(entity.getId(), entity.getAnalysisList());
    }

    @Override
    public void deletePostpose(String id) {
        reportDataFromJsonAnalysisService.deleteByObjectId(id);
    }

    @Override
    public void deleteByFromId(String fromId) {
        QueryWrapper<ReportDataFromJson> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportDataFromJson::getFromId), fromId);
        ReportDataFromJson dataFromJson = getOne(queryWrapper, false);
        if (ObjectUtil.isNotEmpty(dataFromJson)) {
            deleteById(dataFromJson.getId());
        }
    }

    @Override
    public ReportDataFromJson getByFromId(String fromId) {
        QueryWrapper<ReportDataFromJson> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportDataFromJson::getFromId), fromId);
        ReportDataFromJson dataFromJson = getOne(queryWrapper, false);
        if (ObjectUtil.isNotEmpty(dataFromJson)) {
            List<ReportDataFromJsonAnalysis> analysisList = reportDataFromJsonAnalysisService.selectByObjectId(dataFromJson.getId());
            dataFromJson.setAnalysisList(analysisList);
        }
        return dataFromJson;
    }
}
