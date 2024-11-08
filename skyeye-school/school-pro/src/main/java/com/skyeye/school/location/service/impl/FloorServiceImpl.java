package com.skyeye.school.location.service.impl;


import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.school.location.dao.FloorDao;
import com.skyeye.school.location.entity.Floor;
import com.skyeye.school.location.entity.LocationServe;
import com.skyeye.school.location.service.FloorService;
import com.skyeye.school.location.service.LocationServeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: FloorServiceImpl
 * @Description: 楼层教室管理服务层实现类
 * @author: skyeye云系列--lqy
 * @date: 2024/11/6 14:55
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */

@Service
@SkyeyeService(name = "楼层教室管理", groupName = "楼层教室管理")
public class FloorServiceImpl extends SkyeyeBusinessServiceImpl<FloorDao, Floor> implements FloorService {

    @Autowired
    private LocationServeService locationServeService;

    @Override
    public void queryFloorListByHolderId(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        String locationId = commonPageInfo.getHolderId();
        Page page = PageHelper.startPage(commonPageInfo.getPage(), commonPageInfo.getLimit());
        QueryWrapper<Floor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(Floor::getLocationId), locationId);
        queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(Floor::getCreateTime));
        List<Floor> floorList = list(queryWrapper);
        outputObject.setBeans(floorList);
        outputObject.settotal(page.getTotal());
    }

    @Override
    public void queryClassesByFloorNumAndLocationId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String locationId =  params.get("locationId").toString();
        String floorNum = params.get("floorNum").toString();
        if (StrUtil.isEmpty(locationId)){
            throw new CustomException("locationId不能为空");
        }
        if (StrUtil.isEmpty(floorNum)){
            throw new CustomException("floorNum不能为空");
        }
        QueryWrapper<Floor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(Floor::getLocationId), locationId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(Floor::getFloorNum), floorNum);
        List<Floor> floorList = list(queryWrapper);
        outputObject.setBeans(floorList);
        outputObject.settotal(floorList.size());
    }

    @Override
    public void validatorEntity(Floor floor) {
        String floorNum = floor.getFloorNum();
        String classNum = floor.getClassNum();
        QueryWrapper<Floor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(Floor::getFloorNum), floorNum);
        queryWrapper.eq(MybatisPlusUtil.toColumns(Floor::getClassNum), classNum);
        Floor oldFloor = getOne(queryWrapper);
        if(ObjectUtil.isNotEmpty(oldFloor)){
            throw new CustomException("该楼层教室号已存在");
        }
    }

    @Transactional
    @Override
    public void deleteByIds(InputObject inputObject, OutputObject outputObject) {
        String ids = inputObject.getParams().get("ids").toString();
        List<String> idList = Arrays.asList(ids.split(","));
        idList = idList.stream()
                .filter(CharSequenceUtil::isNotEmpty)
                .distinct().collect(Collectors.toList());
        deleteById(idList);

        for(String id :idList){
            QueryWrapper<LocationServe> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(MybatisPlusUtil.toColumns(LocationServe::getFloorId), id);
            locationServeService.remove(queryWrapper);
        }
    }
}
