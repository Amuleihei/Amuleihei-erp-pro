/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.field.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.wages.WagesStaffWorkTimeMation;
import com.skyeye.eve.field.service.FieldStaffLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: FieldStaffLinkController
 * @Description: 员工与薪资字段关系管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/11/4 14:09
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@RestController
@Api(value = "员工与薪资字段关系管理", tags = "员工与薪资字段关系管理", modelName = "员工与薪资字段关系管理")
public class FieldStaffLinkController {

    @Autowired
    private FieldStaffLinkService wagesStaffMationService;

    /**
     * 根据员工id获取该员工拥有的薪资字段
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "wagesstaff002", value = "根据员工id获取该员工拥有的薪资字段", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "staffId", name = "staffId", value = "员工id", required = "required")})
    @RequestMapping("/post/WagesStaffMationController/queryStaffWagesModelFieldMationListByStaffId")
    public void queryStaffWagesModelFieldMationListByStaffId(InputObject inputObject, OutputObject outputObject) {
        wagesStaffMationService.queryStaffWagesModelFieldMationListByStaffId(inputObject, outputObject);
    }

    /**
     * 保存员工薪资设定
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "wagesstaff003", value = "保存员工薪资设定", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "staffId", name = "staffId", value = "员工id", required = "required"),
        @ApiImplicitParam(id = "fieldStr", name = "fieldStr", value = "薪资信息", required = "required"),
        @ApiImplicitParam(id = "actMoney", name = "actMoney", value = "员工月标准工资", required = "required,double")})
    @RequestMapping("/post/WagesStaffMationController/saveStaffWagesModelFieldMation")
    public void saveStaffWagesModelFieldMation(InputObject inputObject, OutputObject outputObject) {
        wagesStaffMationService.saveStaffWagesModelFieldMation(inputObject, outputObject);
    }

    /**
     * 获取应出勤的班次以及小时
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "setLastMonthBe", value = "获取应出勤的班次以及小时", method = "POST", allUse = "0")
    @ApiImplicitParams(classBean = WagesStaffWorkTimeMation.class)
    @RequestMapping("/post/WagesStaffMationController/setLastMonthBe")
    public void setLastMonthBe(InputObject inputObject, OutputObject outputObject) {
        wagesStaffMationService.setLastMonthBe(inputObject, outputObject);
    }

}
