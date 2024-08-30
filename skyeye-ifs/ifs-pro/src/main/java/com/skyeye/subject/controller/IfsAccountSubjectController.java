/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.subject.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.subject.entity.AccountSubject;
import com.skyeye.subject.service.IfsAccountSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: IfsAccountSubjectController
 * @Description: 会计科目管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/3/12 21:54
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "会计科目管理", tags = "会计科目管理", modelName = "会计科目管理")
public class IfsAccountSubjectController {

    @Autowired
    private IfsAccountSubjectService ifsAccountSubjectService;

    /**
     * 获取会计科目列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "ifsaccountsubject001", value = "获取会计科目列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/IfsAccountSubjectController/queryIfsAccountSubjectList")
    public void queryIfsAccountSubjectList(InputObject inputObject, OutputObject outputObject) {
        ifsAccountSubjectService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑会计科目
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeIfsAccountSubject", value = "新增/编辑会计科目", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = AccountSubject.class)
    @RequestMapping("/post/IfsAccountSubjectController/writeIfsAccountSubject")
    public void writeIfsAccountSubject(InputObject inputObject, OutputObject outputObject) {
        ifsAccountSubjectService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除会计科目信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "ifsaccountsubject005", value = "删除会计科目信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/IfsAccountSubjectController/deleteIfsAccountSubjectById")
    public void deleteIfsAccountSubjectById(InputObject inputObject, OutputObject outputObject) {
        ifsAccountSubjectService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取已启用的会计科目
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryEnabledSubjectList", value = "获取已启用的会计科目", method = "GET", allUse = "2")
    @RequestMapping("/post/IfsAccountSubjectController/queryEnabledSubjectList")
    public void queryEnabledSubjectList(InputObject inputObject, OutputObject outputObject) {
        ifsAccountSubjectService.queryEnabledSubjectList(inputObject, outputObject);
    }

}
