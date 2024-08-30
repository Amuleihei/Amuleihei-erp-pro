/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.contract.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.contract.entity.Contract;
import com.skyeye.contract.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ContractController
 * @Description: 员工合同信息管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/5/17 7:56
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "员工合同信息", tags = "员工合同信息", modelName = "员工合同信息")
public class ContractController {

    @Autowired
    private ContractService contractService;

    /**
     * 查询合同列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryContractList", value = "查询合同列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ContractController/queryContractList")
    public void queryContractList(InputObject inputObject, OutputObject outputObject) {
        contractService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑员工合同信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeContract", value = "新增/编辑员工合同信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Contract.class)
    @RequestMapping("/post/ContractController/writeContract")
    public void writeContract(InputObject inputObject, OutputObject outputObject) {
        contractService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id删除员工合同信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteContractById", value = "根据id删除员工合同信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ContractController/deleteContractById")
    public void deleteContractById(InputObject inputObject, OutputObject outputObject) {
        contractService.deleteById(inputObject, outputObject);
    }

}
