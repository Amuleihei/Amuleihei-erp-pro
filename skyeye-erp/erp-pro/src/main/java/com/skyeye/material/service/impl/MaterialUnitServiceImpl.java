/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.enumeration.DeleteFlagEnum;
import com.skyeye.common.enumeration.WhetherEnum;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.material.dao.MaterialUnitDao;
import com.skyeye.material.entity.unit.MaterialUnit;
import com.skyeye.material.service.MaterialUnitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: MaterialUnitServiceImpl
 * @Description: 计量单位信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/10/30 11:34
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "计量单位", groupName = "计量单位", manageShow = false)
public class MaterialUnitServiceImpl extends SkyeyeBusinessServiceImpl<MaterialUnitDao, MaterialUnit> implements MaterialUnitService {

    /**
     * 批量保存计量单位信息
     *
     * @param unitList 计量单位信息
     * @param userId   用户id
     * @param groupId  所属组id
     */
    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void saveBatchList(List<MaterialUnit> unitList, String userId, String groupId) {
        List<MaterialUnit> collect = unitList.stream()
            .filter(bean -> bean.getBaseUnit() == WhetherEnum.ENABLE_USING.getKey()).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(collect)) {
            throw new CustomException("至少需要一个基础单位");
        }
        if (collect.size() > 1) {
            throw new CustomException("分组中只允许一个基础单位");
        }
        // 获取数据库中的数据
        QueryWrapper<MaterialUnit> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(MaterialUnit::getGroupId), groupId);
        wrapper.eq(MybatisPlusUtil.toColumns(MaterialUnit::getDeleteFlag), DeleteFlagEnum.NOT_DELETE.getKey());
        List<MaterialUnit> oldList = super.list(wrapper);
        Map<String, String> nameToId = oldList.stream().collect(Collectors.toMap(MaterialUnit::getName, MaterialUnit::getId));
        List<String> oldKeys = oldList.stream().map(bean -> bean.getName()).collect(Collectors.toList());
        List<String> newKeys = unitList.stream().map(bean -> bean.getName()).collect(Collectors.toList());

        // (旧数据 - 新数据) 从数据库删除
        List<MaterialUnit> deleteBeans = oldList.stream().filter(item -> !newKeys.contains(item.getName())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(deleteBeans)) {
            List<String> ids = deleteBeans.stream().map(bean -> bean.getId()).collect(Collectors.toList());
            deleteById(ids);
        }

        // (新数据 - 旧数据) 添加到数据库
        List<MaterialUnit> addBeans = unitList.stream().filter(item -> !oldKeys.contains(item.getName())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(addBeans)) {
            addBeans.forEach(bean -> {
                bean.setGroupId(groupId);
            });
            createEntity(addBeans, userId);
        }

        // (新数据 包含 旧数据) 修改数据库
        List<MaterialUnit> editBeans = unitList.stream().filter(item -> oldKeys.contains(item.getName())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(editBeans)) {
            editBeans.forEach(bean -> {
                bean.setId(nameToId.get(bean.getName()));
                bean.setGroupId(groupId);
            });
            updateEntity(editBeans, userId);
        }
    }

    /**
     * 查询组下的所有计量单位信息
     *
     * @param groupId 所属组id
     * @return 计量单位信息
     */
    @Override
    public List<MaterialUnit> queryUnitListByGroupId(String groupId) {
        QueryWrapper<MaterialUnit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(MaterialUnit::getGroupId), groupId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(MaterialUnit::getDeleteFlag), DeleteFlagEnum.NOT_DELETE.getKey());
        queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(MaterialUnit::getNumber));
        return list(queryWrapper);
    }


    /**
     * 根据所属组id删除组下面的计量单位信息
     *
     * @param groupId 所属组id
     */
    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void deleteByGroupId(String groupId) {
        List<MaterialUnit> unitList = queryUnitListByGroupId(groupId);
        List<String> ids = unitList.stream().map(MaterialUnit::getId).collect(Collectors.toList());
        deleteById(ids);
    }

    /**
     * 根据组id集合批量查询计量单位信息
     *
     * @param groupIds 组id集合
     * @return
     */
    @Override
    public Map<String, List<MaterialUnit>> queryUnitListByGroupId(List<String> groupIds) {
        QueryWrapper<MaterialUnit> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(MaterialUnit::getGroupId), groupIds);
        queryWrapper.eq(MybatisPlusUtil.toColumns(MaterialUnit::getDeleteFlag), DeleteFlagEnum.NOT_DELETE.getKey());
        List<MaterialUnit> unitMationList = super.list(queryWrapper);
        Map<String, List<MaterialUnit>> collect = unitMationList.stream()
            .sorted(Comparator.comparing(bean -> bean.getNumber())).collect(Collectors.groupingBy(MaterialUnit::getGroupId));
        return collect;
    }

}
