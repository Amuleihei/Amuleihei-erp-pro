/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.articles.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.articles.entity.Articles;
import com.skyeye.eve.articles.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ArticlesController
 * @Description: 用品管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/24 11:48
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "用品管理", tags = "用品管理", modelName = "用品模块")
public class ArticlesController {

    @Autowired
    private ArticlesService articlesService;

    /**
     * 新增/编辑用品信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeAssetArticlesMation", value = "新增/编辑用品信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = Articles.class)
    @RequestMapping("/post/ArticlesController/writeAssetArticlesMation")
    public void writeArticlesMation(InputObject inputObject, OutputObject outputObject) {
        articlesService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 获取用品列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "assetarticles012", value = "获取用品列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ArticlesController/queryArticlesList")
    public void queryArticlesList(InputObject inputObject, OutputObject outputObject) {
        articlesService.queryPageList(inputObject, outputObject);
    }

    /**
     * 删除用品
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "assetarticles014", value = "删除用品", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ArticlesController/deleteArticles")
    public void deleteArticles(InputObject inputObject, OutputObject outputObject) {
        articlesService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取所有用品列表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "assetarticles018", value = "获取所有用品列表信息", method = "GET", allUse = "2")
    @RequestMapping("/post/ArticlesController/queryAllArticlesList")
    public void queryAllArticlesList(InputObject inputObject, OutputObject outputObject) {
        articlesService.queryAllArticlesList(inputObject, outputObject);
    }

}
