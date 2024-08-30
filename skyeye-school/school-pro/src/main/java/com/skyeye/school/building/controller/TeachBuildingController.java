/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.building.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.building.entity.TeachBuilding;
import com.skyeye.school.building.service.TeachBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TeachBuildingController
 * @Description: 教学楼管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/8 14:55
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "教学楼管理", tags = "教学楼管理", modelName = "教学楼管理")
public class TeachBuildingController {

    @Autowired
    private TeachBuildingService teachBuildingService;

    /**
     * 获取教学楼信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryTeachBuildingList", value = "获取教学楼信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/TeachBuildingController/queryTeachBuildingList")
    public void queryTeachBuildingList(InputObject inputObject, OutputObject outputObject) {
        teachBuildingService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加或修改教学楼
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeTeachBuilding", value = "新增/编辑教学楼信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = TeachBuilding.class)
    @RequestMapping("/post/TeachBuildingController/writeTeachBuilding")
    public void writeTeachBuilding(InputObject inputObject, OutputObject outputObject) {
        teachBuildingService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除教学楼信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteTeachBuildingById", value = "根据ID删除教学楼信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/TeachBuildingController/deleteTeachBuildingById")
    public void deleteTeachBuildingById(InputObject inputObject, OutputObject outputObject) {
        teachBuildingService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据学校id获取教学楼信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryTeachBuildingBySchoolId", value = "根据学校id获取教学楼信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "schoolId", name = "schoolId", value = "学校id", required = "required")})
    @RequestMapping("/post/TeachBuildingController/queryTeachBuildingBySchoolId")
    public void queryTeachBuildingBySchoolId(InputObject inputObject, OutputObject outputObject) {
        teachBuildingService.queryTeachBuildingBySchoolId(inputObject, outputObject);
    }

}
