/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.server.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.coderule.entity.CodeRule;
import com.skyeye.coderule.service.CodeRuleService;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.server.dao.ServiceBeanCustomDao;
import com.skyeye.server.entity.ServiceBean;
import com.skyeye.server.entity.ServiceBeanCustom;
import com.skyeye.server.service.ServiceBeanCustomService;
import com.skyeye.server.service.ServiceBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName: ServiceBeanCustomServiceImpl
 * @Description: 自定义服务管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/1/6 22:44
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class ServiceBeanCustomServiceImpl extends SkyeyeBusinessServiceImpl<ServiceBeanCustomDao, ServiceBeanCustom> implements ServiceBeanCustomService {

    @Autowired
    private ServiceBeanService serviceBeanService;

    @Autowired
    private CodeRuleService codeRuleService;

    @Override
    public void queryServiceBeanCustom(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String className = params.get("className").toString();
        String appId = params.get("appId").toString();
        ServiceBeanCustom serviceBeanCustom = selectServiceBeanCustom(appId, className);
        outputObject.setBean(serviceBeanCustom);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    @Override
    public ServiceBeanCustom selectServiceBeanCustom(String appId, String className) {
        QueryWrapper<ServiceBeanCustom> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(appId)) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(ServiceBeanCustom::getAppId), appId);
        }
        queryWrapper.eq(MybatisPlusUtil.toColumns(ServiceBeanCustom::getClassName), className);
        ServiceBeanCustom serviceBeanCustom = getOne(queryWrapper, false);
        ServiceBean serviceBean = serviceBeanService.queryServiceClass(appId, className);
        if (serviceBeanCustom == null) {
            serviceBeanCustom = new ServiceBeanCustom();
        }
        serviceBeanCustom.setServiceBean(serviceBean);
        if (StrUtil.isNotEmpty(serviceBeanCustom.getCodeRuleId())) {
            CodeRule codeRule = codeRuleService.selectById(serviceBeanCustom.getCodeRuleId());
            serviceBeanCustom.setCodeRule(codeRule);
        }
        return serviceBeanCustom;
    }

    @Override
    public void builderByHandler(ServiceBeanCustom bean) {
        if (bean != null) {
            super.builderByHandler(bean);
        }
    }

}
