/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.exception.CustomException;
import com.skyeye.personnel.classenum.UserStaffType;
import com.skyeye.personnel.dao.SysEveUserStaffTeacherDao;
import com.skyeye.personnel.entity.SysEveUserStaff;
import com.skyeye.personnel.entity.SysEveUserStaffTeacher;
import com.skyeye.personnel.service.SysEveUserStaffService;
import com.skyeye.personnel.service.SysEveUserStaffTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: SysEveUserStaffTeacherServiceImpl
 * @Description: 员工所属学校关系服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/11/23 21:39
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Service
@SkyeyeService(name = "员工所属学校关系管理", groupName = "员工管理", manageShow = false)
public class SysEveUserStaffTeacherServiceImpl extends SkyeyeBusinessServiceImpl<SysEveUserStaffTeacherDao, SysEveUserStaffTeacher> implements SysEveUserStaffTeacherService {

    @Autowired
    private SysEveUserStaffService sysEveUserStaffService;

    @Override
    public void createPrepose(SysEveUserStaffTeacher entity) {
        SysEveUserStaff sysEveUserStaff = sysEveUserStaffService.selectById(entity.getStaffId());
        if (ObjectUtil.isNotEmpty(sysEveUserStaff)) {
            // 如果是普通员工，则允许转教职工
            if (sysEveUserStaff.getType() == UserStaffType.SIMPLE_STAFF.getKey()) {
                // 修改员工类型
                sysEveUserStaffService.updateStaffType(entity.getStaffId(), UserStaffType.TEACHER.getKey());
            } else {
                throw new CustomException("该员工无法转教职工。");
            }
        } else {
            throw new CustomException("The data does not exist");
        }
    }

}
