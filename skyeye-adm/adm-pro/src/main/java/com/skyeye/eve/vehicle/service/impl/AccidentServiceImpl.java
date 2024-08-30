/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.vehicle.service.impl;

import cn.hutool.core.util.StrUtil;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.eve.vehicle.dao.AccidentDao;
import com.skyeye.eve.vehicle.entity.Accident;
import com.skyeye.eve.vehicle.service.AccidentService;
import com.skyeye.eve.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: AccidentServiceImpl
 * @Description: 车辆事故管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/6/17 21:45
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "车辆事故管理", groupName = "车辆模块")
public class AccidentServiceImpl extends SkyeyeBusinessServiceImpl<AccidentDao, Accident> implements AccidentService {

    @Autowired
    private VehicleService vehicleService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo pageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryAccidentList(pageInfo);
        vehicleService.setMationForMap(beans, "vehicleId", "vehicleMation");
        // 设置驾驶员信息
        List<String> staffIds = beans.stream().map(bean -> bean.get("driverId").toString())
            .filter(staffId -> StrUtil.isNotEmpty(staffId)).distinct().collect(Collectors.toList());
        Map<String, Map<String, Object>> staffMap = iAuthUserService.queryUserMationListByStaffIds(staffIds);
        beans.forEach(bean -> {
            String driverId = bean.get("driverId").toString();
            bean.put("driverMation", staffMap.get(driverId));
        });
        return beans;
    }

    @Override
    public Accident selectById(String id) {
        Accident vehicleAccident = super.selectById(id);
        // 车辆信息
        vehicleService.setDataMation(vehicleAccident, Accident::getVehicleId);
        // 驾驶员信息
        Map<String, Map<String, Object>> staffMap = iAuthUserService
            .queryUserMationListByStaffIds(Arrays.asList(vehicleAccident.getDriverId()));
        Map<String, Object> staff = staffMap.get(vehicleAccident.getDriverId());
        vehicleAccident.setDriverMation(staff);
        return vehicleAccident;
    }

}
