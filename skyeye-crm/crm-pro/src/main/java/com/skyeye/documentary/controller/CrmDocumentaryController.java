/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.documentary.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.documentary.entity.Documentary;
import com.skyeye.documentary.service.CrmDocumentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CrmDocumentaryController
 * @Description: 跟单管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/2/26 19:04
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "跟单管理", tags = "跟单管理", modelName = "跟单管理")
public class CrmDocumentaryController {

    @Autowired
    private CrmDocumentaryService customerService;

    /**
     * 获取跟单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDocumentaryList", value = "获取跟单列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/DocumentaryController/queryDocumentaryList")
    public void queryDocumentaryList(InputObject inputObject, OutputObject outputObject) {
        customerService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑跟单信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeCrmDocumentary", value = "新增/编辑跟单信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Documentary.class)
    @RequestMapping("/post/DocumentaryController/writeCrmDocumentary")
    public void writeCrmDocumentary(InputObject inputObject, OutputObject outputObject) {
        customerService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除跟单信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteDocumentaryById", value = "删除跟单信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/DocumentaryController/deleteDocumentaryById")
    public void deleteDocumentaryById(InputObject inputObject, OutputObject outputObject) {
        customerService.deleteById(inputObject, outputObject);
    }

}
