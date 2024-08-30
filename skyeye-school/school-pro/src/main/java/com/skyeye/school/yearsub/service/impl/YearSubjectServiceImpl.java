/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.yearsub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.school.subject.entity.Subject;
import com.skyeye.school.subject.service.SubjectService;
import com.skyeye.school.yearsub.dao.YearSubjectDao;
import com.skyeye.school.yearsub.entity.YearSubject;
import com.skyeye.school.yearsub.entity.YearSubjectBox;
import com.skyeye.school.yearsub.service.YearSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: YearSubjectServiceImpl
 * @Description: 学年科目服务管理层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/29 16:31
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "学年科目管理", groupName = "学年科目管理")
public class YearSubjectServiceImpl extends SkyeyeBusinessServiceImpl<YearSubjectDao, YearSubject> implements YearSubjectService {

    @Autowired
    private YearSubjectService yearSubjectService;

    @Autowired
    private SubjectService subjectService;

    @Override
    public Map<String, List<Subject>> querySubjectList(String majorId, List<String> semesterList) {
        // 1. 根据专业id和学年信息查询
        QueryWrapper<YearSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(YearSubject::getMajorId), majorId);
        queryWrapper.in(MybatisPlusUtil.toColumns(YearSubject::getSemester), semesterList);
        List<YearSubject> yearSubjects = list(queryWrapper);
        subjectService.setDataMation(yearSubjects, YearSubject::getSubjectId);
        // 2. 根据学年进行分组并进行学科字段的筛选
        Map<String, List<Subject>> subjectMap = yearSubjects.stream()
                .collect(Collectors.groupingBy(YearSubject::getSemester, Collectors.mapping(YearSubject::getSubjectMation, Collectors.toList())));
        return subjectMap;
    }

    public Map<String, List<YearSubject>> queryYearSubjectList(String majorId, Integer year) {
        // 1. 根据专业id和所属年份信息查询
        QueryWrapper<YearSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(YearSubject::getMajorId), majorId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(YearSubject::getYear), year);
        List<YearSubject> yearSubjects = list(queryWrapper);
        yearSubjectService.setDataMation(yearSubjects, YearSubject::getSubjectId);
        // 2. 根据学年进行分组
        Map<String, List<YearSubject>> yearSubjectMap = yearSubjects.stream()
                .collect(Collectors.groupingBy(YearSubject::getSemester));
        return yearSubjectMap;
    }

    private void deleteListByYearAndMajorId(String majorId, Integer year) {
        QueryWrapper<YearSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(YearSubject::getMajorId), majorId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(YearSubject::getYear), year);
        remove(queryWrapper);
    }

    @Override
    public void writeYearSubject(InputObject inputObject, OutputObject outputObject) {
        YearSubjectBox yearSubjectBox = inputObject.getParams(YearSubjectBox.class);
        deleteListByYearAndMajorId(yearSubjectBox.getMajorId(), yearSubjectBox.getYear());
        yearSubjectBox.getYearSubject().forEach(yearSubject -> {
            yearSubject.setYear(yearSubjectBox.getYear());
            yearSubject.setMajorId(yearSubjectBox.getMajorId());
        });
        String userId = inputObject.getLogParams().get("id").toString();
        createEntity(yearSubjectBox.getYearSubject(), userId);
    }
}