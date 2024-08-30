/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.department.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.department.entity.Department;
import com.skyeye.school.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DepartmentController
 * @Description: 教研室信息管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/18 16:56
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "教研室管理", tags = "教研室管理", modelName = "教研室管理")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取教研室信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDepartmentList", value = "获取教研室信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/DepartmentController/queryDepartmentList")
    public void queryDepartmentList(InputObject inputObject, OutputObject outputObject) {
        departmentService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加或修改教研室信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeDepartment", value = "新增/编辑教研室信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = Department.class)
    @RequestMapping("/post/DepartmentController/writeDepartment")
    public void writeDepartment(InputObject inputObject, OutputObject outputObject) {
        departmentService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除教研室信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteDepartmentById", value = "根据ID删除教研室信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/DepartmentController/deleteDepartmentById")
    public void deleteDepartmentById(InputObject inputObject, OutputObject outputObject) {
        departmentService.deleteById(inputObject, outputObject);
    }

}
