/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.dict.SysDictType;
import com.skyeye.eve.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysDictTypeController
 * @Description: 数据字典类型管理
 * @author: skyeye云系列--卫志强
 * @date: 2022/6/30 22:26
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "数据字典类型管理", tags = "数据字典类型管理", modelName = "数据字典")
public class SysDictTypeController {

    @Autowired
    private SysDictTypeService sysDictTypeService;

    /**
     * 获取数据字典类型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDictTypeList", value = "获取数据字典类型列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/SysDictTypeController/queryDictTypeList")
    public void queryDictTypeList(InputObject inputObject, OutputObject outputObject) {
        sysDictTypeService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑数据字典类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeDictTypeMation", value = "新增/编辑数据字典类型", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysDictType.class)
    @RequestMapping("/post/SysDictTypeController/writeDictTypeMation")
    public void writeDictTypeMation(InputObject inputObject, OutputObject outputObject) {
        sysDictTypeService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据ID获取数据字典类型信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDictTypeMationById", value = "根据ID获取数据字典类型信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SysDictTypeController/queryDictTypeMationById")
    public void queryDictTypeMationById(InputObject inputObject, OutputObject outputObject) {
        sysDictTypeService.selectById(inputObject, outputObject);
    }

    /**
     * 根据ID删除数据字典类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteDictTypeMationById", value = "根据ID删除数据字典类型", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SysDictTypeController/deleteDictTypeMationById")
    public void deleteDictTypeMationById(InputObject inputObject, OutputObject outputObject) {
        sysDictTypeService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据状态获取数据字典类型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDictTypeListByEnabled", value = "根据状态获取数据字典类型列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "enabled", name = "enabled", value = "状态（1 启用  2.停用）", required = "num")})
    @RequestMapping("/post/SysDictTypeController/queryDictTypeListByEnabled")
    public void queryDictTypeListByEnabled(InputObject inputObject, OutputObject outputObject) {
        sysDictTypeService.queryDictTypeListByEnabled(inputObject, outputObject);
    }

}
