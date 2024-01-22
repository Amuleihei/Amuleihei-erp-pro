/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.organization.dao.CompanyTaxRateDao;
import com.skyeye.organization.entity.CompanyTaxRate;
import com.skyeye.organization.service.CompanyTaxRateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: CompanyTaxRateServiceImpl
 * @Description: 公司个人所得税税率信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/1/22 15:47
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Service
@SkyeyeService(name = "公司个人所得税税率信息", groupName = "组织模块", manageShow = false)
public class CompanyTaxRateServiceImpl extends SkyeyeBusinessServiceImpl<CompanyTaxRateDao, CompanyTaxRate> implements CompanyTaxRateService {

    @Override
    public void deleteCompanyTaxRateByPId(String companyId) {
        QueryWrapper<CompanyTaxRate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(CompanyTaxRate::getCompanyId), companyId);
        remove(queryWrapper);
    }

    @Override
    public void saveCompanyTaxRate(String companyId, List<CompanyTaxRate> companyTaxRateList) {
        deleteCompanyTaxRateByPId(companyId);
        if (CollectionUtil.isNotEmpty(companyTaxRateList)) {
            for (CompanyTaxRate companyTaxRate : companyTaxRateList) {
                companyTaxRate.setCompanyId(companyId);
            }
            createEntity(companyTaxRateList, StrUtil.EMPTY);
        }
    }

    @Override
    public List<CompanyTaxRate> queryCompanyTaxRateByPId(String companyId) {
        QueryWrapper<CompanyTaxRate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(CompanyTaxRate::getCompanyId), companyId);
        queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(CompanyTaxRate::getSortNo));
        List<CompanyTaxRate> companyTaxRates = list(queryWrapper);
        return companyTaxRates;
    }

    @Override
    public Map<String, List<CompanyTaxRate>> queryCompanyTaxRateByPId(List<String> companyId) {
        QueryWrapper<CompanyTaxRate> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(CompanyTaxRate::getCompanyId), companyId);
        queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(CompanyTaxRate::getSortNo));
        List<CompanyTaxRate> companyTaxRates = list(queryWrapper);
        Map<String, List<CompanyTaxRate>> listMap = companyTaxRates.stream()
            .collect(Collectors.groupingBy(CompanyTaxRate::getCompanyId));
        return listMap;
    }
}
