/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.inspection.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.inspection.dao.QualityInspectionItemDao;
import com.skyeye.inspection.entity.QualityInspectionItem;
import com.skyeye.inspection.service.QualityInspectionItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: QualityInspectionItemServiceImpl
 * @Description: 质检单子单据服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/22 9:20
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "质检单子单据", groupName = "质检单", manageShow = false)
public class QualityInspectionItemServiceImpl extends SkyeyeBusinessServiceImpl<QualityInspectionItemDao, QualityInspectionItem> implements QualityInspectionItemService {

    @Override
    public void saveList(String parentId, List<QualityInspectionItem> beans) {
        deleteByParentId(parentId);
        if (CollectionUtil.isNotEmpty(beans)) {
            for (QualityInspectionItem qualityInspectionItem : beans) {
                qualityInspectionItem.setParentId(parentId);
            }
            createEntity(beans, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByParentId(String parentId) {
        QueryWrapper<QualityInspectionItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(QualityInspectionItem::getParentId), parentId);
        remove(queryWrapper);
    }

    @Override
    public List<QualityInspectionItem> selectByParentId(String parentId) {
        QueryWrapper<QualityInspectionItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(QualityInspectionItem::getParentId), parentId);
        List<QualityInspectionItem> list = list(queryWrapper);
        return list;
    }

    @Override
    public List<QualityInspectionItem> selectByParentId(List<String> parentId) {
        QueryWrapper<QualityInspectionItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(QualityInspectionItem::getParentId), parentId);
        List<QualityInspectionItem> list = list(queryWrapper);
        return list;
    }
}
