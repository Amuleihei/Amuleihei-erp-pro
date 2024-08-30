/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.other.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.other.entity.AssemblySheet;
import com.skyeye.other.service.AssemblySheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AssemblySheetController
 * @Description: 组装单管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2019/10/16 15:32
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "组装单", tags = "组装单", modelName = "组装单模块")
public class AssemblySheetController {

    @Autowired
    private AssemblySheetService assemblySheetService;

    /**
     * 获取组装单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "assemblysheet001", value = "获取组装单列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/AssemblySheetController/queryAssemblySheetList")
    public void queryAssemblySheetList(InputObject inputObject, OutputObject outputObject) {
        assemblySheetService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑组装单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeAssemblySheet", value = "新增/编辑组装单", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = AssemblySheet.class)
    @RequestMapping("/post/AssemblySheetController/writeAssemblySheet")
    public void writeAssemblySheet(InputObject inputObject, OutputObject outputObject) {
        assemblySheetService.saveOrUpdateEntity(inputObject, outputObject);
    }

}
