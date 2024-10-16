/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.production.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.production.dao.ProductionPlanChildDao;
import com.skyeye.production.entity.ProductionPlanChild;
import com.skyeye.production.service.ProductionPlanChildService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ProductionPlanChildServiceImpl
 * @Description: 出货计划单子单据服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/21 20:38
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "出货计划单子单据", groupName = "出货计划单子单据", manageShow = false)
public class ProductionPlanChildServiceImpl extends SkyeyeBusinessServiceImpl<ProductionPlanChildDao, ProductionPlanChild> implements ProductionPlanChildService {

    @Override
    public void saveList(String parentId, List<ProductionPlanChild> beans) {
        deleteByParentId(parentId);
        if (CollectionUtil.isNotEmpty(beans)) {
            for (ProductionPlanChild productionPlanChild : beans) {
                productionPlanChild.setParentId(parentId);
            }
            createEntity(beans, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByParentId(String parentId) {
        QueryWrapper<ProductionPlanChild> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ProductionPlanChild::getParentId), parentId);
        remove(queryWrapper);
    }

    @Override
    public List<ProductionPlanChild> selectByParentId(String parentId) {
        QueryWrapper<ProductionPlanChild> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ProductionPlanChild::getParentId), parentId);
        List<ProductionPlanChild> productionPlanChildList = list(queryWrapper);
        return productionPlanChildList;
    }

    @Override
    public List<ProductionPlanChild> selectByParentId(List<String> parentIds) {
        QueryWrapper<ProductionPlanChild> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(ProductionPlanChild::getParentId), parentIds);
        List<ProductionPlanChild> productionPlanChildList = list(queryWrapper);
        return productionPlanChildList;
    }
}
