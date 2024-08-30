/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.img.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.img.entity.ImgModel;
import com.skyeye.img.service.ReportImgModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ReportImgModelController
 * @Description: 图片模型控制层
 * @author: skyeye云系列--卫志强
 * @date: 2021/9/5 16:21
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "图片模型管理", tags = "图片模型管理", modelName = "图片模型管理")
public class ReportImgModelController {

    @Autowired
    private ReportImgModelService reportImgModelService;

    /**
     * 新增/编辑图片模型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeImgModel", value = "新增/编辑图片模型", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = ImgModel.class)
    @RequestMapping("/post/ReportImgModelController/writeImgModel")
    public void writeImgModel(InputObject inputObject, OutputObject outputObject) {
        reportImgModelService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 获取图片模型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryImgModelList", value = "获取图片模型列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ReportImgModelController/queryImgModelList")
    public void queryImgModelList(InputObject inputObject, OutputObject outputObject) {
        reportImgModelService.queryPageList(inputObject, outputObject);
    }

    /**
     * 根据Id删除图片模型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteImgModelById", value = "根据Id删除图片模型", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ReportImgModelController/deleteImgModelById")
    public void deleteImgModelById(InputObject inputObject, OutputObject outputObject) {
        reportImgModelService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取所有启动的图片模型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAllEnabledImgModelList", value = "获取所有启动的图片模型列表", method = "GET", allUse = "2")
    @RequestMapping("/post/ReportImgModelController/queryAllEnabledImgModelList")
    public void queryAllEnabledImgModelList(InputObject inputObject, OutputObject outputObject) {
        reportImgModelService.queryAllEnabledImgModelList(inputObject, outputObject);
    }

}
