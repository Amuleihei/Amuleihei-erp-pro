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
import com.skyeye.datafrom.dao.ReportDataFromXMLAnalysisDao;
import com.skyeye.datafrom.entity.ReportDataFromXMLAnalysis;
import com.skyeye.datafrom.service.ReportDataFromXMLAnalysisService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ReportDataFromXMLAnalysisServiceImpl
 * @Description: XML数据对应的解析信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/4 8:54
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "XML数据对应的解析信息", groupName = "XML数据对应的解析信息", manageShow = false)
public class ReportDataFromXMLAnalysisServiceImpl extends SkyeyeBusinessServiceImpl<ReportDataFromXMLAnalysisDao, ReportDataFromXMLAnalysis> implements ReportDataFromXMLAnalysisService {

    @Override
    public void saveList(String objectId, List<ReportDataFromXMLAnalysis> beans) {
        deleteByObjectId(objectId);
        if (CollectionUtil.isNotEmpty(beans)) {
            for (ReportDataFromXMLAnalysis analysis : beans) {
                analysis.setXmlId(objectId);
            }
            createEntity(beans, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByObjectId(String objectId) {
        QueryWrapper<ReportDataFromXMLAnalysis> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportDataFromXMLAnalysis::getXmlId), objectId);
        remove(queryWrapper);
    }

    @Override
    public List<ReportDataFromXMLAnalysis> selectByObjectId(String objectId) {
        QueryWrapper<ReportDataFromXMLAnalysis> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportDataFromXMLAnalysis::getXmlId), objectId);
        List<ReportDataFromXMLAnalysis> list = list(queryWrapper);
        return list;
    }
}
