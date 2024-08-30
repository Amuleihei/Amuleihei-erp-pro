/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.certificate.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.certificate.dao.CertificateDao;
import com.skyeye.certificate.entity.Certificate;
import com.skyeye.certificate.service.CertificateService;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CertificateServiceImpl
 * @Description: 员工证书管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:37
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "员工证书", groupName = "员工证书", teamAuth = true)
public class CertificateServiceImpl extends SkyeyeBusinessServiceImpl<CertificateDao, Certificate> implements CertificateService {

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryCertificateList(commonPageInfo);
        return beans;
    }

    @Override
    public Certificate selectById(String id) {
        Certificate certificate = super.selectById(id);
        iSysDictDataService.setDataMation(certificate, Certificate::getTypeId);
        return certificate;
    }

}
