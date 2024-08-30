/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.usercase.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.usercase.dao.AutoStepAssertDao;
import com.skyeye.usercase.entity.AutoStepAssert;
import com.skyeye.usercase.service.AutoStepAssertService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: AutoStepAssertServiceImpl
 * @Description: 用例步骤关联的断言管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:20
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
@Service
@SkyeyeService(name = "用例步骤关联的断言管理", groupName = "用例步骤关联的断言管理", manageShow = false)
public class AutoStepAssertServiceImpl extends SkyeyeBusinessServiceImpl<AutoStepAssertDao, AutoStepAssert> implements AutoStepAssertService {

    @Override
    public void saveList(String objectId, List<AutoStepAssert> autoStepAssertList) {
        deleteByObjectId(objectId);
        if (CollectionUtil.isNotEmpty(autoStepAssertList)) {
            for (AutoStepAssert asserts : autoStepAssertList) {
                asserts.setCaseId(objectId);
            }
            createEntity(autoStepAssertList, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByObjectId(String objectId) {
        QueryWrapper<AutoStepAssert> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AutoStepAssert::getCaseId), objectId);
        remove(queryWrapper);
    }

    @Override
    public Map<String, List<AutoStepAssert>> selectByObjectId(String objectId) {
        QueryWrapper<AutoStepAssert> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AutoStepAssert::getCaseId), objectId);
        queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(AutoStepAssert::getOrderBy));
        List<AutoStepAssert> list = list(queryWrapper);
        return list.stream().collect(Collectors.groupingBy(AutoStepAssert::getStepId));
    }

}
