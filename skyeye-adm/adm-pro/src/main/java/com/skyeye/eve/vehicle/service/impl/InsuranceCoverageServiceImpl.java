/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.vehicle.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.vehicle.dao.InsuranceCoverageDao;
import com.skyeye.eve.vehicle.entity.InsuranceCoverage;
import com.skyeye.eve.vehicle.service.InsuranceCoverageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: InsuranceCoverageServiceImpl
 * @Description: 车辆保险关联的险种服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/5/12 10:06
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "车辆保险关联的险种", groupName = "车辆模块", manageShow = false)
public class InsuranceCoverageServiceImpl extends SkyeyeBusinessServiceImpl<InsuranceCoverageDao, InsuranceCoverage> implements InsuranceCoverageService {

    @Override
    public void deleteInsuranceCoverageByPId(String parentId) {
        QueryWrapper<InsuranceCoverage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(InsuranceCoverage::getParentId), parentId);
        remove(queryWrapper);
    }

    @Override
    public void saveInsuranceCoverage(String parentId, List<InsuranceCoverage> vehicleInsuranceCoverages, String userId) {
        deleteInsuranceCoverageByPId(parentId);
        if (CollectionUtil.isNotEmpty(vehicleInsuranceCoverages)) {
            for (InsuranceCoverage farmProcedure : vehicleInsuranceCoverages) {
                farmProcedure.setParentId(parentId);
            }
            createEntity(vehicleInsuranceCoverages, userId);
        }
    }

    @Override
    public List<InsuranceCoverage> queryInsuranceCoverageByPId(String parentId) {
        QueryWrapper<InsuranceCoverage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(InsuranceCoverage::getParentId), parentId);
        List<InsuranceCoverage> vehicleInsuranceCoverages = list(queryWrapper);
        return vehicleInsuranceCoverages;
    }
}
