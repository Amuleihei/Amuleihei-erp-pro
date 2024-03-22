/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.authority.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AuthorityDao
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/22 14:21
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface AuthorityDao {

    List<Map<String, Object>> getRoleHasMenuListByRoleId(@Param("roleId") String roleId);

    List<Map<String, Object>> getRoleHasAppMenuByRoleId(@Param("roleId") String roleId);

    List<Map<String, Object>> getRoleHasAuthPointsByRoleId(@Param("roleId") String roleId);

    List<Map<String, Object>> getRoleHasAppAuthPointsByRoleId(@Param("roleId") String roleId);

}
