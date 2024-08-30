/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.department.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.eve.service.SchoolService;
import com.skyeye.school.department.dao.DepartmentDao;
import com.skyeye.school.department.entity.Department;
import com.skyeye.school.department.service.DepartmentService;
import com.skyeye.school.faculty.service.FacultyService;
import com.skyeye.school.major.entity.Major;
import com.skyeye.school.major.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: DepartmentServiceImpl
 * @Description: 教研室信息管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/18 16:57
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "教研室管理", groupName = "教研室管理")
public class DepartmentServiceImpl extends SkyeyeBusinessServiceImpl<DepartmentDao, Department> implements DepartmentService {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private MajorService majorService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        schoolService.setMationForMap(beans, "schoolId", "schoolMation");
        facultyService.setMationForMap(beans, "facultyId", "facultyMation");
        iAuthUserService.setMationForMap(beans, "leader", "leaderMation");
        return beans;
    }

    @Override
    public Department selectById(String id) {
        Department department = super.selectById(id);
        schoolService.setDataMation(department, Department::getSchoolId);
        facultyService.setDataMation(department, Department::getFacultyId);
        if (CollectionUtil.isNotEmpty(department.getGrandMajor())) {
            List<Major> majorList = majorService.selectByIds(department.getGrandMajor().toArray(new String[]{}));
            department.setGrandMajorMation(majorList);
        }
        iAuthUserService.setDataMation(department, Department::getLeader);
        return department;
    }
}
