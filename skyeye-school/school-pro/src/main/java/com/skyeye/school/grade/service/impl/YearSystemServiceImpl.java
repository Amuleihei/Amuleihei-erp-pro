/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.grade.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.school.grade.dao.YearSystemDao;
import com.skyeye.school.grade.entity.YearSystem;
import com.skyeye.school.grade.service.YearSystemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: YearSystemServiceImpl
 * @Description: 年制服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/18 17:48
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "年制", groupName = "班级管理", manageShow = false)
public class YearSystemServiceImpl extends SkyeyeBusinessServiceImpl<YearSystemDao, YearSystem> implements YearSystemService {

    @Override
    public void deleteLinkListByClassId(String classId) {
        QueryWrapper<YearSystem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(YearSystem::getClassId), classId);
        remove(queryWrapper);
    }

    @Override
    public List<YearSystem> queryLinkListByClassId(String classId) {
        QueryWrapper<YearSystem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(YearSystem::getClassId), classId);
        queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(YearSystem::getOrderBy));
        return list(queryWrapper);
    }

    @Override
    public Map<String, List<YearSystem>> queryLinkListByClassId(List<String> classIdList) {
        if (CollectionUtil.isEmpty(classIdList)) {
            return MapUtil.newHashMap();
        }
        classIdList = classIdList.stream().distinct().collect(Collectors.toList());
        QueryWrapper<YearSystem> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(YearSystem::getClassId), classIdList);
        queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(YearSystem::getOrderBy));
        List<YearSystem> yearSystemList = list(queryWrapper);
        return yearSystemList.stream().collect(Collectors.groupingBy(YearSystem::getClassId));
    }

}

