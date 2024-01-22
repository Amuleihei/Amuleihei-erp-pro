/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.organization.entity.CompanyTaxRate;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CompanyTaxRateService
 * @Description: 公司个人所得税税率信息服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/1/22 15:47
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public interface CompanyTaxRateService extends SkyeyeBusinessService<CompanyTaxRate> {

    void deleteCompanyTaxRateByPId(String companyId);

    void saveCompanyTaxRate(String companyId, List<CompanyTaxRate> companyTaxRateList);

    List<CompanyTaxRate> queryCompanyTaxRateByPId(String companyId);

    Map<String, List<CompanyTaxRate>> queryCompanyTaxRateByPId(List<String> companyId);

}
