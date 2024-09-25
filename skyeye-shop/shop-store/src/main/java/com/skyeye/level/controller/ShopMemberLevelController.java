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

@RestController
@Api(value = "会员等级", tags = "会员等级", modelName = "会员等级")
public class ShopMemberLevelController {


    @Autowired
    private ShopMemberLevelService shopMemberLevelService;

    @ApiOperation(id = "queryMemberLevelList", value = "获取会员等级信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ShopMemberLevelController/queryMemberLevelList")
    public void queryMemberLevelList(InputObject inputObject, OutputObject outputObject) {
        shopMemberLevelService.queryPageList(inputObject, outputObject);
    }

    @ApiOperation(id = "writeMemberLevel", value = "添加/编辑会员等级", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = ShopMemberLevel.class)
    @RequestMapping("/post/ShopMemberLevelController/writeMemberLevel")
    public void writeMemberLevel(InputObject inputObject, OutputObject outputObject) {
        shopMemberLevelService.saveOrUpdateEntity(inputObject, outputObject);
    }

    @ApiOperation(id = "deleteMemberLevelById", value = "根据id删除会员级别信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({@ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ShopMemberLevelController/deleteMemberLevelById")
    public void deleteMemberLeveById(InputObject inputObject, OutputObject outputObject) {
        shopMemberLevelService.deleteById(inputObject, outputObject);
    }

    @ApiOperation(id = "getMemberLevel", value = "根据ID获取会员等级", method = "GET", allUse = "2")
    @ApiImplicitParams({@ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ShopMemberLevelController/getMemberLevel")
    public void queryStoreByIds(InputObject inputObject, OutputObject outputObject) {
        shopMemberLevelService.selectById(inputObject, outputObject);
    }

    @ApiOperation(id = "memberLevelListAllSimple", value = "获取精简的会员等级信息，主要用于下拉列表", method = "GET", allUse = "2")
    @RequestMapping("/post/ShopMemberLevelController/memberLevelListAllSimple")
    public void streamlineMemberLevelList(InputObject inputObject, OutputObject outputObject) {
        shopMemberLevelService.streamlineMemberLevelList(inputObject, outputObject);
    }

    @ApiOperation(id = "memberLevelList", value = "获得会员等级列表", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "name", name = "name", value = "等级名称"),
        @ApiImplicitParam(id = "enabled", name = "enabled", value = "状态")})
    @RequestMapping("/post/ShopMemberLevelController/memberLevelList")
    public void memberLevelList(InputObject inputObject, OutputObject outputObject) {
        shopMemberLevelService.memberLevelList(inputObject, outputObject);
    }


}
