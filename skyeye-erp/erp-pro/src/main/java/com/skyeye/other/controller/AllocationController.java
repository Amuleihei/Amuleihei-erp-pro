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
import com.skyeye.other.entity.Allocation;
import com.skyeye.other.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AllocationController
 * @Description: 调拨单管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2019/10/16 15:32
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "调拨单", tags = "调拨单", modelName = "调拨单模块")
public class AllocationController {

    @Autowired
    private AllocationService allocationService;

    /**
     * 获取调拨单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "allocation001", value = "获取调拨单列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/AllocationController/queryAllocationList")
    public void queryAllocationList(InputObject inputObject, OutputObject outputObject) {
        allocationService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑调拨单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeAllocation", value = "新增/编辑调拨单", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = Allocation.class)
    @RequestMapping("/post/AllocationController/writeAllocation")
    public void writeAllocation(InputObject inputObject, OutputObject outputObject) {
        allocationService.saveOrUpdateEntity(inputObject, outputObject);
    }

}
