/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/
package com.skyeye.background.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.background.entity.Background;
import com.skyeye.background.service.BackgroundService;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: BackgroundController
 * @Description: 背景图片管理
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/24 14:31
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "背景图片管理", tags = "背景图片管理", modelName = "背景图片管理")
public class BackgroundController {

    @Autowired
    private BackgroundService backgroundService;

    /**
     * 分页查询历史背景图片
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "queryBackgroundList", value = "分页查询历史背景图片", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/BackgroundController/queryBackgroundList")
    public void queryBackgroundList(InputObject inputObject, OutputObject outputObject) {
        backgroundService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增背景图片
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "insertBackground", value = "修改背景图片", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Background.class)
    @RequestMapping("/post/BackgroundController/insertBackground")
    public void insertBackground(InputObject inputObject, OutputObject outputObject) {
        backgroundService.createEntity(inputObject, outputObject);
    }

    /**
     * 根据id删除背景图片
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "deleteBackgroundById", value = "根据id删除背景图片", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/BackgroundController/deleteBackgroundById")
    public void deleteBackgroundById(InputObject inputObject, OutputObject outputObject) {
        backgroundService.deleteById(inputObject, outputObject);
    }
}