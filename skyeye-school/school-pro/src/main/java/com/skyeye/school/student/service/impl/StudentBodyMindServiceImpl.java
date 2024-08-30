/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.student.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.school.student.dao.StudentBodyMindDao;
import com.skyeye.school.student.entity.StudentBodyMind;
import com.skyeye.school.student.service.StudentBodyMindService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: StudentBodyMindServiceImpl
 * @Description: 学生身心障碍管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/9 9:52
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "学生身心障碍", groupName = "学生管理", manageShow = false)
public class StudentBodyMindServiceImpl extends SkyeyeBusinessServiceImpl<StudentBodyMindDao, StudentBodyMind> implements StudentBodyMindService {

    /**
     * 增改学生身心健康列表
     */
    @Override
    public void saveLinkList(String studentId, List<StudentBodyMind> beans) {
        deleteLinkListByStudentId(studentId);
        beans.forEach(bean -> {
            bean.setStudentId(studentId);
        });
        createEntity(beans, StrUtil.EMPTY);
    }

    /**
     * 删除学生身心健康列表
     */
    @Override
    public void deleteLinkListByStudentId(String studentId) {
        QueryWrapper<StudentBodyMind> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(StudentBodyMind::getStudentId), studentId);
        remove(queryWrapper);
    }

    /**
     * 获取学生身心健康列表
     */
    @Override
    public List<StudentBodyMind> queryLinkListByStudentId(String studentId) {
        QueryWrapper<StudentBodyMind> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(StudentBodyMind::getStudentId), studentId);
        return list(queryWrapper);
    }
}
