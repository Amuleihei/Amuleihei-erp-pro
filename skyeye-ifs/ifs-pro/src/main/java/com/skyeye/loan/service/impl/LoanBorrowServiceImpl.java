/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.loan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeFlowableServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.loan.dao.LoanBorrowDao;
import com.skyeye.loan.entity.LoanBorrow;
import com.skyeye.loan.service.LoanBorrowService;
import com.skyeye.loan.service.UserLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: LoanBorrowServiceImpl
 * @Description: 借款单服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/5 14:17
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "借款单", groupName = "借款单", flowable = true)
public class LoanBorrowServiceImpl extends SkyeyeFlowableServiceImpl<LoanBorrowDao, LoanBorrow> implements LoanBorrowService {

    @Autowired
    private UserLoanService userLoanService;

    @Override
    public QueryWrapper<LoanBorrow> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<LoanBorrow> queryWrapper = super.getQueryWrapper(commonPageInfo);
        // 我创建的
        queryWrapper.eq(MybatisPlusUtil.toColumns(LoanBorrow::getCreateId), InputObject.getLogParamsStatic().get("id").toString());
        return queryWrapper;
    }

    @Override
    public LoanBorrow selectById(String id) {
        LoanBorrow loanBorrow = super.selectById(id);
        iSysDictDataService.setDataMation(loanBorrow, LoanBorrow::getPayTypeId);
        return loanBorrow;
    }

    @Override
    public void approvalEndIsSuccess(LoanBorrow entity) {
        userLoanService.calcUserLoanPrice(entity.getCreateId(), entity.getPrice(), true);
    }

}
