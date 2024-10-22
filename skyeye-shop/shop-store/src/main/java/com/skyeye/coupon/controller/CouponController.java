package com.skyeye.coupon.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.coupon.entity.Coupon;
import com.skyeye.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "优惠券/模版信息管理", tags = "优惠券/模版信息管理", modelName = "优惠券/模版信息管理")
public class CouponController {

    @Autowired
    private CouponService couponService;

    /**
     * 新增/编辑优惠券/模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeCoupon", value = "新增/编辑优惠券/模版信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Coupon.class)
    @RequestMapping("/post/CouponController/writeCoupon")
    public void writeCoupon(InputObject inputObject, OutputObject outputObject) {
        couponService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 获取优惠券/模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryCouponList", value = "分页获取优惠券/模版信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/CouponController/queryCouponList")
    public void queryCouponList(InputObject inputObject, OutputObject outputObject) {
        couponService.queryPageList(inputObject, outputObject);
    }

    /**
     * 根据状态获取优惠券/模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryCouponListByState", value = "根据状态获取优惠券/模版信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "storeId", name = "storeId", value = "门店id", required = "required"),
        @ApiImplicitParam(id = "type", name = "type", value = "优惠券：0，优惠券模板：1，全部：为空")})
    @RequestMapping("/post/CouponController/queryCouponListByState")
    public void queryCouponListByState(InputObject inputObject, OutputObject outputObject) {
        couponService.queryCouponListByState(inputObject, outputObject);
    }

    /**
     * 根据id获取优惠券/模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryCouponById", value = "根据id获取优惠券/模版信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/CouponController/queryCouponById")
    public void queryCouponById(InputObject inputObject, OutputObject outputObject) {
        couponService.selectById(inputObject, outputObject);
    }

    /**
     * 根据id获取优惠券/模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteCouponById", value = "根据id删除优惠券/模版信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({@ApiImplicitParam(id = "ids", name = "ids", value = "主键id列表，多个主键用逗号隔开", required = "required")})
    @RequestMapping("/post/CouponController/deleteCouponById")
    public void deleteCouponById(InputObject inputObject, OutputObject outputObject) {
        couponService.deleteByIds(inputObject, outputObject);
    }
}