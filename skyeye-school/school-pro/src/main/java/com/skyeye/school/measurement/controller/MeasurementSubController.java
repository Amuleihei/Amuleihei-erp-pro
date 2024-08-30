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
import com.skyeye.school.measurement.entity.MeasurementSub;
import com.skyeye.school.measurement.service.MeasurementSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MeasurementSubController
 * @Description: 测试提交控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/2 11:11
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "测试提交", tags = "测试提交", modelName = "测试管理")
public class MeasurementSubController {

    @Autowired
    private MeasurementSubService measurementSubService;

    /**
     * 提交测试信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeMeasurementSub", value = "提交测试信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = MeasurementSub.class)
    @RequestMapping("/post/MeasurementSubController/writeMeasurementSub")
    public void writeMeasurementSub(InputObject inputObject, OutputObject outputObject) {
        measurementSubService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id查询测试提交信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryMeasurementSubById", value = "根据id查询测试提交信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/MeasurementSubController/queryMeasurementSubById")
    public void queryMeasurementSubById(InputObject inputObject, OutputObject outputObject) {
        measurementSubService.selectById(inputObject, outputObject);
    }

    /**
     * 根据ID删除测试提交信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteMeasurementSubById", value = "根据ID删除测试提交信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/MeasurementSubController/deleteMeasurementSubById")
    public void deleteMeasurementSubById(InputObject inputObject, OutputObject outputObject) {
        measurementSubService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据测试id获取测试提交信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryMeasurementStuSubListByMeasurementId", value = "根据测试id获取测试提交信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "measurementId", name = "measurementId", value = "测试id", required = "required")})
    @RequestMapping("/post/MeasurementSubController/queryMeasurementStuSubListByMeasurementId")
    public void queryMeasurementStuSubListByMeasurementId(InputObject inputObject, OutputObject outputObject) {
        measurementSubService.queryMeasurementStuSubListByMeasurementId(inputObject, outputObject);
    }

    /**
     * 根据测试id获取已经提交的学生信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryMeasurementSubListByMeasurementId", value = "根据测试id获取已经提交的学生信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "measurementId", name = "measurementId", value = "测试id")})
    @RequestMapping("/post/MeasurementSubController/queryMeasurementSubListByMeasurementId")
    public void queryMeasurementSubListByMeasurementId(InputObject inputObject, OutputObject outputObject) {
        measurementSubService.queryMeasurementSubListByMeasurementId(inputObject, outputObject);
    }

    /**
     * 根据测试id获取未提交的学生信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryMeasurementNotSubListByMeasurementId", value = "根据测试id获取未提交的学生信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "measurementId", name = "measurementId", value = "测试id")})
    @RequestMapping("/post/MeasurementSubController/queryMeasurementNotSubListByMeasurementId")
    public void queryMeasurementNotSubListByMeasurementId(InputObject inputObject, OutputObject outputObject) {
        measurementSubService.queryMeasurementNotSubListByMeasurementId(inputObject, outputObject);
    }

    /**
     * 批阅测试
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "readOverMeasurementSubById", value = "批阅测试", method = "POST", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required"),
            @ApiImplicitParam(id = "score", name = "score", value = "得分", required = "required"),
            @ApiImplicitParam(id = "comment", name = "comment", value = "评语")})
    @RequestMapping("/post/MeasurementSubController/readOverMeasurementSubById")
    public void readOverMeasurementSubById(InputObject inputObject, OutputObject outputObject) {
        measurementSubService.readOverMeasurementSubById(inputObject, outputObject);
    }
}