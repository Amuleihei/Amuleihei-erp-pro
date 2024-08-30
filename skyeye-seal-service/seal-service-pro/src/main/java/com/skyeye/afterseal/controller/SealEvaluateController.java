/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.afterseal.controller;

import com.skyeye.afterseal.entity.SealEvaluate;
import com.skyeye.afterseal.service.SealEvaluateService;
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
 * @ClassName: SealEvaluateController
 * @Description: 工单服务评价控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/15 18:20
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@RestController
@Api(value = "工单服务评价", tags = "工单服务评价", modelName = "售后工单")
public class SealEvaluateController {

    @Autowired
    private SealEvaluateService sealEvaluateService;

    /**
     * 获取工单服务评价列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "querySealEvaluateList", value = "获取工单服务评价列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/SealEvaluateController/querySealEvaluateList")
    public void querySealEvaluateList(InputObject inputObject, OutputObject outputObject) {
        sealEvaluateService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增工单服务评价
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertSealEvaluate", value = "新增工单服务评价", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = SealEvaluate.class)
    @RequestMapping("/post/SealEvaluateController/insertSealEvaluate")
    public void insertSealEvaluate(InputObject inputObject, OutputObject outputObject) {
        sealEvaluateService.createEntity(inputObject, outputObject);
    }

}
