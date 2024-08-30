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
import com.skyeye.usercase.dao.AutoStepDatabaseValueDao;
import com.skyeye.usercase.entity.AutoStepDatabaseValue;
import com.skyeye.usercase.service.AutoStepDatabaseValueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: AutoStepDatabaseValueServiceImpl
 * @Description: 用例步骤关联的数据库取值管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:20
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
@Service
@SkyeyeService(name = "用例步骤关联的数据库取值管理", groupName = "用例步骤关联的数据库取值管理", manageShow = false)
public class AutoStepDatabaseValueServiceImpl extends SkyeyeBusinessServiceImpl<AutoStepDatabaseValueDao, AutoStepDatabaseValue> implements AutoStepDatabaseValueService {

    @Override
    public void saveList(String objectId, List<AutoStepDatabaseValue> beans) {
        deleteByObjectId(objectId);
        if (CollectionUtil.isNotEmpty(beans)) {
            for (AutoStepDatabaseValue databaseValues : beans) {
                databaseValues.setCaseId(objectId);
            }
            createEntity(beans, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByObjectId(String objectId) {
        QueryWrapper<AutoStepDatabaseValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AutoStepDatabaseValue::getCaseId), objectId);
        remove(queryWrapper);
    }

    @Override
    public Map<String, List<AutoStepDatabaseValue>> selectByObjectId(String objectId) {
        QueryWrapper<AutoStepDatabaseValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AutoStepDatabaseValue::getCaseId), objectId);
        List<AutoStepDatabaseValue> list = list(queryWrapper);
        return list.stream().collect(Collectors.groupingBy(AutoStepDatabaseValue::getStepDatabaseId));
    }
}
