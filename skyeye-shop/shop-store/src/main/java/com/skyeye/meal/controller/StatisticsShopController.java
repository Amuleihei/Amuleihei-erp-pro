/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.meal.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.meal.service.StatisticsShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: StatisticsShopController
 * @Description: 商城统计控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/12 23:57
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "商城统计", tags = "商城统计", modelName = "商城模块")
public class StatisticsShopController {

    @Autowired
    private StatisticsShopService statisticsShopService;

    /**
     * 统计分析
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryStatisticsShop", value = "统计分析", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "areaId", name = "areaId", value = "区域id"),
        @ApiImplicitParam(id = "storeId", name = "storeId", value = "门店id"),
        @ApiImplicitParam(id = "natureId", name = "natureId", value = "性质id"),
        @ApiImplicitParam(id = "startTime", name = "startTime", value = "开始日期", required = "required"),
        @ApiImplicitParam(id = "endTime", name = "endTime", value = "结束日期", required = "required")})
    @RequestMapping("/post/StatisticsShopController/queryStatisticsShop")
    public void queryStatisticsShop(InputObject inputObject, OutputObject outputObject) {
        statisticsShopService.queryStatisticsShop(inputObject, outputObject);
    }

}
