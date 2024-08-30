/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.follow.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.follow.entity.FollowUp;
import com.skyeye.follow.service.FollowUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: FollowUpController
 * @Description: 客户回访控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/2 10:44
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "回访管理", tags = "回访管理", modelName = "回访管理")
public class FollowUpController {

    @Autowired
    private FollowUpService followUpService;

    /**
     * 获取回访列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryFollowUpList", value = "获取回访列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/FollowUpController/queryFollowUpList")
    public void queryFollowUpList(InputObject inputObject, OutputObject outputObject) {
        followUpService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑回访信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeCrmFollowUp", value = "新增/编辑回访信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = FollowUp.class)
    @RequestMapping("/post/FollowUpController/writeCrmFollowUp")
    public void writeCrmFollowUp(InputObject inputObject, OutputObject outputObject) {
        followUpService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除回访信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteFollowUpById", value = "删除回访信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/FollowUpController/deleteFollowUpById")
    public void deleteFollowUpById(InputObject inputObject, OutputObject outputObject) {
        followUpService.deleteById(inputObject, outputObject);
    }

}
