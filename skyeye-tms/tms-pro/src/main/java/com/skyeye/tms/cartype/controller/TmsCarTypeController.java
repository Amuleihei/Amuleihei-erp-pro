/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tms.cartype.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.tms.cartype.entity.TmsCarType;
import com.skyeye.tms.cartype.service.TmsCarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TmsCarTypeController
 * @Description:车辆类型控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/9/5 15:17
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "车辆类型", tags = "车辆类型", modelName = "车辆类型")
public class TmsCarTypeController {

    @Autowired
    private TmsCarTypeService tmsCarTypeService;

    /**
     * 获取车辆类型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryTmsCarTypeList", value = "获取车辆类型列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/TmsCarTypeController/queryTmsCarTypeList")
    public void queryTmsCarTypeList(InputObject inputObject, OutputObject outputObject) {
        tmsCarTypeService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加或修改车辆类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeTmsCarType", value = "新增/编辑车辆类型", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = TmsCarType.class)
    @RequestMapping("/post/TmsCarTypeController/writeTmsCarType")
    public void writeTmsCarType(InputObject inputObject, OutputObject outputObject) {
        tmsCarTypeService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除车辆类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteTmsCarTypeById", value = "根据ID删除车辆类型", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/TmsCarTypeController/deleteTmsCarTypeById")
    public void deleteTmsCarTypeById(InputObject inputObject, OutputObject outputObject) {
        tmsCarTypeService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取已启用的车辆类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryEnabledTmsCarType", value = "获取已启用的车辆类型", method = "GET", allUse = "2")
    @RequestMapping("/post/TmsCarTypeController/queryEnabledTmsCarType")
    public void queryEnabledTmsCarType(InputObject inputObject, OutputObject outputObject) {
        tmsCarTypeService.queryEnabledTmsCarType(inputObject, outputObject);
    }

}


