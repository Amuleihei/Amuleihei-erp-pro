package com.skyeye.school.building.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.building.entity.Floor;
import com.skyeye.school.building.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: FloorController
 * @Description: 楼层教室管理控制层
 * @author: skyeye云系列--lqy
 * @date: 2024/11/6 14:55
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */

@RestController
@Api(value = "楼层教室管理", tags = "楼层教室管理", modelName = "楼层教室管理")
public class FloorController {

    @Autowired
    private FloorService floorService;

    /**
     * 新增/编辑楼层教室
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeOrUpdateFloor", value = "新增/编辑地点类型", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Floor.class)
    @RequestMapping("/post/FloorController/writeOrUpdateFloor")
    public void writeOrUpdateFloor(InputObject inputObject, OutputObject outputObject) {
        floorService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 批量删除楼层教室
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteFloorByIds", value = "批量删除楼层教室信息id之间用','隔开", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "ids", name = "ids", value = "主键id", required = "required")})
    @RequestMapping("/post/FloorController/deleteFloorByIds")
    public void deleteFloorByIds(InputObject inputObject, OutputObject outputObject) {
        floorService.deleteByIds(inputObject, outputObject);
    }

    /**
     * 根据Id获取楼层教室信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryFloorById", value = "根据Id获取楼层教室信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/FloorController/queryFloorById")
    public void queryFloorById(InputObject inputObject, OutputObject outputObject) {
        floorService.selectById(inputObject, outputObject);
    }

    /**
     * 根据holderId分页获取地点楼层教室信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryFloorListByHolderId", value = "根据holderId（地点id）分页获取地点楼层教室信息", method = "GET", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/FloorController/queryFloorListByHolderId")
    public void queryFloorListByHolderId(InputObject inputObject, OutputObject outputObject) {
        floorService.queryFloorListByHolderId(inputObject, outputObject);
    }

    /**
     * 根据floorNum 和 locationId获取一层的教室信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryClassesByFloorNumAndLocationId", value = "根据floorNum 和 locationId获取某层的教室信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id ="floorNum", name = "floorNum", value = "楼层号", required = "required"),
        @ApiImplicitParam(id= "locationId",name = "locationId", value = "地点id", required = "required")
    })
    @RequestMapping("/post/FloorController/queryClassesByFloorNumAndLocationId")
    public void queryClassesByFloorNumAndLocationId(InputObject inputObject, OutputObject outputObject) {
        floorService.queryClassesByFloorNumAndLocationId(inputObject, outputObject);
    }

}
