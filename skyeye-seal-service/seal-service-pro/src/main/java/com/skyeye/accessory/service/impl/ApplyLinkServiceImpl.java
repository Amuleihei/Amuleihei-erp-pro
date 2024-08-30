/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.accessory.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.base.Joiner;
import com.skyeye.accessory.dao.ApplyLinkDao;
import com.skyeye.accessory.entity.ApplyLink;
import com.skyeye.accessory.service.ApplyLinkService;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeLinkDataServiceImpl;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.util.CalculationUtil;
import com.skyeye.erp.service.IMaterialNormsService;
import com.skyeye.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ApplyLinkServiceImpl
 * @Description: 配件申请单配件信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/17 17:01
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Service
@SkyeyeService(name = "配件申请单配件信息", groupName = "配件申领单管理", manageShow = false)
public class ApplyLinkServiceImpl extends SkyeyeLinkDataServiceImpl<ApplyLinkDao, ApplyLink> implements ApplyLinkService {

    @Autowired
    private IMaterialNormsService iMaterialNormsService;

    @Override
    public void checkLinkList(String pId, List<ApplyLink> beans) {
        List<String> checkIds = beans.stream().map(bean -> String.format(Locale.ROOT, "%s_%s", bean.getNormsId(), bean.getDepotId())).distinct().collect(Collectors.toList());
        if (checkIds.size() != beans.size()) {
            throw new CustomException("存在来源为相同仓库配件规格信息.");
        }
    }

    @Override
    public String calcOrderAllTotalPrice(List<ApplyLink> applyLinkList) {
        List<String> normsIds = applyLinkList.stream().map(ApplyLink::getNormsId).collect(Collectors.toList());
        Map<String, Map<String, Object>> normsMap = iMaterialNormsService.queryDataMationForMapByIds(Joiner.on(CommonCharConstants.COMMA_MARK).join(normsIds));
        String allPrice = "0";
        for (ApplyLink applyLink : applyLinkList) {
            // 子单据总价：使用数量 * 零售价
            Map<String, Object> norms = normsMap.get(applyLink.getNormsId());
            if (CollectionUtil.isEmpty(norms)) {
                throw new CustomException("数据中包含不存在的配件规格信息.");
            }
            applyLink.setUnitPrice(norms.get("retailPrice").toString());
            applyLink.setAllPrice(
                CalculationUtil.multiply(CommonNumConstants.NUM_TWO, String.valueOf(applyLink.getOperNumber()), applyLink.getUnitPrice()));
            // 计算主单总价
            allPrice = CalculationUtil.add(applyLink.getAllPrice(), allPrice);
        }
        return allPrice;
    }

}
