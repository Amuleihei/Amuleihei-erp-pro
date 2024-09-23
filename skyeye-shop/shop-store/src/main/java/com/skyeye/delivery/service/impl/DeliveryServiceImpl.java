package com.skyeye.delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.delivery.dao.DeliveryDao;
import com.skyeye.delivery.entity.Delivery;
import com.skyeye.delivery.service.DeliveryService;
import com.skyeye.level.entity.ShopMemberLevel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SkyeyeService(name = "快递公司管理", groupName = "快递公司管理")
public class DeliveryServiceImpl extends SkyeyeBusinessServiceImpl<DeliveryDao , Delivery > implements DeliveryService {


    @Override
    public void streamlineDeliveryList(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<Delivery> queryWrapper = new QueryWrapper<>();
        // 添加查询条件 enabled = "2"
        queryWrapper.eq(MybatisPlusUtil.toColumns(Delivery::getEnabled), "2");
        List<Delivery> list = list(queryWrapper);
        outputObject.setBeans(list);
        outputObject.settotal(list.size());
    }
}
