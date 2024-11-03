package com.skyeye.order.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.order.dao.OrderCommentDao;
import com.skyeye.order.entity.OrderComment;
import com.skyeye.order.service.OrderCommentService;
import org.springframework.stereotype.Service;

@Service
@SkyeyeService(name = "商品订单评价管理", groupName = "商品订单评价管理")
public class OrderCommentServiceImpl extends SkyeyeBusinessServiceImpl<OrderCommentDao, OrderComment> implements OrderCommentService {

    @Override
    public void createPostpose(OrderComment orderComment, String userId){
        
    }
}
