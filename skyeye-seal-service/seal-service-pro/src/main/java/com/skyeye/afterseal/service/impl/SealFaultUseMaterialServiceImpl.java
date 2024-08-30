/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.afterseal.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.google.common.base.Joiner;
import com.skyeye.accessory.entity.ServiceUserStock;
import com.skyeye.accessory.service.ServiceUserStockService;
import com.skyeye.afterseal.dao.SealFaultUseMaterialDao;
import com.skyeye.afterseal.entity.SealFaultUseMaterial;
import com.skyeye.afterseal.service.SealFaultUseMaterialService;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeLinkDataServiceImpl;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.CalculationUtil;
import com.skyeye.erp.service.IMaterialNormsService;
import com.skyeye.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: SealFaultUseMaterialServiceImpl
 * @Description: 售后服务故障配件使用信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/12 21:37
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Service
@SkyeyeService(name = "售后服务故障配件使用信息", groupName = "售后工单", manageShow = false)
public class SealFaultUseMaterialServiceImpl extends SkyeyeLinkDataServiceImpl<SealFaultUseMaterialDao, SealFaultUseMaterial> implements SealFaultUseMaterialService {

    @Autowired
    private IMaterialNormsService iMaterialNormsService;

    @Autowired
    private ServiceUserStockService serviceUserStockService;

    @Override
    protected void checkLinkList(String pId, List<SealFaultUseMaterial> beans) {
        if (CollectionUtil.isEmpty(beans)) {
            return;
        }
        List<String> normsId = beans.stream().map(SealFaultUseMaterial::getNormsId).collect(Collectors.toList());
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        Map<String, ServiceUserStock> userStockMap = serviceUserStockService.queryUserStock(userId, normsId);
        beans.forEach(bean -> {
            ServiceUserStock serviceUserStock = userStockMap.get(bean.getNormsId());
            if (ObjectUtil.isEmpty(serviceUserStock)) {
                throw new CustomException("部分配件库存不足，请重新选择配件！");
            }
            if (bean.getOperNumber() > serviceUserStock.getStock()) {
                throw new CustomException("部分配件库存不足，请重新选择配件！");
            }
        });
    }

    @Override
    public String calcOrderAllTotalPrice(List<SealFaultUseMaterial> sealFaultUseMaterials) {
        String allPrice = "0";
        if (CollectionUtil.isEmpty(sealFaultUseMaterials)) {
            return allPrice;
        }
        List<String> normsIds = sealFaultUseMaterials.stream().map(SealFaultUseMaterial::getNormsId).collect(Collectors.toList());
        Map<String, Map<String, Object>> normsMap = iMaterialNormsService.queryDataMationForMapByIds(Joiner.on(CommonCharConstants.COMMA_MARK).join(normsIds));

        for (SealFaultUseMaterial sealFaultUseMaterial : sealFaultUseMaterials) {
            // 子单据总价：使用数量 * 零售价
            Map<String, Object> norms = normsMap.get(sealFaultUseMaterial.getNormsId());
            sealFaultUseMaterial.setUnitPrice(norms.get("retailPrice").toString());
            sealFaultUseMaterial.setAllPrice(
                CalculationUtil.multiply(CommonNumConstants.NUM_TWO, String.valueOf(sealFaultUseMaterial.getOperNumber()), sealFaultUseMaterial.getUnitPrice()));
            // 计算主单总价
            allPrice = CalculationUtil.add(CommonNumConstants.NUM_TWO, sealFaultUseMaterial.getAllPrice(), allPrice);
        }
        return allPrice;
    }

}
