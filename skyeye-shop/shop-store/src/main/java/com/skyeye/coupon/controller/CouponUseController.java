package com.skyeye.coupon.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.coupon.entity.CouponUse;
import com.skyeye.coupon.service.CouponUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "优惠券领取信息管理", tags = "优惠券领取信息管理", modelName = "优惠券领取信息管理")
public class CouponUseController {

    @Autowired
    private CouponUseService couponUseService;

    /**
     * 新增/编辑优惠券/模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeCouponUse", value = "新增/编辑优惠券领取信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CouponUse.class)
    @RequestMapping("/post/CouponUseController/writeCouponUse")
    public void writeCouponUse(InputObject inputObject, OutputObject outputObject) {
        couponUseService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 分页查询优惠券领取信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryCouponUsePageList", value = "分页查询优惠券领取信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/CouponUseController/queryCouponUsePageList")
    public void queryCouponUsePageList(InputObject inputObject, OutputObject outputObject) {
        couponUseService.queryPageList(inputObject, outputObject);
    }

    /**
     * 查询自己的优惠券领取信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryMyCouponUseByState", value = "根据状态查询自己的优惠券领取信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "state", name = "state", value = "优惠券状态")})
    @RequestMapping("/post/CouponUseController/queryMyCouponUseByState")
    public void queryMyCouponUseByState(InputObject inputObject, OutputObject outputObject) {
        couponUseService.queryList(inputObject, outputObject);
    }


    /**
     * 批量删除优惠券领取信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteCouponUseByIds", value = "批量删除优惠券领取信息", method = "POST", allUse = "2")
    @ApiImplicitParams({@ApiImplicitParam(id = "ids", name = "ids", value = "主键id列表，多个id用逗号隔开", required = "required")})
    @RequestMapping("/post/CouponUseController/deleteCouponUseByIds")
    public void deleteCouponUseByIds(InputObject inputObject, OutputObject outputObject) {
        couponUseService.deleteByIds(inputObject, outputObject);
    }

    /**
     * 根据id查询优惠券领取信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryCouponUseById", value = "根据id查询优惠券领取信息", method = "POST", allUse = "2")
    @ApiImplicitParams({@ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/CouponUseController/queryCouponUseById")
    public void queryCouponUseById(InputObject inputObject, OutputObject outputObject) {
        couponUseService.selectById(inputObject, outputObject);
    }
}