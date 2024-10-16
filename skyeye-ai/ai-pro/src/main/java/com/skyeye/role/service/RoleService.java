/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.role.service;

import com.skyeye.role.entity.Role;
import com.skyeye.base.business.service.SkyeyeBusinessService;

import java.util.List;

/**
 * @ClassName: ShopDeliveryCompanyController
 * @Description: ai角色服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/10/8 10:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface RoleService extends SkyeyeBusinessService<Role> {
    List<Role> queryRoleList(String id);
}
