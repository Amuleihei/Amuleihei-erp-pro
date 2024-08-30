/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.machinprocedure.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.machinprocedure.dao.MachinProcedureAcceptChildCodeDao;
import com.skyeye.machinprocedure.entity.MachinProcedureAcceptChildCode;
import com.skyeye.machinprocedure.service.MachinProcedureAcceptChildCodeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: MachinProcedureAcceptChildCodeServiceImpl
 * @Description: 加工单子单据工序验收耗材表关联的条形码编号服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/30 14:43
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class MachinProcedureAcceptChildCodeServiceImpl extends SkyeyeBusinessServiceImpl<MachinProcedureAcceptChildCodeDao, MachinProcedureAcceptChildCode> implements MachinProcedureAcceptChildCodeService {

    @Override
    public void saveList(String orderId, List<MachinProcedureAcceptChildCode> beans) {
        deleteByOrderId(orderId);
        if (CollectionUtil.isNotEmpty(beans)) {
            for (MachinProcedureAcceptChildCode machinProcedureAcceptChildCode : beans) {
                machinProcedureAcceptChildCode.setOrderId(orderId);
            }
            createEntity(beans, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByOrderId(String orderId) {
        QueryWrapper<MachinProcedureAcceptChildCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(MachinProcedureAcceptChildCode::getOrderId), orderId);
        remove(queryWrapper);
    }

    @Override
    public List<MachinProcedureAcceptChildCode> selectByOrderId(String... orderId) {
        List<String> orderIdList = Arrays.asList(orderId);
        if (CollectionUtil.isEmpty(orderIdList)) {
            return CollectionUtil.newArrayList();
        }
        QueryWrapper<MachinProcedureAcceptChildCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(MachinProcedureAcceptChildCode::getOrderId), orderIdList);
        List<MachinProcedureAcceptChildCode> list = list(queryWrapper);
        return list;
    }

    @Override
    public Map<String, List<String>> selectMapByOrderId(String... orderId) {
        List<MachinProcedureAcceptChildCode> machinProcedureAcceptChildCodeList = selectByOrderId(orderId);
        Map<String, List<String>> listMap = machinProcedureAcceptChildCodeList.stream().
            collect(Collectors.groupingBy(bean -> String.format(Locale.ROOT, "%s_%s", bean.getParentId(), bean.getNormsId()),
                Collectors.mapping(MachinProcedureAcceptChildCode::getNormsCode, Collectors.toList())));
        return listMap;
    }
}
