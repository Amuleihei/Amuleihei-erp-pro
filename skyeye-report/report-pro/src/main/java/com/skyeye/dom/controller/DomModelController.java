/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dom.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.dom.entity.DomModel;
import com.skyeye.dom.service.DomModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DomModelController
 * @Description: DOM模型控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/4 10:09
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "DOM模型管理", tags = "DOM模型管理", modelName = "DOM模型管理")
public class DomModelController {

    @Autowired
    private DomModelService domModelService;

    /**
     * 新增/编辑DOM模型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeDomModel", value = "新增/编辑DOM模型", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = DomModel.class)
    @RequestMapping("/post/DomModelController/writeDomModel")
    public void writeDomModel(InputObject inputObject, OutputObject outputObject) {
        domModelService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 获取DOM模型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDomModelList", value = "获取DOM模型列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/DomModelController/queryDomModelList")
    public void queryDomModelList(InputObject inputObject, OutputObject outputObject) {
        domModelService.queryPageList(inputObject, outputObject);
    }

    /**
     * 根据Id删除DOM模型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteDomModelById", value = "根据Id删除DOM模型", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/DomModelController/deleteDomModelById")
    public void deleteDomModelById(InputObject inputObject, OutputObject outputObject) {
        domModelService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取所有启动的DOM模型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAllEnabledDomModelList", value = "获取所有启动的DOM模型列表", method = "GET", allUse = "2")
    @RequestMapping("/post/DomModelController/queryAllEnabledDomModelList")
    public void queryAllEnabledDomModelList(InputObject inputObject, OutputObject outputObject) {
        domModelService.queryAllEnabledDomModelList(inputObject, outputObject);
    }

}
