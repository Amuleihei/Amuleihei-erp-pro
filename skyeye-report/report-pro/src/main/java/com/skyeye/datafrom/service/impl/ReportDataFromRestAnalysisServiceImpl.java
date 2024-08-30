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
import com.skyeye.datafrom.dao.ReportDataFromRestAnalysisDao;
import com.skyeye.datafrom.entity.ReportDataFromRestAnalysis;
import com.skyeye.datafrom.service.ReportDataFromRestAnalysisService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ReportDataFromRestAnalysisServiceImpl
 * @Description: Rest数据对应的解析信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/3 22:58
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "Rest数据对应的解析信息", groupName = "Rest数据对应的解析信息", manageShow = false)
public class ReportDataFromRestAnalysisServiceImpl extends SkyeyeBusinessServiceImpl<ReportDataFromRestAnalysisDao, ReportDataFromRestAnalysis> implements ReportDataFromRestAnalysisService {

    @Override
    public void saveList(String objectId, List<ReportDataFromRestAnalysis> beans) {
        deleteByObjectId(objectId);
        if (CollectionUtil.isNotEmpty(beans)) {
            for (ReportDataFromRestAnalysis analysis : beans) {
                analysis.setRestId(objectId);
            }
            createEntity(beans, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByObjectId(String objectId) {
        QueryWrapper<ReportDataFromRestAnalysis> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportDataFromRestAnalysis::getRestId), objectId);
        remove(queryWrapper);
    }

    @Override
    public List<ReportDataFromRestAnalysis> selectByObjectId(String objectId) {
        QueryWrapper<ReportDataFromRestAnalysis> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportDataFromRestAnalysis::getRestId), objectId);
        List<ReportDataFromRestAnalysis> list = list(queryWrapper);
        return list;
    }

}
