package com.skyeye.adsense.controller;

import com.skyeye.adsense.entity.Adsense;
import com.skyeye.adsense.service.AdsenseService;
import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(value = "广告位管理", tags = "广告位管理", modelName = "广告位管理")
public class AdsenseController {

    @Autowired
    private AdsenseService adsenseService;

    /**
     * 新增/编辑广告位管理信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAdsense", value = "分页查询快递公司信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/DeliveryController/queryAdsense")
    public void queryMemberLevelList(InputObject inputObject, OutputObject outputObject) {
        adsenseService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑广告位管理信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeAdsense", value = "新增/编辑广告位管理信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Adsense.class)
    @RequestMapping("/post/AdsenseController/writeAdsense")
    public void writeAdsense(InputObject inputObject, OutputObject outputObject) {
        adsenseService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 批量删除广告位管理信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteAdsenseByIds", value = "批量删除广告位管理信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "ids", name = "ids", value = "主键id列表，多个id用逗号分隔", required = "required")})
    @RequestMapping("/post/AdsenseController/deleteAdsenseByIds")
    public void deleteAdsenseByIds(InputObject inputObject, OutputObject outputObject) {
        adsenseService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取广告位管理信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAdsenseList", value = "获取广告位管理信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "name", name = "name", value = "标题"),
            @ApiImplicitParam(id = "enabled", name = "enabled", value = "状态1是0否")})
    @RequestMapping("/post/AdsenseController/queryAdsenseList")
    public void queryAdsenseList(InputObject inputObject, OutputObject outputObject) {
        adsenseService.queryList(inputObject, outputObject);
    }

    /**
     * 根据id获取广告位管理信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "selectAdsenseById", value = "根据id获取广告位管理信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AdsenseController/selectAdsenseById")
    public void selectAdsenseById(InputObject inputObject, OutputObject outputObject) {
        adsenseService.selectById(inputObject, outputObject);
    }

    /**
     * 获取精简的广告位管理信息，主要用于下拉列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "adsenseListAllSimple",value = "获取状态正常的精简的广告位管理信息，主要用于下拉列表",method = "GET",allUse = "2")
    @RequestMapping("/post/AdsenseController/adsenseListAllSimple")
    public void streamlineMemberLevelList(InputObject inputObject, OutputObject outputObject) {
        adsenseService.streamlineMemberLevelList(inputObject,outputObject);
    }
}
