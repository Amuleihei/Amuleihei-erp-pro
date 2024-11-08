package com.skyeye.school.location.service.impl;


import cn.hutool.core.text.CharSequenceUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.school.location.dao.LocationDao;
import com.skyeye.school.location.entity.*;
import com.skyeye.school.location.service.FloorService;
import com.skyeye.school.location.service.LocationServeService;
import com.skyeye.school.location.service.LocationService;
import com.skyeye.school.location.service.LocationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: LocationServiceImpl
 * @Description: 地点管理服务层实现类
 * @author: skyeye云系列--lqy
 * @date: 2024/11/6 14:55
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "地点管理", groupName = "地点管理")
public class LocationServiceImpl extends SkyeyeBusinessServiceImpl<LocationDao, Location> implements LocationService {

    @Autowired
    private LocationTypeService locationTypeService;

    @Autowired
    private FloorService floorService;

    @Autowired
    private LocationServeService locationServeService;


    @Override
    public void queryLocationListByHolderId(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        String typeId = commonPageInfo.getHolderId();
        if(StringUtils.isEmpty(typeId)){
            throw new CustomException("地点分类id不能为空");
        }
        LocationType locationType = locationTypeService.selectById(typeId);
        Page page = PageHelper.startPage(commonPageInfo.getPage(), commonPageInfo.getLimit());
        QueryWrapper<Location> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(Location::getTypeId), typeId);
        List<Location> locationList = list(queryWrapper);
        List<Location> locationLists = locationList.stream().map(location -> {
            location.setTypeMation(locationType);
            return location;
        }).collect(Collectors.toList());
        outputObject.setBeans(locationLists);
        outputObject.settotal(page.getTotal());
    }

    @Override
    public void validatorEntity(Location location) {
        Float longitude = location.getLongitude();
        Float latitude = location.getLatitude();
        String longitudeRegex = LocationConstants.LONGITUDE_REGEX;
        String latitudeRegex = LocationConstants.LATITUDE_REGEX;
        if (!longitude.toString().matches(longitudeRegex)) {
            throw new CustomException("经度格式不正确");
        }
        if (!latitude.toString().matches(latitudeRegex)) {
            throw new CustomException("纬度格式不正确");
        }
    }

    @Override
    public void selectById(InputObject inputObject, OutputObject outputObject) {
        String id = inputObject.getParams().get("id").toString();
        Location location = selectById(id);
        LocationType locationType = locationTypeService.selectById(location.getTypeId());
        location.setTypeMation(locationType);
        outputObject.setBean(location);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    @Transactional
    @Override
    public void deleteByIds(InputObject inputObject, OutputObject outputObject){
        String ids = inputObject.getParams().get("ids").toString();
        List<String> idList = Arrays.asList(ids.split(","));
        idList = idList.stream()
                .filter(CharSequenceUtil::isNotEmpty)
                .distinct().collect(Collectors.toList());
        deleteById(idList);
        // 级联删除
        for (String locationId : idList){
            QueryWrapper<Floor> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(MybatisPlusUtil.toColumns(Floor::getLocationId), locationId);
            List<Floor> floorList = floorService.list(queryWrapper);
            floorService.remove(queryWrapper);
            for (Floor floor : floorList){
                QueryWrapper<LocationServe> serveQueryWrapper = new QueryWrapper<>();
                serveQueryWrapper.eq(MybatisPlusUtil.toColumns(LocationServe::getFloorId), floor.getId());
                locationServeService.remove(serveQueryWrapper);
            }
        }
    }
}
