/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.other.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.depot.entity.DepotOut;
import com.skyeye.other.entity.OtherOutLets;
import com.skyeye.other.service.OtherOutLetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: OtherOutLetsController
 * @Description: 其他出库单管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/8 21:07
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "其他出库单", tags = "其他出库单", modelName = "其他订单模块")
public class OtherOutLetsController {

    @Autowired
    private OtherOutLetsService otherOutLetsService;

    /**
     * 获取其他出库单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "otheroutlets001", value = "获取其他出库单列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/OtherOutLetsController/queryOtherOutLetsList")
    public void queryOtherOutLetsList(InputObject inputObject, OutputObject outputObject) {
        otherOutLetsService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑其他出库单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeOtherOutLets", value = "新增/编辑其他出库单", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = OtherOutLets.class)
    @RequestMapping("/post/OtherOutLetsController/writeOtherOutLets")
    public void writeOtherOutLets(InputObject inputObject, OutputObject outputObject) {
        otherOutLetsService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 转仓库出库单时，根据id查询其他出库单信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryOtherOutLetsTransById", value = "转仓库出库单时，根据id查询其他出库单信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/OtherOutLetsController/queryOtherOutLetsTransById")
    public void queryOtherOutLetsTransById(InputObject inputObject, OutputObject outputObject) {
        otherOutLetsService.queryOtherOutLetsTransById(inputObject, outputObject);
    }

    /**
     * 其他出库单信息转仓库出库单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertOtherOutLetsToTurnDepot", value = "其他出库单信息转仓库出库单", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = DepotOut.class, value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/OtherOutLetsController/insertOtherOutLetsToTurnDepot")
    public void insertOtherOutLetsToTurnDepot(InputObject inputObject, OutputObject outputObject) {
        otherOutLetsService.insertOtherOutLetsToTurnDepot(inputObject, outputObject);
    }

}
