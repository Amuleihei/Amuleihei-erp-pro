/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.level.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.level.entity.ShopMemberLevel;
import com.skyeye.level.service.ShopMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ShopMemberLevelController
 * @Description: 会员等级控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/4 10:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "会员等级", tags = "会员等级", modelName = "会员等级")
public class ShopMemberLevelController {


    @Autowired
    private ShopMemberLevelService shopMemberLevelService;

    /**
     * 分页获取会员等级
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryMemberLevel", value = "分页获取会员等级信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ShopMemberLevelController/queryMemberLevel")
    public void queryMemberLevelList(InputObject inputObject, OutputObject outputObject) {
        shopMemberLevelService.queryPageList(inputObject, outputObject);
    }

    /**
     * 编辑会员等级信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeMemberLevel", value = "添加/编辑会员等级", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = ShopMemberLevel.class)
    @RequestMapping("/post/ShopMemberLevelController/writeMemberLevel")
    public void writeMemberLevel(InputObject inputObject, OutputObject outputObject) {
        shopMemberLevelService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id删除会员等级信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteMemberLevelById", value = "根据id删除会员级别信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({@ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ShopMemberLevelController/deleteMemberLevelById")
    public void deleteMemberLeveById(InputObject inputObject, OutputObject outputObject) {
        shopMemberLevelService.deleteById(inputObject, outputObject);
    }

    /**
     * 批量删除快递运费费用模版信息[ids]
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteMemberLeveByIds", value = "批量删除会员等级信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "ids", name = "ids", value = "主键id列表，多个id用逗号分隔", required = "required")})
    @RequestMapping("/post/ShopMemberLevelController/deleteMemberLeveByIds")
    public void deleteMemberLeveByIds(InputObject inputObject, OutputObject outputObject) {
        shopMemberLevelService.deleteByIds(inputObject, outputObject);
    }

    /**
     * 根据ID获取会员等级
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "getMemberLevel", value = "根据ID获取会员等级", method = "GET", allUse = "2")
    @ApiImplicitParams({@ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ShopMemberLevelController/getMemberLevel")
    public void queryStoreByIds(InputObject inputObject, OutputObject outputObject) {
        shopMemberLevelService.selectById(inputObject, outputObject);
    }

    /**
     * 获得会员等级列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "memberLevelList", value = "获得会员等级列表", method = "POST", allUse = "0")
    @RequestMapping("/post/ShopMemberLevelController/memberLevelList")
    public void memberLevelList(InputObject inputObject, OutputObject outputObject) {
        shopMemberLevelService.queryList(inputObject, outputObject);
    }
}
