/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.seal.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.seal.entity.Seal;
import com.skyeye.eve.seal.service.SealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SealController
 * @Description: 印章管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/1 19:33
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "印章管理", tags = "印章管理", modelName = "印章模块")
public class SealController {

    @Autowired
    private SealService sealService;

    /**
     * 查询所有的印章
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "seal001", value = "查询所有的印章", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/SealController/querySealList")
    public void querySealList(InputObject inputObject, OutputObject outputObject) {
        sealService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/修改印章信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeSeal", value = "新增/修改印章信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = Seal.class)
    @RequestMapping("/post/SealController/writeSeal")
    public void writeSeal(InputObject inputObject, OutputObject outputObject) {
        sealService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id删除印章信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "seal003", value = "根据id删除印章信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SealController/deleteSealById")
    public void deleteSealById(InputObject inputObject, OutputObject outputObject) {
        sealService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取所有启用的印章信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "seal007", value = "获取所有启用的印章信息", method = "GET", allUse = "2")
    @RequestMapping("/post/SealController/queryAllEnabledSealList")
    public void queryAllEnabledSealList(InputObject inputObject, OutputObject outputObject) {
        sealService.queryAllEnabledSealList(inputObject, outputObject);
    }

    /**
     * 获取我借用中的所有印章信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sealrevert008", value = "获取我借用中的所有印章信息", method = "GET", allUse = "2")
    @RequestMapping("/post/SealController/queryMyRevertSealList")
    public void queryMyRevertSealList(InputObject inputObject, OutputObject outputObject) {
        sealService.queryMyRevertSealList(inputObject, outputObject);
    }

    /**
     * 获取我借用中的印章列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryMyRevertSealPageList", value = "获取我借用中的印章列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/SealController/queryMyRevertSealPageList")
    public void queryMyRevertSealPageList(InputObject inputObject, OutputObject outputObject) {
        sealService.queryMyRevertSealPageList(inputObject, outputObject);
    }

}
