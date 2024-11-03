package com.skyeye.order.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.enumeration.WhetherEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.erp.service.IMaterialNormsService;
import com.skyeye.erp.service.IMaterialService;
import com.skyeye.exception.CustomException;
import com.skyeye.order.dao.OrderCommentDao;
import com.skyeye.order.entity.OrderComment;
import com.skyeye.order.entity.OrderItem;
import com.skyeye.order.enums.OrderCommentType;
import com.skyeye.order.enums.ShopOrderCommentState;
import com.skyeye.order.service.OrderCommentService;
import com.skyeye.order.service.OrderItemService;
import com.skyeye.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@SkyeyeService(name = "商品订单评价管理", groupName = "商品订单评价管理")
public class OrderCommentServiceImpl extends SkyeyeBusinessServiceImpl<OrderCommentDao, OrderComment> implements OrderCommentService {

    @Autowired
    private IMaterialService iMaterialService;

    @Autowired
    private IMaterialNormsService iMaterialNormsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Override
    public void validatorEntity(OrderComment orderComment) {
        if (orderComment.getType() == OrderCommentType.MERCHANT.getKey()) {
            if (StrUtil.isEmpty(orderComment.getParentId())) {
                throw new CustomException("商家回复评价，父级评价id不能为空.");
            }
        }
        if (orderComment.getType() == OrderCommentType.CUSTOMERFiRST.getKey() ||
            orderComment.getType() == OrderCommentType.CUSTOMERLATER.getKey()) {
            if (StrUtil.isNotEmpty(orderComment.getParentId())) {
                throw new CustomException("客户的评价无需父级id");
            }
        }
    }

    @Override
    public void createPostpose(OrderComment orderComment, String userId) {
        List<OrderItem> orderItemList = orderItemService.queryListByStateAndOrderId(orderComment.getOrderId(), WhetherEnum.DISABLE_USING.getKey());
        if (CollectionUtil.isNotEmpty(orderItemList)) {
            orderService.updateCommonState(orderComment.getOrderId(), ShopOrderCommentState.PORTION.getKey());
        } else {
            orderService.updateCommonState(orderComment.getOrderId(), ShopOrderCommentState.FINISHED.getKey());
        }
    }

    @Override
    public OrderComment selectById(String id) {
        OrderComment orderComment = super.getDataFromDb(id);
        if (ObjectUtil.isEmpty(orderComment)) {
            throw new CustomException("信息不存在");
        }
        iMaterialService.setDataMation(orderComment, OrderComment::getMaterialId);
        iMaterialNormsService.setDataMation(orderComment, OrderComment::getNormsId);
        return orderComment;
    }

    @Override
    public void queryMyOrderComment(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<OrderComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(OrderComment::getCreateId), inputObject.getLogParams().get("id"));
        queryWrapper.orderByDesc(MybatisPlusUtil.toColumns(OrderComment::getCreateTime));
        List<OrderComment> list = list(queryWrapper);
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        iMaterialService.setDataMation(list, OrderComment::getMaterialId);
        iMaterialNormsService.setDataMation(list, OrderComment::getNormsId);
        outputObject.setBean(list);
        outputObject.settotal(list.size());
    }

    @Override
    public void queryOrderCommentByMaterialId(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<OrderComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(OrderComment::getMaterialId), inputObject.getParams().get("materialId"));
        queryWrapper.orderByDesc(MybatisPlusUtil.toColumns(OrderComment::getCreateTime));
        List<OrderComment> list = list(queryWrapper);
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        iMaterialService.setDataMation(list, OrderComment::getMaterialId);
        iMaterialNormsService.setDataMation(list, OrderComment::getNormsId);
        outputObject.setBean(list);
        outputObject.settotal(list.size());
    }
}
