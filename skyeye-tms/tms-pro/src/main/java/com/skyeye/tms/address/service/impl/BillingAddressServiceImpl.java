/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tms.address.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.tms.address.dao.BillingAddressDao;
import com.skyeye.tms.address.entity.BillingAddress;
import com.skyeye.tms.address.service.BillingAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: BillingAddressServiceImpl
 * @Description: 计费地址服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/9 15:35
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "计费地址", groupName = "计费地址")
public class BillingAddressServiceImpl extends SkyeyeBusinessServiceImpl<BillingAddressDao, BillingAddress> implements BillingAddressService {

    @Override
    public void queryEnabledBillingAddressList(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<BillingAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(BillingAddress::getEnabled), EnableEnum.ENABLE_USING.getKey());
        List<BillingAddress> billingAddressList = list(queryWrapper);
        outputObject.setBeans(billingAddressList);
        outputObject.settotal(billingAddressList.size());
    }

}
