/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.request.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.request.dao.PurchaseRequestInquiryChildDao;
import com.skyeye.request.entity.PurchaseRequestInquiryChild;
import com.skyeye.request.service.PurchaseRequestInquiryChildService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName: PurchaseRequestInquiryChildServiceImpl
 * @Description: 采购申请询价明细服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/22 11:32
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "采购申请询价明细", groupName = "采购申请", manageShow = false)
public class PurchaseRequestInquiryChildServiceImpl extends SkyeyeBusinessServiceImpl<PurchaseRequestInquiryChildDao, PurchaseRequestInquiryChild> implements PurchaseRequestInquiryChildService {

    @Override
    public void saveList(String parentId, List<PurchaseRequestInquiryChild> beans) {
        deleteByParentId(parentId);
        if (CollectionUtil.isNotEmpty(beans)) {
            for (PurchaseRequestInquiryChild purchaseRequestInquiryChild : beans) {
                purchaseRequestInquiryChild.setParentId(parentId);
                // 计算子单据总价：单价 * 数量
                BigDecimal itemAllPrice = new BigDecimal(purchaseRequestInquiryChild.getUnitPrice());
                itemAllPrice = itemAllPrice.multiply(new BigDecimal(purchaseRequestInquiryChild.getOperNumber()));
                purchaseRequestInquiryChild.setAllPrice(itemAllPrice.toString());

                // 计算子单据价税合计：含税单价 * 数量
                BigDecimal taxUnitPrice = new BigDecimal(purchaseRequestInquiryChild.getTaxUnitPrice());
                taxUnitPrice = taxUnitPrice.multiply(new BigDecimal(purchaseRequestInquiryChild.getOperNumber()));
                purchaseRequestInquiryChild.setTaxLastMoney(taxUnitPrice.toString());

            }
            createEntity(beans, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByParentId(String parentId) {
        QueryWrapper<PurchaseRequestInquiryChild> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(PurchaseRequestInquiryChild::getParentId), parentId);
        remove(queryWrapper);
    }

    @Override
    public List<PurchaseRequestInquiryChild> selectByParentId(String parentId) {
        QueryWrapper<PurchaseRequestInquiryChild> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(PurchaseRequestInquiryChild::getParentId), parentId);
        List<PurchaseRequestInquiryChild> list = list(queryWrapper);
        return list;
    }
}
