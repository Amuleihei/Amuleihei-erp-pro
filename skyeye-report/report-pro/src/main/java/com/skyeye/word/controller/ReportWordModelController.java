/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.word.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.word.entity.WordModel;
import com.skyeye.word.service.ReportWordModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ReportWordModelController
 * @Description: 文字模型管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2021/9/5 16:21
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "文字模型管理", tags = "文字模型管理", modelName = "文字模型管理")
public class ReportWordModelController {

    @Autowired
    private ReportWordModelService reportWordModelService;

    /**
     * 获取文字模型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "reportwordmodel001", value = "获取文字模型列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ReportWordModelController/getReportWordModelList")
    public void getReportWordModelList(InputObject inputObject, OutputObject outputObject) {
        reportWordModelService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑文字模型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeWordModel", value = "新增/编辑文字模型", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = WordModel.class)
    @RequestMapping("/post/ReportWordModelController/writeWordModel")
    public void writeWordModel(InputObject inputObject, OutputObject outputObject) {
        reportWordModelService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据Id删除文字模型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteWordModelById", value = "根据Id删除文字模型", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ReportWordModelController/deleteWordModelById")
    public void deleteWordModelById(InputObject inputObject, OutputObject outputObject) {
        reportWordModelService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id查询文字模型信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryWordModelById", value = "根据id查询文字模型信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ReportWordModelController/queryWordModelById")
    public void queryWordModelById(InputObject inputObject, OutputObject outputObject) {
        reportWordModelService.selectById(inputObject, outputObject);
    }

    /**
     * 获取所有启用的文字模型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "getEnabledWordModelList", value = "获取所有启用的文字模型列表", method = "GET", allUse = "2")
    @RequestMapping("/post/ReportWordModelController/getEnabledWordModelList")
    public void getEnabledWordModelList(InputObject inputObject, OutputObject outputObject) {
        reportWordModelService.getEnabledWordModelList(inputObject, outputObject);
    }

}
