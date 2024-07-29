/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tenant.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.tenant.dao.TenantDao;
import com.skyeye.tenant.entity.Tenant;
import com.skyeye.tenant.service.TenantService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TenantServiceImpl
 * @Description: 租户服务实现层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/28 20:14
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "租户管理", groupName = "租户管理")
public class TenantServiceImpl extends SkyeyeBusinessServiceImpl<TenantDao, Tenant> implements TenantService {

    @Override
    public void createPrepose(Tenant entity) {
        entity.setAccountNum(CommonNumConstants.NUM_ZERO);
    }
}
