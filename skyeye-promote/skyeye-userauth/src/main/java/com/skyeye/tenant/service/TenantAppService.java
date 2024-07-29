/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tenant.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.tenant.entity.TenantApp;

/**
 * @ClassName: TenantAppService
 * @Description: 租户应用管理服务层接口
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/29 16:38
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface TenantAppService extends SkyeyeBusinessService<TenantApp> {

    void queryTenantAppBandMenuList(InputObject inputObject, OutputObject outputObject);

    void editTenantAppPCAuth(InputObject inputObject, OutputObject outputObject);

    void queryTenantAppBandAppMenuList(InputObject inputObject, OutputObject outputObject);

    void editTenantAppAppMenuById(InputObject inputObject, OutputObject outputObject);

    void queryAllTenantAppList(InputObject inputObject, OutputObject outputObject);
}
