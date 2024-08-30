/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.rest.staff.service.impl;

import com.skyeye.base.rest.service.impl.IServiceImpl;
import com.skyeye.common.client.ExecuteFeignClient;
import com.skyeye.rest.staff.rest.ISysUserStaffRest;
import com.skyeye.rest.staff.service.ISysUserStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ISysUserStaffServiceImpl
 * @Description: 员工信息管理公共的一些操作
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/15 10:32
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Service
public class ISysUserStaffServiceImpl extends IServiceImpl implements ISysUserStaffService {

    @Autowired
    private ISysUserStaffRest iSysUserStaffRest;

    @Override
    public void editSysUserStaffActMoneyById(String staffId, String actMoney) {
        Map<String, Object> map = new HashMap<>();
        map.put("staffId", staffId);
        map.put("actMoney", actMoney);
        ExecuteFeignClient.get(() -> iSysUserStaffRest.editSysUserStaffActMoneyById(map));
    }
}
