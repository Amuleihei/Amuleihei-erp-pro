/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tms.path.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.tms.address.service.BillingAddressService;
import com.skyeye.tms.path.dao.TransportationPathDao;
import com.skyeye.tms.path.entity.TransportationPath;
import com.skyeye.tms.path.service.TransportationPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TransportationPathServiceImpl
 * @Description: 运输路径服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/11 22:14
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "运输路径", groupName = "运输路径")
public class TransportationPathServiceImpl extends SkyeyeBusinessServiceImpl<TransportationPathDao, TransportationPath> implements TransportationPathService {

    @Autowired
    private BillingAddressService billingAddressService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        billingAddressService.setMationForMap(beans, "departureId", "departureMation");
        billingAddressService.setMationForMap(beans, "destinationId", "destinationMation");
        return beans;
    }

    @Override
    protected void validatorEntity(TransportationPath entity) {
        super.validatorEntity(entity);
        if (StrUtil.equals(entity.getDepartureId(), entity.getDestinationId())) {
            throw new CustomException("出发地和目的地不能一致，请确认");
        }
    }

    @Override
    public TransportationPath selectById(String id) {
        TransportationPath transportationPath = super.selectById(id);
        billingAddressService.setDataMation(transportationPath, TransportationPath::getDepartureId);
        billingAddressService.setDataMation(transportationPath, TransportationPath::getDestinationId);
        return transportationPath;
    }

    @Override
    public List<TransportationPath> selectByIds(String... ids) {
        List<TransportationPath> transportationPathList = super.selectByIds(ids);
        billingAddressService.setDataMation(transportationPathList, TransportationPath::getDepartureId);
        billingAddressService.setDataMation(transportationPathList, TransportationPath::getDestinationId);
        return transportationPathList;
    }

    @Override
    public void queryEnabledTransportationPathList(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<TransportationPath> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(TransportationPath::getEnabled), EnableEnum.ENABLE_USING.getKey());
        List<TransportationPath> transportationPathList = list(queryWrapper);
        outputObject.setBeans(transportationPathList);
        outputObject.settotal(transportationPathList.size());
    }

}
