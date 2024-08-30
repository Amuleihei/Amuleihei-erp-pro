/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.inventory.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.inventory.service.InventoryChildCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: InventoryChildCodeController
 * @Description: 盘点任务表-子单据关联的编码控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/18 17:20
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "盘点任务表-子单据关联的编码", tags = "盘点任务表-子单据关联的编码", modelName = "盘点任务单")
public class InventoryChildCodeController {

    @Autowired
    private InventoryChildCodeService inventoryChildCodeService;

    /**
     * 获取盘点任务子单据下的编码信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryInventoryChildCodeList", value = "获取盘点任务子单据下的编码信息列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/InventoryChildCodeController/queryInventoryChildCodeList")
    public void queryInventoryChildCodeList(InputObject inputObject, OutputObject outputObject) {
        inventoryChildCodeService.queryPageList(inputObject, outputObject);
    }

}
