/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.courseware.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.school.courseware.classenum.CoursewareStudyState;
import com.skyeye.school.courseware.dao.CoursewareStudyDao;
import com.skyeye.school.courseware.entity.CoursewareStudy;
import com.skyeye.school.courseware.service.CoursewareStudyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: CoursewareStudyServiceImpl
 * @Description: 互动课件学习信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/2 10:01
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "互动课件学习信息", groupName = "互动课件")
public class CoursewareStudyServiceImpl extends SkyeyeBusinessServiceImpl<CoursewareStudyDao, CoursewareStudy> implements CoursewareStudyService {

    @Override
    public void studyCoursewareByCoursewareId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String coursewareId = map.get("coursewareId").toString();
        String userId = inputObject.getLogParams().get("id").toString();
        // 判断当前登录人是否学习过这个课件
        QueryWrapper<CoursewareStudy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(CoursewareStudy::getCoursewareId), coursewareId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(CoursewareStudy::getCreateId), userId);
        long count = count(queryWrapper);
        if (count == 0) {
            CoursewareStudy coursewareStudy = new CoursewareStudy();
            coursewareStudy.setCoursewareId(coursewareId);
            coursewareStudy.setState(CoursewareStudyState.COMPLATE.getKey());
            createEntity(coursewareStudy, userId);
        }
    }

    @Override
    public Map<String, String> queryStudyState(List<String> coursewareIdList, String userId) {
        if (CollectionUtil.isEmpty(coursewareIdList)) {
            return MapUtil.newHashMap();
        }
        coursewareIdList = coursewareIdList.stream().distinct().collect(Collectors.toList());
        QueryWrapper<CoursewareStudy> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(CoursewareStudy::getCoursewareId), coursewareIdList);
        queryWrapper.eq(MybatisPlusUtil.toColumns(CoursewareStudy::getCreateId), userId);
        List<CoursewareStudy> coursewareStudyList = list(queryWrapper);
        return coursewareStudyList.stream().collect(Collectors.toMap(CoursewareStudy::getCoursewareId, CoursewareStudy::getState));
    }
}