/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.inventory.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.inventory.dao.InventoryChildCodeDao;
import com.skyeye.inventory.entity.InventoryChildCode;
import com.skyeye.inventory.service.InventoryChildCodeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: InventoryChildCodeServiceImpl
 * @Description: 盘点任务表-子单据关联的编码服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/18 17:19
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "盘点任务表-子单据关联的编码", groupName = "盘点任务单")
public class InventoryChildCodeServiceImpl extends SkyeyeBusinessServiceImpl<InventoryChildCodeDao, InventoryChildCode> implements InventoryChildCodeService {

    @Override
    public QueryWrapper<InventoryChildCode> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<InventoryChildCode> queryWrapper = super.getQueryWrapper(commonPageInfo);
        if (StrUtil.isNotBlank(commonPageInfo.getObjectId())) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(InventoryChildCode::getParentId), commonPageInfo.getObjectId());
        }
        return queryWrapper;
    }

    @Override
    public void saveList(String orderId, List<InventoryChildCode> beans) {
        deleteByOrderId(orderId);
        if (CollectionUtil.isNotEmpty(beans)) {
            for (InventoryChildCode inventoryChildCode : beans) {
                inventoryChildCode.setOrderId(orderId);
            }
            createEntity(beans, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByOrderId(String orderId) {
        QueryWrapper<InventoryChildCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(InventoryChildCode::getOrderId), orderId);
        remove(queryWrapper);
    }

    @Override
    public List<InventoryChildCode> selectByOrderId(String... orderId) {
        List<String> orderIdList = Arrays.asList(orderId);
        if (CollectionUtil.isEmpty(orderIdList)) {
            return CollectionUtil.newArrayList();
        }
        QueryWrapper<InventoryChildCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(InventoryChildCode::getOrderId), orderIdList);
        List<InventoryChildCode> list = list(queryWrapper);
        return list;
    }

    @Override
    public List<InventoryChildCode> selectByParentId(String... parentId) {
        List<String> parentIds = Arrays.asList(parentId);
        if (CollectionUtil.isEmpty(parentIds)) {
            return CollectionUtil.newArrayList();
        }
        QueryWrapper<InventoryChildCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(InventoryChildCode::getParentId), parentIds);
        List<InventoryChildCode> list = list(queryWrapper);
        return list;
    }
}
