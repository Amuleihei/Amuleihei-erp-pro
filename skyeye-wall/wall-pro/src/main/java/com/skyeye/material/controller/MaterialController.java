/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.material.entity.Material;
import com.skyeye.material.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MaterialController
 * @Description: 资料信息管理
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/9 14:31
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "资料管理", tags = "资料管理", modelName = "资料管理")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    /**
     * 根据ID获取资料信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryMaterialById", value = "根据ID获取资料信息", method = "GET", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/MaterialController/queryMaterialById")
    public void queryMaterialById(InputObject inputObject, OutputObject outputObject) {
        materialService.selectById(inputObject, outputObject);
    }

    /**
     * 新增/编辑资料信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "saveOrUpdateMaterial", value = "新增/编辑资料信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Material.class)
    @RequestMapping("/post/MaterialController/saveOrUpdateMaterial")
    public void saveOrUpdateMaterial(InputObject inputObject, OutputObject outputObject) {
        materialService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据ID删除资料信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteMaterialById", value = "根据ID删除资料信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/MaterialController/deleteMaterialById")
    public void deleteMaterialById(InputObject inputObject, OutputObject outputObject) {
        materialService.deleteById(inputObject, outputObject);
    }
}