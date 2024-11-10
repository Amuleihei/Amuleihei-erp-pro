package com.skyeye.order.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.enumeration.WhetherEnum;
import com.skyeye.order.entity.OrderItem;

import java.util.List;

public interface OrderItemService extends SkyeyeBusinessService<OrderItem> {


    void deleteByPerentIds(List<String> ids);

    List<OrderItem> selectByParentId(String id);

    List<OrderItem> queryListByStateAndOrderId(String orderId, Integer state);
}
