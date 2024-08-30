/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.family.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.family.entity.Family;
import com.skyeye.family.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: FamilyController
 * @Description: 员工家庭成员信息管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/5/17 7:56
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "员工家庭成员", tags = "员工家庭成员", modelName = "员工家庭成员")
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    /**
     * 查询家庭情况列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryFamilyList", value = "查询家庭情况列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/FamilyController/queryFamilyList")
    public void queryFamilyList(InputObject inputObject, OutputObject outputObject) {
        familyService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑员工家庭情况
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeFamily", value = "新增/编辑员工家庭情况", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Family.class)
    @RequestMapping("/post/FamilyController/writeFamily")
    public void writeFamily(InputObject inputObject, OutputObject outputObject) {
        familyService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id删除员工家庭情况信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteFamilyById", value = "根据id删除员工家庭情况信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/FamilyController/deleteFamilyById")
    public void deleteFamilyById(InputObject inputObject, OutputObject outputObject) {
        familyService.deleteById(inputObject, outputObject);
    }

}
