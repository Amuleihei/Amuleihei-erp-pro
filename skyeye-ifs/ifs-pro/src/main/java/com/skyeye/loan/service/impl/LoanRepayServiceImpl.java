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
import com.skyeye.loan.dao.LoanRepayDao;
import com.skyeye.loan.entity.LoanRepay;
import com.skyeye.loan.service.LoanRepayService;
import com.skyeye.loan.service.UserLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: LoanRepayServiceImpl
 * @Description: 还款单服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/5 14:20
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "还款单", groupName = "还款单", flowable = true)
public class LoanRepayServiceImpl extends SkyeyeFlowableServiceImpl<LoanRepayDao, LoanRepay> implements LoanRepayService {

    @Autowired
    private UserLoanService userLoanService;

    @Override
    public QueryWrapper<LoanRepay> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<LoanRepay> queryWrapper = super.getQueryWrapper(commonPageInfo);
        // 我创建的
        queryWrapper.eq(MybatisPlusUtil.toColumns(LoanRepay::getCreateId), InputObject.getLogParamsStatic().get("id").toString());
        return queryWrapper;
    }

    @Override
    public LoanRepay selectById(String id) {
        LoanRepay loanRepay = super.selectById(id);
        iSysDictDataService.setDataMation(loanRepay, LoanRepay::getPayTypeId);
        return loanRepay;
    }

    @Override
    public void approvalEndIsSuccess(LoanRepay entity) {
        userLoanService.calcUserLoanPrice(entity.getCreateId(), entity.getPrice(), false);
    }

}
