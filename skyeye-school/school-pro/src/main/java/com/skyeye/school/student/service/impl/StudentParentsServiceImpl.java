/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.student.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.school.student.dao.StudentParentsDao;
import com.skyeye.school.student.entity.StudentParents;
import com.skyeye.school.student.service.StudentParentsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: StudentParentsServiceImpl
 * @Description: 学生父母情况管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/11 20:51
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "学生父母情况", groupName = "学生管理", manageShow = false)
public class StudentParentsServiceImpl extends SkyeyeBusinessServiceImpl<StudentParentsDao, StudentParents> implements StudentParentsService {
    @Override
    public void saveLinkList(String studentId, List<StudentParents> beans) {
        deleteLinkListByStudentId(studentId);
        beans.forEach(bean -> {
            bean.setStudentId(studentId);
        });
        createEntity(beans, StrUtil.EMPTY);
    }

    @Override
    public void deleteLinkListByStudentId(String studentId) {
        QueryWrapper<StudentParents> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(StudentParents::getStudentId), studentId);
        remove(queryWrapper);
    }

    @Override
    public List<StudentParents> queryLinkListByStudentId(String studentId) {
        QueryWrapper<StudentParents> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(StudentParents::getStudentId), studentId);
        return list(queryWrapper);
    }

}
