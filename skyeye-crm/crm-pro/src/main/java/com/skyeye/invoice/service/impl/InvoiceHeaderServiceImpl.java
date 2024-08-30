/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.invoice.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeTeamAuthServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.invoice.classenum.CrmInvoiceHeaderAuthEnum;
import com.skyeye.invoice.dao.InvoiceHeaderDao;
import com.skyeye.invoice.entity.InvoiceHeader;
import com.skyeye.invoice.service.InvoiceHeaderService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: InvoiceHeaderServiceImpl
 * @Description: 发票抬头服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/3 14:36
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "发票抬头管理", groupName = "发票抬头管理", teamAuth = true)
public class InvoiceHeaderServiceImpl extends SkyeyeTeamAuthServiceImpl<InvoiceHeaderDao, InvoiceHeader> implements InvoiceHeaderService {

    @Override
    public Class getAuthEnumClass() {
        return CrmInvoiceHeaderAuthEnum.class;
    }

    @Override
    public List<String> getAuthPermissionKeyList() {
        return Arrays.asList(CrmInvoiceHeaderAuthEnum.ADD.getKey(), CrmInvoiceHeaderAuthEnum.EDIT.getKey(), CrmInvoiceHeaderAuthEnum.DELETE.getKey());
    }

    @Override
    public QueryWrapper<InvoiceHeader> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<InvoiceHeader> queryWrapper = super.getQueryWrapper(commonPageInfo);
        queryWrapper.eq(MybatisPlusUtil.toColumns(InvoiceHeader::getObjectId), commonPageInfo.getObjectId());
        return queryWrapper;
    }

    @Override
    public void queryInvoiceHeaderByObjectId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String objectId = map.get("objectId").toString();
        if (StrUtil.isEmpty(objectId)) {
            return;
        }
        QueryWrapper<InvoiceHeader> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(InvoiceHeader::getObjectId), objectId);
        List<InvoiceHeader> invoiceHeaders = list(queryWrapper);
        outputObject.setBeans(invoiceHeaders);
        outputObject.settotal(invoiceHeaders.size());
    }
}
