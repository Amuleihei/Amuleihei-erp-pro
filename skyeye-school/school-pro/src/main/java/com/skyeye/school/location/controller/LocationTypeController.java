package com.skyeye.school.location.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.location.entity.LocationType;
import com.skyeye.school.location.service.LocationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: LocationTypeController
 * @Description: 地点类型管理控制层
 * @author: skyeye云系列--lqy
 * @date: 2024/11/6 14:55
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */

@RestController
@Api(value = "地点类型管理", tags = "地点类型管理", modelName = "地点类型管理")
public class LocationTypeController {

    @Autowired
    private LocationTypeService locationTypeService;

    /**
     * 新增/编辑地点类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeOrUpdateLocationType", value = "新增/编辑地点类型", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = LocationType.class)
    @RequestMapping("/post/LocationTypeController/writeOrUpdateLocationType")
    public void writeOrUpdateLocationType(InputObject inputObject, OutputObject outputObject) {
        locationTypeService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 批量删除地点类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteLocationTypeByIds", value = "批量删除地点类型id之间用','隔开", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "ids", name = "ids", value = "主键id", required = "required")})
    @RequestMapping("/post/LocationTypeController/deleteLocationTypeByIds")
    public void deleteLocationTypeByIds(InputObject inputObject, OutputObject outputObject) {
        locationTypeService.deleteByIds(inputObject, outputObject);
    }

    /**
     * 分页获取地点类型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryLocationTypeList", value = "获取地点类型列表", method = "GET", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/LocationTypeController/queryLocationTypeList")
    public void queryLocationTypeList(InputObject inputObject, OutputObject outputObject) {
        locationTypeService.queryPageList(inputObject, outputObject);
    }

}
