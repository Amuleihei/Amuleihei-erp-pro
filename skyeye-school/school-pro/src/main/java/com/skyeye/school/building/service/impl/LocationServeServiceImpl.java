package com.skyeye.school.building.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;

import com.skyeye.school.building.dao.LocationServeDao;
import com.skyeye.school.building.entity.Floor;
import com.skyeye.school.building.entity.LocationServe;
import com.skyeye.school.building.service.FloorService;
import com.skyeye.school.building.service.LocationServeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: LocationServeServiceImpl
 * @Description: 地点服务管理服务层实现类
 * @author: skyeye云系列--lqy
 * @date: 2024/11/6 14:55
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "地点服务管理", groupName = "地点服务管理")
public class LocationServeServiceImpl extends SkyeyeBusinessServiceImpl<LocationServeDao, LocationServe> implements LocationServeService {

    @Autowired
    private FloorService floorService;



    @Override
    public void queryFloorServeListByHolderId(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        String floorId = commonPageInfo.getHolderId();
        Floor floor = floorService.selectById(floorId);


        Page page= PageHelper.startPage(commonPageInfo.getPage(), commonPageInfo.getLimit());
        QueryWrapper<LocationServe> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(LocationServe::getFloorId), floorId);
        List<LocationServe> list = list(queryWrapper);
        List<LocationServe> bean = list.stream().map(locationServe -> {
            locationServe.setFloorMation(floor);
            return locationServe;
        }).collect(Collectors.toList());
        outputObject.setBeans(bean);
        outputObject.settotal(page.getTotal());
    }

}
