/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.seal.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.business.service.impl.SkyeyeErpOrderServiceImpl;
import com.skyeye.classenum.ErpOrderStateEnum;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.enumeration.FlowableStateEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.depot.classenum.DepotOutFromType;
import com.skyeye.depot.classenum.DepotOutState;
import com.skyeye.depot.classenum.DepotPutOutType;
import com.skyeye.depot.entity.DepotOut;
import com.skyeye.depot.service.DepotOutService;
import com.skyeye.entity.ErpOrderItem;
import com.skyeye.exception.CustomException;
import com.skyeye.material.classenum.MaterialInOrderType;
import com.skyeye.seal.classenum.SealOutLetFromType;
import com.skyeye.seal.dao.SalesOutLetDao;
import com.skyeye.seal.entity.SalesOrder;
import com.skyeye.seal.entity.SalesOutLet;
import com.skyeye.seal.service.SalesOrderService;
import com.skyeye.seal.service.SalesOutLetService;
import com.skyeye.seal.service.SalesReturnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: SalesOutLetServiceImpl
 * @Description: 销售出库单管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/8 21:19
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "销售出库单", groupName = "销售模块", flowable = true)
public class SalesOutLetServiceImpl extends SkyeyeErpOrderServiceImpl<SalesOutLetDao, SalesOutLet> implements SalesOutLetService {

    @Autowired
    private SalesOrderService salesOrderService;

    @Autowired
    private SalesReturnsService salesReturnsService;

    @Autowired
    private DepotOutService depotOutService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        // 设置销售订单
        salesOrderService.setOrderMationByFromId(beans, "fromId", "fromMation");
        return beans;
    }

    @Override
    public void validatorEntity(SalesOutLet entity) {
        entity.setOtherState(DepotOutState.NEED_OUT.getKey());
        checkMaterialNorms(entity, false);
    }

    @Override
    public void createPrepose(SalesOutLet entity) {
        super.createPrepose(entity);
        entity.setType(DepotPutOutType.OUT.getKey());
        entity.getErpOrderItemList().forEach(erpOrderItem -> {
            erpOrderItem.setMType(MaterialInOrderType.GENERAL.getKey());
        });
    }

    @Override
    public SalesOutLet selectById(String id) {
        SalesOutLet salesOutLet = super.selectById(id);
        if (salesOutLet.getFromTypeId() == SealOutLetFromType.SEAL_ORDER.getKey()) {
            // 销售订单
            salesOrderService.setDataMation(salesOutLet, SalesOutLet::getFromId);
        }
        return salesOutLet;
    }

    private void checkMaterialNorms(SalesOutLet entity, boolean setData) {
        if (StrUtil.isEmpty(entity.getFromId())) {
            return;
        }
        // 当前销售出库单的商品数量
        Map<String, Integer> orderNormsNum = entity.getErpOrderItemList().stream()
            .collect(Collectors.toMap(ErpOrderItem::getNormsId, ErpOrderItem::getOperNumber));
        // 获取已经下达销售出库单的商品信息
        Map<String, Integer> executeNum = calcMaterialNormsNumByFromId(entity.getFromId());
        List<String> inSqlNormsId = new ArrayList<>(executeNum.keySet());
        if (entity.getFromTypeId() == SealOutLetFromType.SEAL_ORDER.getKey()) {
            // 销售订单
            checkAndUpdateSalesOrderState(entity, setData, orderNormsNum, executeNum, inSqlNormsId);
        }
    }

    private void checkAndUpdateSalesOrderState(SalesOutLet entity, boolean setData, Map<String, Integer> orderNormsNum, Map<String, Integer> executeNum, List<String> inSqlNormsId) {
        SalesOrder salesOrder = salesOrderService.selectById(entity.getFromId());
        if (CollectionUtil.isEmpty(salesOrder.getErpOrderItemList())) {
            throw new CustomException("该销售订单下未包含商品.");
        }
        super.checkFromOrderMaterialNorms(salesOrder.getErpOrderItemList(), inSqlNormsId);
        // 获取已经下达销售退货单的商品信息
        Map<String, Integer> returnExecuteNum = salesReturnsService.calcMaterialNormsNumByFromId(entity.getFromId());
        // 来源单据的商品数量 - 当前单据的商品数量 - 已经出库的商品数量 - 已经退货的商品数量
        super.setOrCheckOperNumber(salesOrder.getErpOrderItemList(), setData, orderNormsNum, executeNum, returnExecuteNum);
        if (setData) {
            // 过滤掉剩余数量为0的商品
            List<ErpOrderItem> erpOrderItemList = salesOrder.getErpOrderItemList().stream()
                .filter(erpOrderItem -> erpOrderItem.getOperNumber() > 0).collect(Collectors.toList());
            // 如果该销售订单的商品已经全部生成了销售出库单/销售退货单，那说明已经完成了销售订单的内容
            if (CollectionUtil.isEmpty(erpOrderItemList)) {
                salesOrderService.editStateById(salesOrder.getId(), ErpOrderStateEnum.COMPLETED.getKey());
            } else {
                salesOrderService.editStateById(salesOrder.getId(), ErpOrderStateEnum.PARTIALLY_COMPLETED.getKey());
            }
        }
    }

    @Override
    public void approvalEndIsSuccess(SalesOutLet entity) {
        entity = selectById(entity.getId());
        // 修改来源单据信息
        checkMaterialNorms(entity, true);
    }

    @Override
    public void querySalesOutLetTransById(InputObject inputObject, OutputObject outputObject) {
        String id = inputObject.getParams().get("id").toString();
        SalesOutLet salesOutLet = selectById(id);
        // 该销售出库单下的已经下达仓库出库单(审核通过)的数量
        Map<String, Integer> depotNumMap = depotOutService.calcMaterialNormsNumByFromId(salesOutLet.getId());
        // 设置未下达商品数量-----销售出库单数量 - 已出库数量
        super.setOrCheckOperNumber(salesOutLet.getErpOrderItemList(), true, depotNumMap);
        // 过滤掉数量为0的商品信息
        salesOutLet.setErpOrderItemList(salesOutLet.getErpOrderItemList().stream()
            .filter(erpOrderItem -> erpOrderItem.getOperNumber() > 0).collect(Collectors.toList()));
        outputObject.setBean(salesOutLet);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void insertSalesOutLetToTurnDepot(InputObject inputObject, OutputObject outputObject) {
        DepotOut depotOut = inputObject.getParams(DepotOut.class);
        // 获取销售出库单状态
        SalesOutLet salesOutLet = selectById(depotOut.getId());
        if (ObjectUtil.isEmpty(salesOutLet)) {
            throw new CustomException("该数据不存在.");
        }
        // 审核通过的可以转到仓库出库单
        if (FlowableStateEnum.PASS.getKey().equals(salesOutLet.getState())) {
            String userId = inputObject.getLogParams().get("id").toString();
            depotOut.setFromId(depotOut.getId());
            depotOut.setFromTypeId(DepotOutFromType.SEAL_OUTLET.getKey());
            depotOut.setId(StrUtil.EMPTY);
            depotOutService.createEntity(depotOut, userId);
        } else {
            outputObject.setreturnMessage("状态错误，无法下达仓库出库单.");
        }
    }
}
