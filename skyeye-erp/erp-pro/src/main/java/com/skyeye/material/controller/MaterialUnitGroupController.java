/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.material.entity.unit.MaterialUnitGroup;
import com.skyeye.material.service.MaterialUnitGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MaterialUnitGroupController
 * @Description: 计量单位管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/9 10:15
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "计量单位", tags = "计量单位", modelName = "计量单位")
public class MaterialUnitGroupController {

    @Autowired
    private MaterialUnitGroupService materialUnitGroupService;

    /**
     * 获取计量单位列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "materialunit001", value = "获取计量单位列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/MaterialUnitGroupController/queryMaterialUnitList")
    public void queryMaterialUnitList(InputObject inputObject, OutputObject outputObject) {
        materialUnitGroupService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑计量单位
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeMaterialUnitMation", value = "新增/编辑计量单位", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = MaterialUnitGroup.class)
    @RequestMapping("/post/MaterialUnitGroupController/writeMaterialUnitMation")
    public void writeMaterialUnitMation(InputObject inputObject, OutputObject outputObject) {
        materialUnitGroupService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除计量单位
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "materialunit003", value = "删除计量单位", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "计量单位组id", required = "required")})
    @RequestMapping("/post/MaterialUnitGroupController/deleteMaterialUnitMationById")
    public void deleteMaterialUnitMationById(InputObject inputObject, OutputObject outputObject) {
        materialUnitGroupService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取所有的计量单位
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "materialunit006", value = "获取所有的计量单位", method = "GET", allUse = "2")
    @RequestMapping("/post/MaterialUnitGroupController/queryAllMaterialUnitList")
    public void queryAllMaterialUnitList(InputObject inputObject, OutputObject outputObject) {
        materialUnitGroupService.queryAllMaterialUnitList(inputObject, outputObject);
    }

}
