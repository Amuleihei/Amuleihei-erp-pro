/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye-report
 ******************************************************************************/

package com.skyeye.database.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.database.entity.DataBase;
import com.skyeye.database.service.ReportDataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ReportDataBaseController
 * @Description: 数据库管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:17
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
@RestController
@Api(value = "数据库管理", tags = "数据库管理", modelName = "数据库管理")
public class ReportDataBaseController {

    @Autowired
    private ReportDataBaseService reportDataBaseService;

    /**
     * 获取数据库列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "reportdatabase001", value = "获取数据库列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ReportDataBaseController/queryDataBaseList")
    public void queryDataBaseList(InputObject inputObject, OutputObject outputObject) {
        reportDataBaseService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑数据库信
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeDataBase", value = "新增/编辑数据库信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = DataBase.class)
    @RequestMapping("/post/ReportDataBaseController/writeDataBase")
    public void writeDataBase(InputObject inputObject, OutputObject outputObject) {
        reportDataBaseService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id删除数据库信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteDataBaseById", value = "根据id删除数据库信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ReportDataBaseController/deleteDataBaseById")
    public void deleteDataBaseById(InputObject inputObject, OutputObject outputObject) {
        reportDataBaseService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id查询数据库配置信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDataBaseById", value = "根据id查询数据库配置信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ReportDataBaseController/queryDataBaseById")
    public void queryDataBaseById(InputObject inputObject, OutputObject outputObject) {
        reportDataBaseService.selectById(inputObject, outputObject);
    }

    /**
     * 获取所有数据库列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAllDataBaseList", value = "获取所有数据库列表", method = "GET", allUse = "2")
    @RequestMapping("/post/ReportDataBaseController/queryAllDataBaseList")
    public void queryAllDataBaseList(InputObject inputObject, OutputObject outputObject) {
        reportDataBaseService.queryAllDataBaseList(inputObject, outputObject);
    }

}
