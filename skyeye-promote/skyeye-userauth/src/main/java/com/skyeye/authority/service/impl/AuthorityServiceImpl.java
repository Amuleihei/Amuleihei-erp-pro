/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.authority.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.authority.dao.AuthorityDao;
import com.skyeye.authority.service.AuthorityService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AuthorityServiceImpl
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/22 14:21
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "权限管理", groupName = "权限管理", manageShow = false)
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public void getRoleHasMenuListByRoleId(InputObject inputObject, OutputObject outputObject) {
        String roleId = inputObject.getParams().get("roleId").toString();
        List<Map<String, Object>> beans = authorityDao.getRoleHasMenuListByRoleId(roleId);
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    @Override
    public void getRoleHasAppMenuByRoleId(InputObject inputObject, OutputObject outputObject) {
        String roleId = inputObject.getParams().get("roleId").toString();
        List<Map<String, Object>> beans = authorityDao.getRoleHasAppMenuByRoleId(roleId);
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    @Override
    public void getRoleHasAuthPointsByRoleId(InputObject inputObject, OutputObject outputObject) {
        String roleId = inputObject.getParams().get("roleId").toString();
        List<Map<String, Object>> beans = authorityDao.getRoleHasAuthPointsByRoleId(roleId);
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    @Override
    public void getRoleHasAppAuthPointsByRoleId(InputObject inputObject, OutputObject outputObject) {
        String roleId = inputObject.getParams().get("roleId").toString();
        List<Map<String, Object>> beans = authorityDao.getRoleHasAppAuthPointsByRoleId(roleId);
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }
}
