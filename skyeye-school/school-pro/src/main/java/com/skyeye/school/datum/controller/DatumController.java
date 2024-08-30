/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.datum.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.datum.entity.Datum;
import com.skyeye.school.datum.service.DatumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DatumController
 * @Description: 资料管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/2 10:47
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "资料管理", tags = "资料管理", modelName = "资料管理")
public class DatumController {

    @Autowired
    private DatumService datumService;

    /**
     * 根据科目表与班级表的关系id获取资料信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDatumListBySubjectClassesId", value = "根据科目表与班级表的关系id获取资料信息列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "subjectClassesId", name = "subjectClassesId", value = "科目表与班级表的关系id", required = "required")})
    @RequestMapping("/post/DatumController/queryDatumListBySubjectClassesId")
    public void queryDatumListBySubjectClassesId(InputObject inputObject, OutputObject outputObject) {
        datumService.queryDatumListBySubjectClassesId(inputObject, outputObject);
    }

  /**
     * 添加或修改资料
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeDatum", value = "新增/编辑资料信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean =Datum.class)
    @RequestMapping("/post/DatumController/writeDatum")
    public void writeDatum(InputObject inputObject, OutputObject outputObject) {
        datumService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 获取资料信息详情
     */
    @ApiOperation(id = "queryDatumById", value = "获取资料信息详情", method = "POST", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/DatumController/queryDatumById")
    public void queryDatumById(InputObject inputObject, OutputObject outputObject) {
        datumService.selectById(inputObject, outputObject);
    }

    /**
     * 删除资料信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteDatumById", value = "根据ID删除资料信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/DatumController/deleteDatumById")
    public void deleteDatumById(InputObject inputObject, OutputObject outputObject) {
        datumService.deleteById(inputObject, outputObject);
    }
}
