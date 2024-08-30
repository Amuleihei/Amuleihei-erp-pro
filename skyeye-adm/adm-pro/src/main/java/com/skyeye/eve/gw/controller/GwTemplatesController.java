/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.gw.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.gw.entity.GwTemplates;
import com.skyeye.eve.gw.service.GwTemplatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: GwTemplatesController
 * @Description: 套红模板控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/25 10:45
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "套红模板", tags = "套红模板", modelName = "套红模板")
public class GwTemplatesController {

    @Autowired
    private GwTemplatesService gwTemplatesService;

    /**
     * 查询套红模版列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryGwTemplatesList", value = "查询套红模版列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/GwTemplatesController/queryGwTemplatesList")
    public void queryGwTemplatesList(InputObject inputObject, OutputObject outputObject) {
        gwTemplatesService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/修改套红模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeGwTemplates", value = "新增/修改套红模版信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = GwTemplates.class)
    @RequestMapping("/post/GwTemplatesController/writeGwTemplates")
    public void writeGwTemplates(InputObject inputObject, OutputObject outputObject) {
        gwTemplatesService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id删除套红模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteGwTemplatesById", value = "根据id删除套红模版信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/GwTemplatesController/deleteGwTemplatesById")
    public void deleteGwTemplatesById(InputObject inputObject, OutputObject outputObject) {
        gwTemplatesService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取所有启用的套红模版列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryEnabledGwTemplatesList", value = "获取所有启用的套红模版列表", method = "GET", allUse = "2")
    @RequestMapping("/post/GwTemplatesController/queryEnabledGwTemplatesList")
    public void queryAllGwTemplatesList(InputObject inputObject, OutputObject outputObject) {
        gwTemplatesService.queryEnabledGwTemplatesList(inputObject, outputObject);
    }

}
