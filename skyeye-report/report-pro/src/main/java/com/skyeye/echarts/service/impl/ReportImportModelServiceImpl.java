/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye-report
 ******************************************************************************/

package com.skyeye.echarts.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.echarts.dao.ReportImportModelDao;
import com.skyeye.echarts.entity.ImportModel;
import com.skyeye.echarts.service.ReportImportModelService;
import com.skyeye.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ReportImportModelServiceImpl
 * @Description: Echarts模型管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:21
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
@Service
@SkyeyeService(name = "Echarts模型管理", groupName = "Echarts模型管理")
public class ReportImportModelServiceImpl extends SkyeyeBusinessServiceImpl<ReportImportModelDao, ImportModel> implements ReportImportModelService {

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = skyeyeBaseMapper.getReportImportModelList(commonPageInfo);
        iSysDictDataService.setNameForMap(beans, "typeId", "typeName");
        return beans;
    }

    @Override
    public void validatorEntity(ImportModel entity) {
        super.validatorEntity(entity);
        QueryWrapper<ImportModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(wrapper ->
            wrapper.eq(MybatisPlusUtil.toColumns(ImportModel::getName), entity.getName())
                .or().eq(MybatisPlusUtil.toColumns(ImportModel::getModelCode), entity.getModelCode()));
        if (StringUtils.isNotEmpty(entity.getId())) {
            queryWrapper.ne(CommonConstants.ID, entity.getId());
        }
        ImportModel checkModel = getOne(queryWrapper);
        if (ObjectUtil.isNotEmpty(checkModel)) {
            throw new CustomException("this `name` OR `modelCode` is exist.");
        }
    }

    @Override
    public List<ImportModel> queryImportModelList(List<String> modelCodes) {
        QueryWrapper<ImportModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(ImportModel::getModelCode), modelCodes);
        List<ImportModel> importModels = list(queryWrapper);
        return importModels;
    }

}
