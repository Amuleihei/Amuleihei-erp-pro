/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.courseware.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.SchoolConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.object.PutObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.classenum.LoginIdentity;
import com.skyeye.school.chapter.service.ChapterService;
import com.skyeye.school.courseware.dao.CoursewareDao;
import com.skyeye.school.courseware.entity.Courseware;
import com.skyeye.school.courseware.service.CoursewareService;
import com.skyeye.school.courseware.service.CoursewareStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: CoursewareServiceImpl
 * @Description: 互动课件服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/2 9:29
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "互动课件", groupName = "互动课件")
public class CoursewareServiceImpl extends SkyeyeBusinessServiceImpl<CoursewareDao, Courseware> implements CoursewareService {

    @Autowired
    private CoursewareStudyService coursewareStudyService;

    @Autowired
    private ChapterService chapterService;

    @Override
    public Courseware selectById(String id) {
        Courseware courseware = super.selectById(id);
        chapterService.setDataMation(courseware, Courseware::getChapterId);
        if (ObjectUtil.isNotEmpty(courseware.getChapterMation())) {
            courseware.getChapterMation().setName(String.format(Locale.ROOT, "第 %s 章 %s", courseware.getChapterMation().getSection(), courseware.getChapterMation().getName()));
        }
        return courseware;
    }

    @Override
    public void queryCoursewareListBySubjectClassesId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String subjectClassesId = map.get("subjectClassesId").toString();
        QueryWrapper<Courseware> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(Courseware::getSubjectClassesId), subjectClassesId);
        List<Courseware> coursewareList = list(queryWrapper);

        String userIdentity = PutObject.getRequest().getHeader(SchoolConstants.USER_IDENTITY_KEY);
        if (StrUtil.equals(userIdentity, LoginIdentity.STUDENT.getKey())) {
            // 学生身份信息
            String userId = inputObject.getLogParams().get("id").toString();
            List<String> coursewareIdList = coursewareList.stream().map(Courseware::getId).collect(Collectors.toList());
            Map<String, String> stateMap = coursewareStudyService.queryStudyState(coursewareIdList, userId);
            coursewareList.forEach(courseware -> {
                courseware.setState(stateMap.get(courseware.getId()));
            });
        }

        chapterService.setDataMation(coursewareList, Courseware::getChapterId);
        coursewareList.forEach(courseware -> {
            if (ObjectUtil.isNotEmpty(courseware.getChapterMation())) {
                courseware.getChapterMation().setName(String.format(Locale.ROOT, "第 %s 章 %s", courseware.getChapterMation().getSection(), courseware.getChapterMation().getName()));
            }
        });

        iAuthUserService.setDataMation(coursewareList, Courseware::getCreateId);
        iAuthUserService.setDataMation(coursewareList, Courseware::getLastUpdateId);
        outputObject.setBeans(coursewareList);
        outputObject.settotal(coursewareList.size());
    }
}
