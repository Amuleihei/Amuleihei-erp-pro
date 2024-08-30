/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.afterseal.controller;

import com.skyeye.afterseal.entity.SealFeedBack;
import com.skyeye.afterseal.service.SealFeedBackService;
import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SealFeedBackController
 * @Description: 工单情况反馈信息控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/10 13:14
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@RestController
@Api(value = "工单情况反馈信息", tags = "工单情况反馈信息", modelName = "售后工单")
public class SealFeedBackController {

    @Autowired
    private SealFeedBackService sealFeedBackService;

    /**
     * 获取情况反馈列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryFeedBackList", value = "获取情况反馈列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/SealFeedBackController/queryFeedBackList")
    public void queryFeedBackList(InputObject inputObject, OutputObject outputObject) {
        sealFeedBackService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑情况反馈信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeFeedBack", value = "新增/编辑情况反馈信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = SealFeedBack.class)
    @RequestMapping("/post/SealFeedBackController/writeFeedBack")
    public void writeFeedBack(InputObject inputObject, OutputObject outputObject) {
        sealFeedBackService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除情况反馈
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteFeedBackById", value = "删除情况反馈", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SealFeedBackController/deleteFeedBackById")
    public void deleteFeedBackById(InputObject inputObject, OutputObject outputObject) {
        sealFeedBackService.deleteById(inputObject, outputObject);
    }

}
