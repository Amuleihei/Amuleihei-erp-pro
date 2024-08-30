/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.afterseal.controller;

import com.skyeye.afterseal.entity.SealSign;
import com.skyeye.afterseal.service.SealSignService;
import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SealSignController
 * @Description: 售后服务故障信息控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/10 13:14
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@RestController
@Api(value = "工人签到信息", tags = "工人签到信息", modelName = "售后工单")
public class SealSignController {

    @Autowired
    private SealSignService sealSignService;

    /**
     * 获取工人签到信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "querySealSignList", value = "获取工人签到信息列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/SealSignController/querySealSignList")
    public void querySealSignList(InputObject inputObject, OutputObject outputObject) {
        sealSignService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增工人签到信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertSealSign", value = "新增工人签到信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = SealSign.class)
    @RequestMapping("/post/SealSignController/insertSealSign")
    public void insertSealSign(InputObject inputObject, OutputObject outputObject) {
        sealSignService.createEntity(inputObject, outputObject);
    }

}
