/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tenant.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.tenant.service.TenantAppLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TenantAppLinkController
 * @Description: 租户与应用的关系管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/29 20:45
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "租户与应用的关系管理", tags = "租户与应用的关系管理", modelName = "租户管理")
public class TenantAppLinkController {

    @Autowired
    private TenantAppLinkService tenantAppLinkService;

}
