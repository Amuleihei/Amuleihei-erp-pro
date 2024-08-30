/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.bgimg.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.bgimg.entity.BgImage;
import com.skyeye.bgimg.service.ReportBgImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ReportBgImageController
 * @Description: 背景图片管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/3 8:33
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "背景图片管理", tags = "背景图片管理", modelName = "背景图片管理")
public class ReportBgImageController {

    @Autowired
    private ReportBgImageService reportBgImageService;

    /**
     * 获取背景图片列表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "reportbgimage001", value = "获取背景图片列表信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ReportBgImageController/queryReportBgImageList")
    public void queryReportBgImageList(InputObject inputObject, OutputObject outputObject) {
        reportBgImageService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑背景图片
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeBgImage", value = "新增/编辑背景图片", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = BgImage.class)
    @RequestMapping("/post/ReportBgImageController/writeBgImage")
    public void writeBgImage(InputObject inputObject, OutputObject outputObject) {
        reportBgImageService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除背景图片信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteBgImageById", value = "删除背景图片信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ReportBgImageController/deleteBgImageById")
    public void deleteBgImageById(InputObject inputObject, OutputObject outputObject) {
        reportBgImageService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取所有启用的背景图片列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "getEnabledBgImageList", value = "获取所有启用的背景图片列表", method = "GET", allUse = "2")
    @RequestMapping("/post/ReportBgImageController/getEnabledBgImageList")
    public void getEnabledBgImageList(InputObject inputObject, OutputObject outputObject) {
        reportBgImageService.getEnabledBgImageList(inputObject, outputObject);
    }

}
