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
import com.skyeye.usercase.dao.AutoStepCaseDao;
import com.skyeye.usercase.entity.AutoStepCase;
import com.skyeye.usercase.service.AutoStepCaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName: AutoStepCaseServiceImpl
 * @Description: 用例步骤关联的用例管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:20
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
@Service
@SkyeyeService(name = "用例步骤关联的用例管理", groupName = "用例步骤关联的用例管理", manageShow = false)
public class AutoStepCaseServiceImpl extends SkyeyeBusinessServiceImpl<AutoStepCaseDao, AutoStepCase> implements AutoStepCaseService {

    @Override
    public void saveList(String objectId, List<AutoStepCase> autoStepCaseList) {
        deleteByObjectId(objectId);
        if (CollectionUtil.isNotEmpty(autoStepCaseList)) {
            for (AutoStepCase cases : autoStepCaseList) {
                cases.setCaseId(objectId);
            }
            createEntity(autoStepCaseList, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByObjectId(String objectId) {
        QueryWrapper<AutoStepCase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AutoStepCase::getCaseId), objectId);
        remove(queryWrapper);
    }

    @Override
    public Map<String, AutoStepCase> selectByObjectId(String objectId) {
        QueryWrapper<AutoStepCase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AutoStepCase::getCaseId), objectId);
        List<AutoStepCase> list = list(queryWrapper);
        return list.stream().collect(Collectors.toMap(AutoStepCase::getStepId, Function.identity(), (v1, v2) -> v1));
    }
}
