/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.rest.staff.service;

import com.skyeye.base.rest.service.IService;

/**
 * @ClassName: ISysUserStaffService
 * @Description: 员工信息管理公共的一些操作
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/15 10:31
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public interface ISysUserStaffService extends IService {

    /**
     * 修改员工薪资设定信息
     *
     * @param staffId  员工id
     * @param actMoney 员工实际薪资
     */
    void editSysUserStaffActMoneyById(String staffId, String actMoney);

}
