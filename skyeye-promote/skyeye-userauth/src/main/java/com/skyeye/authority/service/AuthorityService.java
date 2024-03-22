/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.authority.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

/**
 * @ClassName: AuthorityService
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/22 14:20
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface AuthorityService {

    void getRoleHasMenuListByRoleId(InputObject inputObject, OutputObject outputObject);

    void getRoleHasAppMenuByRoleId(InputObject inputObject, OutputObject outputObject);

    void getRoleHasAuthPointsByRoleId(InputObject inputObject, OutputObject outputObject);

    void getRoleHasAppAuthPointsByRoleId(InputObject inputObject, OutputObject outputObject);

}
