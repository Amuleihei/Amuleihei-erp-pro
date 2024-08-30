/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.measurement.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.measurement.entity.Measurement;
import com.skyeye.school.measurement.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MeasurementController
 * @Description: 测试管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/2 10:47
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "测试管理", tags = "测试管理", modelName = "测试管理")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    /**
     * 添加或修改测试
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeMeasurement", value = "新增/编辑测试信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Measurement.class)
    @RequestMapping("/post/MeasurementController/writeMeasurement")
    public void writeMeasurement(InputObject inputObject, OutputObject outputObject) {
        measurementService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id查询测试信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryMeasurementById", value = "根据id查询测试信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ MeasurementController/queryMeasurementById")
    public void queryMeasurementById(InputObject inputObject, OutputObject outputObject) {
        measurementService.selectById(inputObject, outputObject);
    }

    /**
     * 删除测试信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteMeasurementById", value = "根据ID删除测试信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/MeasurementController/deleteMeasurementById")
    public void deleteMeasurementById(InputObject inputObject, OutputObject outputObject) {
        measurementService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据科目表与班级表的关系id获取测试列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryMeasurementListBySubjectClassesId", value = "根据科目表与班级表的关系id获取测试列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "subjectClassesId", name = "subjectClassesId", value = "科目表与班级表的关系id", required = "required")})
    @RequestMapping("/post/MeasurementController/queryMeasurementListBySubjectClassesId")
    public void queryMeasurementListBySubjectClassesId(InputObject inputObject, OutputObject outputObject) {
        measurementService.queryMeasurementListBySubjectClassesId(inputObject, outputObject);
    }
}