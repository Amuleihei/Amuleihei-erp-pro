package com.skyeye.school.location.controller;


import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.location.entity.Location;
import com.skyeye.school.location.entity.LocationServe;
import com.skyeye.school.location.service.LocationServeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: LocationServeController
 * @Description: 地点服务管理控制层
 * @author: skyeye云系列--lqy
 * @date: 2024/11/6 14:55
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */

@RestController
@Api(value = "地点服务管理", tags = "地点服务管理", modelName = "地点服务管理")
public class LocationServeController {

    @Autowired
    private LocationServeService locationServeService;

    /**
     * 新增/编辑地点服务
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeOrUpdateLocationServe", value = "新增/编辑地点类型", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = LocationServe.class)
    @RequestMapping("/post/LocationServeController/writeOrUpdateLocationServe")
    public void writeOrUpdateLocationServe(InputObject inputObject, OutputObject outputObject) {
        locationServeService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 批量删除地点服务信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteLocationServeByIds", value = "批量删除地点服务信息id之间用','隔开", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "ids", name = "ids", value = "主键id", required = "required")})
    @RequestMapping("/post/LocationServeController/deleteLocationServeByIds")
    public void deleteLocationServeByIds(InputObject inputObject, OutputObject outputObject) {
        locationServeService.deleteByIds(inputObject, outputObject);
    }

    /**
     * 根据地点服务Id获取地点服务信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryLocationServeById", value = "根据地点服务Id获取地点服务信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/LocationServeController/queryLocationServeById")
    public void queryLocationServeById(InputObject inputObject, OutputObject outputObject) {
        locationServeService.selectById(inputObject, outputObject);
    }

    /**
     * 根据floorId获取获取地点楼层教室信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryFloorServeListByHolderId", value = "根据holderId(floorId)获取获取地点楼层教室服务信息", method = "GET", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/LocationServeController/queryFloorServeListByFloorId")
    public void queryFloorServeListByHolderId(InputObject inputObject, OutputObject outputObject) {
        locationServeService.queryFloorServeListByHolderId(inputObject, outputObject);
    }



}
