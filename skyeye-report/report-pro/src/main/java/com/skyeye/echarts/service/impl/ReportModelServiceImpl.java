/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.echarts.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.echarts.classenum.ReportModelState;
import com.skyeye.echarts.dao.ReportModelDao;
import com.skyeye.echarts.entity.ReportModel;
import com.skyeye.echarts.service.ReportModelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ReportModelServiceImpl
 * @Description: 模型版本服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/6/20 15:01
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "模型版本", groupName = "模型版本", manageShow = false)
public class ReportModelServiceImpl extends SkyeyeBusinessServiceImpl<ReportModelDao, ReportModel> implements ReportModelService {

    @Override
    public void createPrepose(ReportModel entity) {
        // 将之前的修改为废弃状态
        UpdateWrapper<ReportModel> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(MybatisPlusUtil.toColumns(ReportModel::getModelCode), entity.getModelCode());
        updateWrapper.set(MybatisPlusUtil.toColumns(ReportModel::getState), ReportModelState.ABANDONED.getKey());
        update(updateWrapper);
    }

    /**
     * 根据模型code获取一个最新的版本号
     *
     * @param modelCode 模型code
     * @return 最新的版本号
     */
    @Override
    public Integer queryNewMaxVersionByModelCode(String modelCode) {
        Integer version = 1;
        QueryWrapper<ReportModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportModel::getModelCode), modelCode);
        queryWrapper.orderByDesc(MybatisPlusUtil.toColumns(ReportModel::getSoftwareVersion));
        List<ReportModel> list = list(queryWrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            version = list.get(0).getSoftwareVersion() + 1;
        }
        return version;
    }

    @Override
    public List<ReportModel> queryAllMaxVersionReportModel() {
        QueryWrapper<ReportModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ReportModel::getState), ReportModelState.NORMAL.getKey());
        queryWrapper.orderByDesc(MybatisPlusUtil.toColumns(ReportModel::getSoftwareVersion));
        List<ReportModel> list = list(queryWrapper);
        return list;
    }
}
