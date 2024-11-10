package com.skyeye.order.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.util.CalculationUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.coupon.enums.CouponUseState;
import com.skyeye.coupon.enums.PromotionDiscountType;
import com.skyeye.coupon.service.CouponUseService;
import com.skyeye.erp.service.IMaterialNormsService;
import com.skyeye.erp.service.IMaterialService;
import com.skyeye.exception.CustomException;
import com.skyeye.order.dao.OrderItemDao;
import com.skyeye.order.entity.Order;
import com.skyeye.order.entity.OrderItem;
import com.skyeye.order.enums.ShopOrderCommentState;
import com.skyeye.order.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@SkyeyeService(name = "商品订单子单项管理", groupName = "商品订单子单项管理")
public class OrderItemServiceImpl extends SkyeyeBusinessServiceImpl<OrderItemDao, OrderItem> implements OrderItemService {

    @Override
    public void deleteByPerentIds(List<String> ids) {
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(OrderItem::getParentId), ids);
        remove(queryWrapper);
    }

    @Override
    public List<OrderItem> selectByParentId(String parentId) {
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(OrderItem::getParentId), parentId);
        return list(queryWrapper);
    }

    @Override
    public List<OrderItem> queryListByStateAndOrderId(String orderId, Integer state) {
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(OrderItem::getParentId), orderId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(OrderItem::getCommentState), state);
        List<OrderItem> list = list(queryWrapper);
        return CollectionUtil.isEmpty(list) ? new ArrayList<>() : list;
    }
}