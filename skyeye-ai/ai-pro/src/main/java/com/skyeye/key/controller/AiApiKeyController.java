/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.key.controller;

import com.skyeye.key.entity.AiApiKey;
import com.skyeye.key.service.AiApiKeyService;
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
 * @ClassName: ShopDeliveryCompanyController
 * @Description: ai配置控制类
 * @author: skyeye云系列--卫志强
 * @date: 2024/10/8 10:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "API配置", tags = "API配置", modelName = "API配置")
public class AiApiKeyController  {

    @Autowired
    private AiApiKeyService aiApiKeyService;

    /**
     * 新增/编辑API配置
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeAiApiKey", value = "新增/编辑API配置", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = AiApiKey.class)
    @RequestMapping("/post/aiApiKeyController/writeAiApiKey")
    public void writeAiApiKey(InputObject inputObject, OutputObject outputObject) {
        aiApiKeyService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 分页查询API配置
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAiApiKey", value = "分页查询API配置", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/aiApiKeyController/queryAiApiKey")
    public void queryAiApiKey(InputObject inputObject, OutputObject outputObject) {
        aiApiKeyService.queryPageList(inputObject, outputObject);
    }

    /**
     * 批量删除API配置
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteAiApiKeyByIds", value = "批量删除API配置", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "ids", name = "ids", value = "主键id列表，多个id用逗号分隔", required = "required")})
    @RequestMapping("/post/aiApiKeyController/deleteAiApiKeyByIds")
    public void deleteAiApiKeyByIds(InputObject inputObject, OutputObject outputObject) {
        aiApiKeyService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id获取API配置
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "selectAiApiKeyById", value = "根据id获取API配置", method = "POST", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/aiApiKeyController/selectAiApiKeyById")
    public void selectAiApiKeyById(InputObject inputObject, OutputObject outputObject) {
        aiApiKeyService.selectById(inputObject, outputObject);
    }

    /**
     * 获取全部API配置
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAiApiKeyList", value = "获取广告位管理信息", method = "POST", allUse = "0")
    @RequestMapping("/post/aiApiKeyController/queryAiApiKeyList")
    public void queryAiApiKeyList(InputObject inputObject, OutputObject outputObject) {
        aiApiKeyService.queryList(inputObject, outputObject);
    }
}
