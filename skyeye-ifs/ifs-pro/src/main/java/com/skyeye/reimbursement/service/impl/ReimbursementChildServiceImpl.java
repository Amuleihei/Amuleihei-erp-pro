/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.reimbursement.service.impl;

import com.skyeye.base.business.service.impl.SkyeyeLinkDataServiceImpl;
import com.skyeye.common.util.CalculationUtil;
import com.skyeye.reimbursement.dao.ReimbursementChildDao;
import com.skyeye.reimbursement.entity.ReimbursementChild;
import com.skyeye.reimbursement.service.ReimbursementChildService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ReimbursementChildServiceImpl
 * @Description: 报销订单子内容服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/4 16:23
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class ReimbursementChildServiceImpl extends SkyeyeLinkDataServiceImpl<ReimbursementChildDao, ReimbursementChild> implements ReimbursementChildService {

    @Override
    public String calcOrderAllTotalPrice(List<ReimbursementChild> orderItemList) {
        String totalPrice = "0";
        for (ReimbursementChild orderItem : orderItemList) {
            // 计算子单据总价：单价相加
            totalPrice = CalculationUtil.add(totalPrice, orderItem.getPrice());
        }
        return totalPrice;
    }

}
