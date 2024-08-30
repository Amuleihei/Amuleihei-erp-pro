/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.teaching.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.school.grade.service.ClassesService;
import com.skyeye.school.teaching.dao.TeachingDao;
import com.skyeye.school.teaching.entity.Teaching;
import com.skyeye.school.teaching.service.TeachingService;
import com.skyeye.school.yearsub.service.YearSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TeachingServiceImpl
 * @Description: 授课服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/29 17:42
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "授课管理", groupName = "授课管理")
public class TeachingServiceImpl extends SkyeyeBusinessServiceImpl<TeachingDao, Teaching> implements TeachingService {

    @Autowired
    private YearSubjectService yearSubjectService;

    @Autowired
    private TeachingService teachingService;

    @Autowired
    private ClassesService classesService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryTeachingList(commonPageInfo);
        yearSubjectService.setMationForMap(beans, "yearSubjectServiceId", "yearSubjectServiceMation");
        teachingService.setMationForMap(beans,"teachingId","teachingMation");
        classesService.setMationForMap(beans, "classId", "classMation");
        return beans;
    }

    @Override
    public Teaching selectById(String id) {
        Teaching teaching = super.selectById(id);
        yearSubjectService.setDataMation(teaching, Teaching::getYearSubjectId);
        teachingService.setDataMation(teaching, Teaching::getTeacherId);
        classesService.setDataMation(teaching, Teaching::getClassId);
        return teaching;
    }

    @Override
    public List<Teaching> selectByIds(String...ids){
        List<Teaching> teachingList = super.selectByIds(ids);
        yearSubjectService.setDataMation(teachingList, Teaching::getYearSubjectId);
        classesService.setDataMation(teachingList, Teaching::getClassId);
        return teachingList;
    }
}