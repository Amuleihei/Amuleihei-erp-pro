/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pick.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.pick.dao.PickChildDao;
import com.skyeye.pick.entity.PickChild;
import com.skyeye.pick.service.PickChildService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: PickChildServiceImpl
 * @Description: 物料单子单据服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/3/30 9:01
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "物料单子单据", groupName = "物料单子单据", manageShow = false)
public class PickChildServiceImpl extends SkyeyeBusinessServiceImpl<PickChildDao, PickChild> implements PickChildService {

    @Override
    public void deleteByParentId(String pickId) {
        QueryWrapper<PickChild> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(PickChild::getParentId), pickId);
        remove(queryWrapper);
    }

    @Override
    public List<PickChild> selectByParentId(String pickId) {
        QueryWrapper<PickChild> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(PickChild::getParentId), pickId);
        List<PickChild> pickChildList = list(queryWrapper);
        return pickChildList;
    }

    @Override
    public void saveList(String pickId, List<PickChild> pickChildList, String userId) {
        deleteByParentId(pickId);
        if (CollectionUtil.isNotEmpty(pickChildList)) {
            for (PickChild pickChild : pickChildList) {
                pickChild.setParentId(pickId);
            }
            createEntity(pickChildList, userId);
        }
    }

    @Override
    public List<PickChild> queryPickChildListByParentIds(List<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return CollectionUtil.newArrayList();
        }
        QueryWrapper<PickChild> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(PickChild::getParentId), ids);
        return list(queryWrapper);
    }
}
