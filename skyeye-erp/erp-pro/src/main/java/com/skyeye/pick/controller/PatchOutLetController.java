/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pick.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.depot.entity.DepotOut;
import com.skyeye.pick.entity.PatchOutLet;
import com.skyeye.pick.service.PatchOutLetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: PatchOutLetController
 * @Description: 补料出库单控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/26 20:42
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "补料出库单", tags = "补料出库单", modelName = "物料单")
public class PatchOutLetController {

    @Autowired
    private PatchOutLetService patchOutLetService;

    /**
     * 获取补料出库单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryPatchOutLetList", value = "获取补料出库单列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/PatchOutLetController/queryPatchOutLetList")
    public void queryPatchOutLetList(InputObject inputObject, OutputObject outputObject) {
        patchOutLetService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑补料出库单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writePatchOutLet", value = "新增/编辑补料出库单", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = PatchOutLet.class)
    @RequestMapping("/post/PatchOutLetController/writePatchOutLet")
    public void writePatchOutLet(InputObject inputObject, OutputObject outputObject) {
        patchOutLetService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 转仓库出库单时，根据id查询补料出库信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryPatchOutLetTransById", value = "转仓库出库单时，根据id查询补料出库信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/PatchOutLetController/queryPatchOutLetTransById")
    public void queryPatchOutLetTransById(InputObject inputObject, OutputObject outputObject) {
        patchOutLetService.queryPatchOutLetTransById(inputObject, outputObject);
    }

    /**
     * 补料出库单信息转仓库出库单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertPatchOutLetToTurnDepot", value = "补料出库单信息转仓库出库单", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = DepotOut.class, value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/PatchOutLetController/insertPatchOutLetToTurnDepot")
    public void insertPatchOutLetToTurnDepot(InputObject inputObject, OutputObject outputObject) {
        patchOutLetService.insertPatchOutLetToTurnDepot(inputObject, outputObject);
    }

}
