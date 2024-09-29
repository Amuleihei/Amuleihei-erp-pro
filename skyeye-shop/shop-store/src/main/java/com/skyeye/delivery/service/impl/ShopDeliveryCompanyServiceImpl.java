package com.skyeye.delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.delivery.dao.ShopDeliveryCompanyDao;
import com.skyeye.delivery.entity.ShopDeliveryCompany;
import com.skyeye.delivery.service.ShopDeliveryCompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SkyeyeService(name = "快递公司管理", groupName = "快递公司管理")
public class ShopDeliveryCompanyServiceImpl extends SkyeyeBusinessServiceImpl<ShopDeliveryCompanyDao, ShopDeliveryCompany> implements ShopDeliveryCompanyService {


    @Override
    public void streamlineDeliveryList(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<ShopDeliveryCompany> queryWrapper = new QueryWrapper<>();
        // 添加查询条件 enabled = "2"
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopDeliveryCompany::getEnabled), "2");
        List<ShopDeliveryCompany> list = list(queryWrapper);
        outputObject.setBeans(list);
        outputObject.settotal(list.size());
    }



}
