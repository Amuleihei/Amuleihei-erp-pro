/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pick.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.enumeration.FlowableStateEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exception.CustomException;
import com.skyeye.machin.classenum.MachinPickStateEnum;
import com.skyeye.machin.service.MachinService;
import com.skyeye.pick.classenum.OutLetState;
import com.skyeye.pick.classenum.RequisitionOutLetFromType;
import com.skyeye.pick.dao.RequisitionMaterialDao;
import com.skyeye.pick.entity.RequisitionMaterial;
import com.skyeye.pick.entity.RequisitionOutLet;
import com.skyeye.pick.service.RequisitionMaterialService;
import com.skyeye.pick.service.RequisitionOutLetService;
import com.skyeye.util.ErpOrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: RequisitionMaterialServiceImpl
 * @Description: 领料申请单管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/27 12:50
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "领料单", groupName = "物料单", flowable = true)
public class RequisitionMaterialServiceImpl extends ErpPickServiceImpl<RequisitionMaterialDao, RequisitionMaterial> implements RequisitionMaterialService {

    @Autowired
    private MachinService machinService;

    @Autowired
    private RequisitionOutLetService requisitionOutLetService;

    @Override
    public void createPrepose(RequisitionMaterial entity) {
        super.createPrepose(entity);
        entity.setOtherState(OutLetState.NEED_OUTLET.getKey());
    }

    @Override
    public void approvalEndIsSuccess(RequisitionMaterial entity) {
        if (StrUtil.isNotEmpty(entity.getFromId())) {
            machinService.editPickStateById(entity.getFromId(), MachinPickStateEnum.PICKED.getKey());
        }
    }

    @Override
    public void queryRequisitionMaterialTransById(InputObject inputObject, OutputObject outputObject) {
        String id = inputObject.getParams().get("id").toString();
        RequisitionMaterial requisitionMaterial = selectById(id);
        // 该领料单下的已经下达领料出库单(审核通过)的数量
        Map<String, Integer> executeNum = requisitionOutLetService.calcMaterialNormsNumByFromId(requisitionMaterial.getId());
        // 设置未下达商品数量-----领料单数量 - 领料出库单数量
        requisitionMaterial.getPickChildList().forEach(pickChild -> {
            // 领料单数量 - 已经下达领料出库单的数量
            Integer surplusNum = ErpOrderUtil.checkOperNumber(pickChild.getNeedNum(), pickChild.getNormsId(), executeNum);
            pickChild.setNeedNum(surplusNum);
        });
        // 过滤掉数量为0的商品信息
        requisitionMaterial.setPickChildList(requisitionMaterial.getPickChildList().stream()
            .filter(erpOrderItem -> erpOrderItem.getNeedNum() > 0).collect(Collectors.toList()));
        outputObject.setBean(requisitionMaterial);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    @Override
    public void insertRequisitionMaterialToTurnOut(InputObject inputObject, OutputObject outputObject) {
        RequisitionOutLet requisitionOutLet = inputObject.getParams(RequisitionOutLet.class);
        // 获取领料单状态
        RequisitionMaterial requisitionMaterial = selectById(requisitionOutLet.getId());
        if (ObjectUtil.isEmpty(requisitionMaterial)) {
            throw new CustomException("该数据不存在.");
        }
        // 审核通过的可以转到领料出库单
        if (FlowableStateEnum.PASS.getKey().equals(requisitionMaterial.getState()) &&
            (requisitionMaterial.getOtherState() == OutLetState.NEED_OUTLET.getKey()
                || requisitionMaterial.getOtherState() == OutLetState.PARTIAL_OUTLET.getKey())) {
            String userId = inputObject.getLogParams().get("id").toString();
            requisitionOutLet.setFromId(requisitionOutLet.getId());
            requisitionOutLet.setFromTypeId(RequisitionOutLetFromType.REQUISITION_MATERIAL.getKey());
            requisitionOutLet.setId(StrUtil.EMPTY);
            requisitionOutLetService.createEntity(requisitionOutLet, userId);
        } else {
            outputObject.setreturnMessage("状态错误，无法下达领料出库单.");
        }
    }
}
