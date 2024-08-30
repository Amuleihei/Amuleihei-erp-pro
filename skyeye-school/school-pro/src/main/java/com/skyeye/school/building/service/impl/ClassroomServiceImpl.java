/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.building.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.school.building.dao.ClassroomDao;
import com.skyeye.school.building.entity.Classroom;
import com.skyeye.school.building.service.ClassroomService;
import com.skyeye.school.building.service.TeachBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ClassroomServiceImpl
 * @Description: 教室管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/9/5 17:15
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "教室管理", groupName = "教学楼管理")
public class ClassroomServiceImpl extends SkyeyeBusinessServiceImpl<ClassroomDao, Classroom> implements ClassroomService {

    @Autowired
    private TeachBuildingService teachBuildingService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        teachBuildingService.setMationForMap(beans, "teachBuildingId", "teachBuildingMation");
        return beans;
    }

    @Override
    public Classroom selectById(String id) {
        Classroom classroom = super.selectById(id);
        teachBuildingService.setDataMation(classroom, Classroom::getTeachBuildingId);
        return classroom;
    }

    @Override
    public List<Classroom> selectByIds(String...ids){
        List<Classroom> classroomList = super.selectByIds(ids);
        teachBuildingService.setDataMation(classroomList,Classroom::getTeachBuildingId);
        return classroomList;
    }
}