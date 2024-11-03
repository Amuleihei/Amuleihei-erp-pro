package com.skyeye.order.conroller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.order.entity.OrderComment;
import com.skyeye.order.service.OrderCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@Api(value = "商品订单评价管理", tags = "商品订单评价管理", modelName = "商品订单评价管理")
public class OrderCommentController {

    @Autowired
    private OrderCommentService orderCommentService;

    /**
     * 新增商品订单评价信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertOrderComment", value = "新增商品订单评价信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = OrderComment.class)
    @RequestMapping("/post/OrderCommentController/insertOrderComment")
    public void insertOrderComment(InputObject inputObject, OutputObject outputObject) {
        orderCommentService.createEntity(inputObject, outputObject);
    }

    /**
     * 根据id删除商品订单评价信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteOrderCommentById", value = "根据id删除商品订单评价信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/OrderCommentController/deleteOrderCommentById")
    public void deleteOrderCommentById(InputObject inputObject, OutputObject outputObject) {
        orderCommentService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id查询商品订单评价信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "selectOrderCommentById", value = "根据id查询商品订单评价信息", method = "POST", allUse = "2")
    @ApiImplicitParams({@ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/OrderCommentController/selectOrderCommentById")
    public void selectOrderCommentById(InputObject inputObject, OutputObject outputObject) {
        orderCommentService.selectById(inputObject, outputObject);
    }
}