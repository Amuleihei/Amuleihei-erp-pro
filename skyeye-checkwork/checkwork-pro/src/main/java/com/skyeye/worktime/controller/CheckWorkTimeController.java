/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.worktime.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.worktime.entity.CheckWorkTime;
import com.skyeye.worktime.service.CheckWorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CheckWorkTimeController
 * @Description: 考勤班次管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/4/3 14:37
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "考勤班次", tags = "考勤班次", modelName = "考勤班次")
public class CheckWorkTimeController {

    @Autowired
    private CheckWorkTimeService checkWorkTimeService;

    /**
     * 查询考勤班次列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "checkworktime001", value = "查询考勤班次列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/CheckWorkTimeController/queryCheckWorkTimeList")
    public void queryCheckWorkTimeList(InputObject inputObject, OutputObject outputObject) {
        checkWorkTimeService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑考勤班次信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeCheckWorkTime", value = "新增/编辑考勤班次信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CheckWorkTime.class)
    @RequestMapping("/post/CheckWorkTimeController/writeCheckWorkTime")
    public void writeCheckWorkTime(InputObject inputObject, OutputObject outputObject) {
        checkWorkTimeService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 设置线上打卡的信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "setOnlineCheckWorkTime", value = "设置线上打卡的信息", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required"),
        @ApiImplicitParam(id = "longitude", name = "longitude", value = "经度", required = "required"),
        @ApiImplicitParam(id = "latitude", name = "latitude", value = "纬度", required = "required"),
        @ApiImplicitParam(id = "provinceId", name = "provinceId", value = "省", required = "required"),
        @ApiImplicitParam(id = "cityId", name = "cityId", value = "市", required = "required"),
        @ApiImplicitParam(id = "areaId", name = "areaId", value = "区县", required = "required"),
        @ApiImplicitParam(id = "townshipId", name = "townshipId", value = "乡镇", required = "required"),
        @ApiImplicitParam(id = "absoluteAddress", name = "absoluteAddress", value = "具体地址", required = "required")})
    @RequestMapping("/post/CheckWorkTimeController/setOnlineCheckWorkTime")
    public void setOnlineCheckWorkTime(InputObject inputObject, OutputObject outputObject) {
        checkWorkTimeService.setOnlineCheckWorkTime(inputObject, outputObject);
    }

    /**
     * 根据id查询考勤班次信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryCheckWorkTimeById", value = "根据id查询考勤班次信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/CheckWorkTimeController/queryCheckWorkTimeById")
    public void queryCheckWorkTimeById(InputObject inputObject, OutputObject outputObject) {
        checkWorkTimeService.selectById(inputObject, outputObject);
    }

    /**
     * 根据id删除考勤班次信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteCheckWorkTimeById", value = "根据id删除考勤班次信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/CheckWorkTimeController/deleteCheckWorkTimeById")
    public void deleteCheckWorkTimeById(InputObject inputObject, OutputObject outputObject) {
        checkWorkTimeService.deleteById(inputObject, outputObject);
    }

    /**
     * 查询启用的考勤班次列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryEnableCheckWorkTimeList", value = "查询启用的考勤班次列表", method = "GET", allUse = "2")
    @RequestMapping("/post/CheckWorkTimeController/queryEnableCheckWorkTimeList")
    public void queryEnableCheckWorkTimeList(InputObject inputObject, OutputObject outputObject) {
        checkWorkTimeService.queryEnableCheckWorkTimeList(inputObject, outputObject);
    }

    /**
     * 获取当前登陆人的考勤班次
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "checkworktime007", value = "获取当前登陆人的考勤班次", method = "GET", allUse = "2")
    @RequestMapping("/post/CheckWorkTimeController/queryCheckWorkTimeListByLoginUser")
    public void queryCheckWorkTimeListByLoginUser(InputObject inputObject, OutputObject outputObject) {
        checkWorkTimeService.queryCheckWorkTimeListByLoginUser(inputObject, outputObject);
    }

    /**
     * 根据指定年月获取所有的考勤班次的信息以及工作日信息等
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "getAllCheckWorkTime", value = "根据指定年月获取所有的考勤班次的信息以及工作日信息等", method = "GET", allUse = "0")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "pointMonthDate", name = "pointMonthDate", value = "指定年月，格式为yyyy-MM", required = "required")})
    @RequestMapping("/post/CheckWorkTimeController/getAllCheckWorkTime")
    public void getAllCheckWorkTime(InputObject inputObject, OutputObject outputObject) {
        checkWorkTimeService.getAllCheckWorkTime(inputObject, outputObject);
    }

}
