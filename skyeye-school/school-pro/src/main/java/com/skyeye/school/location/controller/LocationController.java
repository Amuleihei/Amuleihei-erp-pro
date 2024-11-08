package com.skyeye.school.location.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.location.entity.Location;
import com.skyeye.school.location.entity.LocationType;
import com.skyeye.school.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: LocationController
 * @Description: 地点管理控制层
 * @author: skyeye云系列--lqy
 * @date: 2024/11/6 14:55
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "地点管理", tags = "地点管理", modelName = "地点管理")
public class LocationController {

    @Autowired
    private LocationService locationService;

    /**
     * 新增/编辑地点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeOrUpdateLocation", value = "新增/编辑地点类型", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Location.class)
    @RequestMapping("/post/LocationController/writeOrUpdateLocation")
    public void writeOrUpdateLocation(InputObject inputObject, OutputObject outputObject) {
        locationService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 批量删除地点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteLocationByIds", value = "批量删除地点id之间用','隔开", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "ids", name = "ids", value = "主键id", required = "required")})
    @RequestMapping("/post/LocationController/deleteLocationByIds")
    public void deleteLocationByIds(InputObject inputObject, OutputObject outputObject) {
        locationService.deleteByIds(inputObject, outputObject);
    }

    /**
     * 根据地点类型typeId获取分页获取地点列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryLocationListByHolderId", value = "根据holderId(typeId地点类型id)获取地点列表", method = "GET", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/LocationController/queryLocationListByHolderId")
    public void queryLocationListByHolderId(InputObject inputObject, OutputObject outputObject) {
        locationService.queryLocationListByHolderId(inputObject, outputObject);
    }

    /**
     * 根据地点Id获取地点信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryLocationById", value = "根据地点Id获取地点信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/LocationController/queryLocationById")
    public void queryLocationById(InputObject inputObject, OutputObject outputObject) {
        locationService.selectById(inputObject, outputObject);
    }

}
