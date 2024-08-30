/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.datafrom.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.datafrom.dao.ReportDataFromXMLDao;
import com.skyeye.datafrom.entity.ReportDataFromXML;
import com.skyeye.datafrom.entity.ReportDataFromXMLAnalysis;
import com.skyeye.datafrom.service.ReportDataFromXMLAnalysisService;
import com.skyeye.datafrom.service.ReportDataFromXMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ReportDataFromXMLServiceImpl
 * @Description: XML格式的数据来源服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/4 8:48
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "XML格式的数据来源", groupName = "XML格式的数据来源", manageShow = false)
public class ReportDataFromXMLServiceImpl extends SkyeyeBusinessServiceImpl<ReportDataFromXMLDao, ReportDataFromXML> implements ReportDataFromXMLService {

    @Autowired
    private ReportDataFromXMLAnalysisService reportDataFromXMLAnalysisService;

    @Override
    public void writePostpose(ReportDataFromXML entity, String userId) {
        super.writePostpose(entity, userId);
        reportDataFromXMLAnalysisService.saveList(entity.getId(), entity.getAnalysisList());
    }

    @Override
    public void deletePostpose(String id) {
        reportDataFromXMLAnalysisService.deleteByObjectId(id);
    }

    @Override
    public void deleteByFromId(String fromId) {
        QueryWrapper<ReportDataFromXML> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportDataFromXML::getFromId), fromId);
        ReportDataFromXML dataFromXML = getOne(queryWrapper, false);
        if (ObjectUtil.isNotEmpty(dataFromXML)) {
            deleteById(dataFromXML.getId());
        }
    }

    @Override
    public ReportDataFromXML getByFromId(String fromId) {
        QueryWrapper<ReportDataFromXML> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportDataFromXML::getFromId), fromId);
        ReportDataFromXML dataFromXML = getOne(queryWrapper, false);
        if (ObjectUtil.isNotEmpty(dataFromXML)) {
            List<ReportDataFromXMLAnalysis> analysisList = reportDataFromXMLAnalysisService.selectByObjectId(dataFromXML.getId());
            dataFromXML.setAnalysisList(analysisList);
        }
        return dataFromXML;
    }
}
