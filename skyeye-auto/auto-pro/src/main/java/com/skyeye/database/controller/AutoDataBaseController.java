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
import com.skyeye.database.entity.AutoDataBase;
import com.skyeye.database.service.AutoDataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AutoDataBaseController
 * @Description: 数据库管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:17
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
@RestController
@Api(value = "数据库管理", tags = "数据库管理", modelName = "数据库管理")
public class AutoDataBaseController {

    @Autowired
    private AutoDataBaseService autoDataBaseService;

    /**
     * 获取数据库列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAutoDataBaseList", value = "获取数据库列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/AutoDataBaseController/queryAutoDataBaseList")
    public void queryAutoDataBaseList(InputObject inputObject, OutputObject outputObject) {
        autoDataBaseService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑数据库信
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeAutoDataBase", value = "新增/编辑数据库信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = AutoDataBase.class)
    @RequestMapping("/post/AutoDataBaseController/writeAutoDataBase")
    public void writeAutoDataBase(InputObject inputObject, OutputObject outputObject) {
        autoDataBaseService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id删除数据库信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteAutoDataBaseById", value = "根据id删除数据库信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AutoDataBaseController/deleteAutoDataBaseById")
    public void deleteAutoDataBaseById(InputObject inputObject, OutputObject outputObject) {
        autoDataBaseService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id查询数据库配置信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAutoDataBaseById", value = "根据id查询数据库配置信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AutoDataBaseController/queryAutoDataBaseById")
    public void queryAutoDataBaseById(InputObject inputObject, OutputObject outputObject) {
        autoDataBaseService.selectById(inputObject, outputObject);
    }

    /**
     * 获取所有数据库列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAllAutoDataBaseList", value = "获取所有数据库列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "objectId", name = "objectId", value = "所属第三方业务数据id", required = "required"),
        @ApiImplicitParam(id = "objectKey", name = "objectKey", value = "所属第三方业务数据的key", required = "required")})
    @RequestMapping("/post/AutoDataBaseController/queryAllAutoDataBaseList")
    public void queryAllAutoDataBaseList(InputObject inputObject, OutputObject outputObject) {
        autoDataBaseService.queryAllAutoDataBaseList(inputObject, outputObject);
    }

    /**
     * 测试数据源
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "testAutoDbConnection", value = "测试数据源", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "driverClass", name = "driverClass", value = "数据源驱动类", required = "required"),
        @ApiImplicitParam(id = "url", name = "url", value = "数据源连接字符串", required = "required"),
        @ApiImplicitParam(id = "user", name = "user", value = "用户名", required = "required"),
        @ApiImplicitParam(id = "pass", name = "pass", value = "密码")})
    @RequestMapping("/post/AutoDataBaseController/testAutoDbConnection")
    public void testAutoDbConnection(InputObject inputObject, OutputObject outputObject) {
        autoDataBaseService.testAutoDbConnection(inputObject, outputObject);
    }

    /**
     * 获取数据库类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAutoDataBaseTypeList", value = "获取数据库类型", method = "GET", allUse = "2")
    @RequestMapping("/post/AutoDataBaseController/queryAutoDataBaseTypeList")
    public void queryAutoDataBaseTypeList(InputObject inputObject, OutputObject outputObject) {
        autoDataBaseService.queryAutoDataBaseTypeList(inputObject, outputObject);
    }

    /**
     * 获取连接池类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAutoPoolTypeList", value = "获取连接池类型", method = "GET", allUse = "2")
    @RequestMapping("/post/AutoDataBaseController/queryAutoPoolTypeList")
    public void queryAutoPoolTypeList(InputObject inputObject, OutputObject outputObject) {
        autoDataBaseService.queryAutoPoolTypeList(inputObject, outputObject);
    }

}
