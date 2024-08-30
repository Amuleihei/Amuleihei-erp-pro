/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.request.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.CalculationUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.request.dao.PurchaseRequestFixedChildDao;
import com.skyeye.request.entity.PurchaseRequestFixedChild;
import com.skyeye.request.service.PurchaseRequestFixedChildService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName: PurchaseRequestFixedChildServiceImpl
 * @Description: 采购申请-定价子单据服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/26 14:45
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "采购申请-定价子单据", groupName = "采购申请", manageShow = false)
public class PurchaseRequestFixedChildServiceImpl extends SkyeyeBusinessServiceImpl<PurchaseRequestFixedChildDao, PurchaseRequestFixedChild> implements PurchaseRequestFixedChildService {

    @Override
    public void saveList(String parentId, List<PurchaseRequestFixedChild> beans) {
        deleteByParentId(parentId);
        if (CollectionUtil.isNotEmpty(beans)) {
            for (PurchaseRequestFixedChild purchaseRequestFixedChild : beans) {
                purchaseRequestFixedChild.setId(null);
                purchaseRequestFixedChild.setParentId(parentId);
            }
            createEntity(beans, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByParentId(String parentId) {
        QueryWrapper<PurchaseRequestFixedChild> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(PurchaseRequestFixedChild::getParentId), parentId);
        remove(queryWrapper);
    }

    @Override
    public List<PurchaseRequestFixedChild> selectByParentId(String parentId) {
        QueryWrapper<PurchaseRequestFixedChild> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(PurchaseRequestFixedChild::getParentId), parentId);
        List<PurchaseRequestFixedChild> list = list(queryWrapper);
        return list;
    }

    @Override
    public String calcOrderAllTotalPrice(List<PurchaseRequestFixedChild> purchaseRequestFixedChildList) {
        String totalPrice = "0";
        for (PurchaseRequestFixedChild purchaseRequestFixedChild : purchaseRequestFixedChildList) {
            // 计算子单据总价：单价 * 数量
            BigDecimal itemAllPrice = new BigDecimal(purchaseRequestFixedChild.getUnitPrice());
            itemAllPrice = itemAllPrice.multiply(new BigDecimal(purchaseRequestFixedChild.getOperNumber()));
            purchaseRequestFixedChild.setAllPrice(itemAllPrice.toString());

            // 计算子单据价税合计：含税单价 * 数量
            BigDecimal taxUnitPrice = new BigDecimal(purchaseRequestFixedChild.getTaxUnitPrice());
            taxUnitPrice = taxUnitPrice.multiply(new BigDecimal(purchaseRequestFixedChild.getOperNumber()));
            purchaseRequestFixedChild.setTaxLastMoney(taxUnitPrice.toString());
            totalPrice = CalculationUtil.add(totalPrice, purchaseRequestFixedChild.getTaxLastMoney());
        }
        return totalPrice;
    }
}
