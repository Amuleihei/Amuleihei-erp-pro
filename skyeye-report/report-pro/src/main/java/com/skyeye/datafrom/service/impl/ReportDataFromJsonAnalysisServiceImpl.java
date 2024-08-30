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
import com.skyeye.datafrom.dao.ReportDataFromJsonAnalysisDao;
import com.skyeye.datafrom.entity.ReportDataFromJsonAnalysis;
import com.skyeye.datafrom.service.ReportDataFromJsonAnalysisService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ReportDataFromJsonAnalysisServiceImpl
 * @Description: JSON数据对应的解析信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/3 21:59
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "JSON数据对应的解析信息", groupName = "JSON数据对应的解析信息", manageShow = false)
public class ReportDataFromJsonAnalysisServiceImpl extends SkyeyeBusinessServiceImpl<ReportDataFromJsonAnalysisDao, ReportDataFromJsonAnalysis> implements ReportDataFromJsonAnalysisService {

    @Override
    public void saveList(String objectId, List<ReportDataFromJsonAnalysis> beans) {
        deleteByObjectId(objectId);
        if (CollectionUtil.isNotEmpty(beans)) {
            for (ReportDataFromJsonAnalysis analysis : beans) {
                analysis.setJsonId(objectId);
            }
            createEntity(beans, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByObjectId(String objectId) {
        QueryWrapper<ReportDataFromJsonAnalysis> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportDataFromJsonAnalysis::getJsonId), objectId);
        remove(queryWrapper);
    }

    @Override
    public List<ReportDataFromJsonAnalysis> selectByObjectId(String objectId) {
        QueryWrapper<ReportDataFromJsonAnalysis> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportDataFromJsonAnalysis::getJsonId), objectId);
        List<ReportDataFromJsonAnalysis> list = list(queryWrapper);
        return list;
    }
}
