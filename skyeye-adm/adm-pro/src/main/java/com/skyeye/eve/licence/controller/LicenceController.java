/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.licence.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.licence.entity.Licence;
import com.skyeye.eve.licence.service.LicenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: LicenceController
 * @Description: 证照管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/24 22:55
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "证照管理", tags = "证照管理", modelName = "证照模块")
public class LicenceController {

    @Autowired
    private LicenceService licenceService;

    /**
     * 查询所有的证照
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "licence001", value = "查询所有的证照", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/LicenceController/queryLicenceList")
    public void queryLicenceList(InputObject inputObject, OutputObject outputObject) {
        licenceService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/修改证照信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeLicence", value = "新增/修改证照信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = Licence.class)
    @RequestMapping("/post/LicenceController/writeLicence")
    public void writeLicence(InputObject inputObject, OutputObject outputObject) {
        licenceService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id删除证照信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "licence003", value = "根据id删除证照信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/LicenceController/deleteLicenceById")
    public void deleteLicenceById(InputObject inputObject, OutputObject outputObject) {
        licenceService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取所有证照信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "licenceborrow008", value = "获取所有证照信息", method = "GET", allUse = "2")
    @RequestMapping("/post/LicenceController/queryAllLicenceList")
    public void queryAllLicenceList(InputObject inputObject, OutputObject outputObject) {
        licenceService.queryAllLicenceList(inputObject, outputObject);
    }

    /**
     * 获取我借用中的所有证照信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "licencerevert008", value = "获取我借用中的所有证照信息", method = "GET", allUse = "2")
    @RequestMapping("/post/LicenceController/queryMyRevertLicenceList")
    public void queryMyRevertLicenceList(InputObject inputObject, OutputObject outputObject) {
        licenceService.queryMyRevertLicenceList(inputObject, outputObject);
    }

    /**
     * 获取我借用中的证照列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryMyRevertLicencePageList", value = "获取我借用中的证照列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/LicenceController/queryMyRevertLicencePageList")
    public void queryMyRevertLicencePageList(InputObject inputObject, OutputObject outputObject) {
        licenceService.queryMyRevertLicencePageList(inputObject, outputObject);
    }

}
