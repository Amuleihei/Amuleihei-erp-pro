/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.procedure.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.procedure.dao.WorkProcedureEquipmentDao;
import com.skyeye.procedure.entity.WorkProcedureEquipment;
import com.skyeye.procedure.service.WorkProcedureEquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: WorkProcedureEquipmentServiceImpl
 * @Description: 工序下的设备清单管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/18 8:46
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "工序下的设备清单管理", groupName = "工序管理", manageShow = false)
public class WorkProcedureEquipmentServiceImpl extends SkyeyeBusinessServiceImpl<WorkProcedureEquipmentDao, WorkProcedureEquipment> implements WorkProcedureEquipmentService {

    @Override
    public void saveList(String parentId, List<WorkProcedureEquipment> beans) {
        deleteByParentId(parentId);
        if (CollectionUtil.isNotEmpty(beans)) {
            for (WorkProcedureEquipment procedureEquipment : beans) {
                procedureEquipment.setWorkProcedureId(parentId);
            }
            createEntity(beans, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByParentId(String parentId) {
        QueryWrapper<WorkProcedureEquipment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(WorkProcedureEquipment::getWorkProcedureId), parentId);
        remove(queryWrapper);
    }

    @Override
    public List<WorkProcedureEquipment> selectByParentId(String parentId) {
        QueryWrapper<WorkProcedureEquipment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(WorkProcedureEquipment::getWorkProcedureId), parentId);
        List<WorkProcedureEquipment> list = list(queryWrapper);
        return list;
    }

    @Override
    public Map<String, List<WorkProcedureEquipment>> selectByParentId(List<String> parentIds) {
        if (CollectionUtil.isEmpty(parentIds)) {
            return MapUtil.newHashMap();
        }
        QueryWrapper<WorkProcedureEquipment> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(WorkProcedureEquipment::getWorkProcedureId), parentIds);
        List<WorkProcedureEquipment> list = list(queryWrapper);
        return list.stream().collect(Collectors.groupingBy(WorkProcedureEquipment::getWorkProcedureId));
    }
}
