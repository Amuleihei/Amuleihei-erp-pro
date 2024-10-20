/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.purchase.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.business.classenum.OrderItemQualityInspectionType;
import com.skyeye.business.classenum.OrderQualityInspectionType;
import com.skyeye.business.service.impl.SkyeyeErpOrderServiceImpl;
import com.skyeye.classenum.ErpOrderStateEnum;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.enumeration.FlowableStateEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.contract.classenum.SupplierContractChildStateEnum;
import com.skyeye.contract.entity.SupplierContract;
import com.skyeye.contract.entity.SupplierContractChild;
import com.skyeye.contract.service.SupplierContractService;
import com.skyeye.depot.classenum.DepotPutOutType;
import com.skyeye.entity.ErpOrderItem;
import com.skyeye.exception.CustomException;
import com.skyeye.material.classenum.MaterialFromType;
import com.skyeye.material.classenum.MaterialInOrderType;
import com.skyeye.production.classenum.ProductionPlanPurchaseState;
import com.skyeye.production.entity.ProductionPlan;
import com.skyeye.production.entity.ProductionPlanChild;
import com.skyeye.production.service.ProductionPlanService;
import com.skyeye.purchase.classenum.*;
import com.skyeye.purchase.dao.PurchaseOrderDao;
import com.skyeye.purchase.entity.PurchaseDelivery;
import com.skyeye.purchase.entity.PurchaseOrder;
import com.skyeye.purchase.entity.PurchasePut;
import com.skyeye.purchase.entity.PurchaseReturn;
import com.skyeye.purchase.service.PurchaseDeliveryService;
import com.skyeye.purchase.service.PurchaseOrderService;
import com.skyeye.purchase.service.PurchasePutService;
import com.skyeye.purchase.service.PurchaseReturnsService;
import com.skyeye.util.ErpOrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: PurchaseOrderServiceImpl
 * @Description: 采购订单管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:45
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "采购订单", groupName = "采购模块", flowable = true)
public class PurchaseOrderServiceImpl extends SkyeyeErpOrderServiceImpl<PurchaseOrderDao, PurchaseOrder> implements PurchaseOrderService {

    @Autowired
    private PurchasePutService purchasePutService;

    @Autowired
    private PurchaseDeliveryService purchaseDeliveryService;

    @Autowired
    private PurchaseReturnsService purchaseReturnsService;

    @Autowired
    private SupplierContractService supplierContractService;

    @Autowired
    private ProductionPlanService productionPlanService;

    @Override
    public QueryWrapper<PurchaseOrder> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<PurchaseOrder> queryWrapper = super.getQueryWrapper(commonPageInfo);
        if (StrUtil.isNotEmpty(commonPageInfo.getHolderId())) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(PurchaseOrder::getHolderId), commonPageInfo.getHolderId());
        }
        if (StrUtil.isNotEmpty(commonPageInfo.getFromId())) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(PurchaseOrder::getFromId), commonPageInfo.getFromId());
        }
        return queryWrapper;
    }

    @Override
    public void validatorEntity(PurchaseOrder entity) {
        checkMaterialNorms(entity, false);
    }

    @Override
    public void createPrepose(PurchaseOrder entity) {
        super.createPrepose(entity);
        entity.setType(DepotPutOutType.PUT.getKey());
        setOtherMation(entity);
    }

    @Override
    public void updatePrepose(PurchaseOrder entity) {
        super.updatePrepose(entity);
        setOtherMation(entity);
    }

    @Override
    public List<Map<String, Object>> queryPageData(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageData(inputObject);
        supplierContractService.setContractMationByFromId(beans, "fromId", "fromMation");
        productionPlanService.setOrderMationByFromId(beans, "fromId", "fromMation");
        return beans;
    }

    private static void setOtherMation(PurchaseOrder entity) {
        // 设置质检类型
        Integer qualityInspection = OrderQualityInspectionType.NOT_NEED_QUALITYINS_INS.getKey();
        for (ErpOrderItem erpOrderItem : entity.getErpOrderItemList()) {
            qualityInspection = setQualityInspection(erpOrderItem, qualityInspection);
            erpOrderItem.setMType(MaterialInOrderType.GENERAL.getKey());
        }
        entity.setQualityInspection(qualityInspection);
        // 设置到货状态
        if (qualityInspection == OrderQualityInspectionType.NEED_QUALITYINS_INS.getKey()) {
            // 如果需要质检，则需要先下【到货单】
            entity.setOtherState(OrderArrivalState.NEED_ARRIVAL.getKey());
        } else {
            entity.setOtherState(OrderArrivalState.NOT_NEED_ARRIVAL.getKey());
        }
    }

    private void checkMaterialNorms(PurchaseOrder entity, boolean setData) {
        if (StrUtil.isEmpty(entity.getFromId())) {
            return;
        }
        // 当前采购订单的商品数量
        Map<String, Integer> orderNormsNum = entity.getErpOrderItemList().stream()
            .collect(Collectors.toMap(ErpOrderItem::getNormsId, ErpOrderItem::getOperNumber));
        // 获取已经下达采购订单的商品信息
        Map<String, Integer> executeNum = calcMaterialNormsNumByFromId(entity.getFromId());
        List<String> inSqlNormsId = new ArrayList<>(executeNum.keySet());
        if (entity.getFromTypeId() == PurchaseOrderFromType.SUPPLIER_CONTRACT.getKey()) {
            // 采购合同
            checkAndUpdateSupplierContractState(entity, setData, orderNormsNum, executeNum, inSqlNormsId);
        } else if (entity.getFromTypeId() == PurchaseOrderFromType.DELIVERY_PLAN.getKey()) {
            // 到货计划
            checkAndUpdateDeliveryPlanState(entity, setData, orderNormsNum, executeNum, inSqlNormsId);
        }
    }

    private void checkAndUpdateSupplierContractState(PurchaseOrder entity, boolean setData, Map<String, Integer> orderNormsNum, Map<String, Integer> executeNum, List<String> inSqlNormsId) {
        SupplierContract supplierContract = supplierContractService.selectById(entity.getFromId());
        if (CollectionUtil.isEmpty(supplierContract.getSupplierContractChildList())) {
            throw new CustomException("该采购合同下未包含商品.");
        }
        List<String> fromNormsIds = supplierContract.getSupplierContractChildList().stream()
            .map(SupplierContractChild::getNormsId).collect(Collectors.toList());

        super.checkIdFromOrderMaterialNorms(fromNormsIds, inSqlNormsId);
        supplierContract.getSupplierContractChildList().forEach(supplierContractChild -> {
            // 合同数量 - 当前采购订单的数量 - 已经下达采购订单的数量
            Integer surplusNum = ErpOrderUtil.checkOperNumber(supplierContractChild.getOperNumber(), supplierContractChild.getNormsId(),
                orderNormsNum, executeNum);
            if (setData) {
                supplierContractChild.setOperNumber(surplusNum);
            }
        });
        if (setData) {
            // 过滤掉剩余数量为0的商品
            List<SupplierContractChild> supplierContractChildList = supplierContract.getSupplierContractChildList().stream()
                .filter(supplierContractChild -> supplierContractChild.getOperNumber() > 0).collect(Collectors.toList());
            // 如果该合同的商品已经全部下达订单，那说明已经完成了合同的内容
            if (CollectionUtil.isEmpty(supplierContractChildList)) {
                supplierContractService.editChildState(supplierContract.getId(), SupplierContractChildStateEnum.ALL_ISSUED.getKey());
            } else {
                supplierContractService.editChildState(supplierContract.getId(), SupplierContractChildStateEnum.PARTIAL_RELEASE.getKey());
            }
        }
    }

    private void checkAndUpdateDeliveryPlanState(PurchaseOrder entity, boolean setData, Map<String, Integer> orderNormsNum, Map<String, Integer> executeNum, List<String> inSqlNormsId) {
        ProductionPlan productionPlan = productionPlanService.selectById(entity.getFromId());
        // 只查询外购商品
        List<ProductionPlanChild> productionPlanChildList = productionPlan.getProductionPlanChildList().stream()
            .filter(productionPlanChild -> productionPlanChild.getMaterialMation().getFromType() == MaterialFromType.OUTSOURCING.getKey())
            .collect(Collectors.toList());
        if (CollectionUtil.isEmpty(productionPlanChildList)) {
            throw new CustomException("该到货计划单下未包含外购商品.");
        }
        List<String> fromNormsIds = productionPlanChildList.stream()
            .map(ProductionPlanChild::getNormsId).collect(Collectors.toList());

        super.checkIdFromOrderMaterialNorms(fromNormsIds, inSqlNormsId);
        productionPlanChildList.forEach(productionPlanChild -> {
            // 到货计划单的数量 - 当前采购订单的数量 - 已经下达采购订单的数量
            Integer surplusNum = ErpOrderUtil.checkOperNumber(productionPlanChild.getOperNumber(), productionPlanChild.getNormsId(),
                orderNormsNum, executeNum);
            if (setData) {
                productionPlanChild.setOperNumber(surplusNum);
            }
        });
        if (setData) {
            // 过滤掉剩余数量为0的商品
            productionPlanChildList = productionPlanChildList.stream()
                .filter(productionPlanChild -> productionPlanChild.getOperNumber() > 0).collect(Collectors.toList());
            // 如果该合同的商品已经全部下达订单，那说明已经完成了合同的内容
            if (CollectionUtil.isEmpty(productionPlanChildList)) {
                productionPlanService.editPurchaseState(productionPlan.getId(), ProductionPlanPurchaseState.COMPLATE.getKey());
            } else {
                productionPlanService.editPurchaseState(productionPlan.getId(), ProductionPlanPurchaseState.PARTIAL.getKey());
            }
        }
    }

    @Override
    public Map<String, Integer> calcMaterialNormsNumByFromId(String fromId) {
        QueryWrapper<PurchaseOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(PurchaseOrder::getFromId), fromId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(PurchaseOrder::getIdKey), getServiceClassName());
        // 只查询审批通过，部分入库，已完成的采购订单
        List<String> stateList = Arrays.asList(new String[]{FlowableStateEnum.PASS.getKey(), ErpOrderStateEnum.PARTIALLY_COMPLETED.getKey(),
            ErpOrderStateEnum.COMPLETED.getKey()});
        queryWrapper.in(MybatisPlusUtil.toColumns(PurchaseOrder::getState), stateList);
        List<PurchaseOrder> purchaseOrderList = list(queryWrapper);
        List<String> ids = purchaseOrderList.stream().map(PurchaseOrder::getId).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(ids)) {
            return new HashMap<>();
        }
        // 获取所有的商品信息
        List<ErpOrderItem> erpOrderItemList = skyeyeErpOrderItemService.queryErpOrderItemByPIds(ids);
        if (CollectionUtil.isNotEmpty(erpOrderItemList)) {
            // 分组计算已经下达订单的数量
            return erpOrderItemList.stream()
                .collect(Collectors.groupingBy(ErpOrderItem::getNormsId, Collectors.summingInt(ErpOrderItem::getOperNumber)));
        }
        return MapUtil.newHashMap();
    }

    @Override
    public PurchaseOrder selectById(String id) {
        PurchaseOrder purchaseOrder = super.selectById(id);
        if (purchaseOrder.getFromTypeId() == PurchaseOrderFromType.SUPPLIER_CONTRACT.getKey()) {
            // 采购合同
            supplierContractService.setDataMation(purchaseOrder, PurchaseOrder::getFromId);
        } else if (purchaseOrder.getFromTypeId() == PurchaseOrderFromType.DELIVERY_PLAN.getKey()) {
            // 到货计划
            productionPlanService.setDataMation(purchaseOrder, PurchaseOrder::getFromId);
        }
        purchaseOrder.getErpOrderItemList().forEach(erpOrderItem -> {
            erpOrderItem.setQualityInspectionMation(OrderItemQualityInspectionType.getMation(erpOrderItem.getQualityInspection()));
        });
        return purchaseOrder;
    }

    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void insertPurchaseOrderToTurnPut(InputObject inputObject, OutputObject outputObject) {
        PurchasePut purchasePut = inputObject.getParams(PurchasePut.class);
        // 获取采购单状态
        PurchaseOrder order = selectById(purchasePut.getId());
        if (ObjectUtil.isEmpty(order)) {
            throw new CustomException("该数据不存在.");
        }
        // 审核通过/部分完成的可以进行入库
        if (FlowableStateEnum.PASS.getKey().equals(order.getState()) || ErpOrderStateEnum.PARTIALLY_COMPLETED.getKey().equals(order.getState())) {
            if (order.getQualityInspection() == OrderQualityInspectionType.NEED_QUALITYINS_INS.getKey()) {
                throw new CustomException("该订单需要进行质检，无法直接转采购入库，请先转【到货单】.");
            }
            String userId = inputObject.getLogParams().get("id").toString();
            purchasePut.setFromId(purchasePut.getId());
            purchasePut.setFromTypeId(PurchasePutFromType.PURCHASE_ORDER.getKey());
            purchasePut.setId(StrUtil.EMPTY);
            purchasePutService.createEntity(purchasePut, userId);
        } else {
            outputObject.setreturnMessage("状态错误，无法下达采购入库单.");
        }
    }

    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void insertPurchaseOrderToTurnDelivery(InputObject inputObject, OutputObject outputObject) {
        PurchaseDelivery purchaseDelivery = inputObject.getParams(PurchaseDelivery.class);
        // 获取采购单状态
        PurchaseOrder order = selectById(purchaseDelivery.getId());
        if (ObjectUtil.isEmpty(order)) {
            throw new CustomException("该数据不存在.");
        }
        // 审核通过/部分完成的可以转到货单
        if (FlowableStateEnum.PASS.getKey().equals(order.getState()) || ErpOrderStateEnum.PARTIALLY_COMPLETED.getKey().equals(order.getState())) {
            if (order.getQualityInspection() == OrderQualityInspectionType.NOT_NEED_QUALITYINS_INS.getKey()) {
                throw new CustomException("该订单无需进行质检，请直接转采购入库.");
            }
            String userId = inputObject.getLogParams().get("id").toString();
            purchaseDelivery.setFromId(purchaseDelivery.getId());
            purchaseDelivery.setFromTypeId(PurchaseDeliveryFromType.PURCHASE_ORDER.getKey());
            purchaseDelivery.setId(StrUtil.EMPTY);
            purchaseDeliveryService.createEntity(purchaseDelivery, userId);
        } else {
            outputObject.setreturnMessage("状态错误，无法下达到货单.");
        }
    }

    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void insertPurchaseOrderToReturns(InputObject inputObject, OutputObject outputObject) {
        PurchaseReturn purchaseReturn = inputObject.getParams(PurchaseReturn.class);
        // 获取采购单状态
        PurchaseOrder order = selectById(purchaseReturn.getId());
        if (ObjectUtil.isEmpty(order)) {
            throw new CustomException("该数据不存在.");
        }
        // 审核通过/部分完成的可以转到采购退货单
        if (FlowableStateEnum.PASS.getKey().equals(order.getState()) || ErpOrderStateEnum.PARTIALLY_COMPLETED.getKey().equals(order.getState())) {
            String userId = inputObject.getLogParams().get("id").toString();
            purchaseReturn.setFromId(purchaseReturn.getId());
            purchaseReturn.setFromTypeId(PurchaseReturnsFromType.PURCHASE_ORDER.getKey());
            purchaseReturn.setId(StrUtil.EMPTY);
            purchaseReturnsService.createEntity(purchaseReturn, userId);
        } else {
            outputObject.setreturnMessage("状态错误，无法下达采购退货单.");
        }
    }

    @Override
    public void approvalEndIsSuccess(PurchaseOrder entity) {
        entity = selectById(entity.getId());
        checkMaterialNorms(entity, true);
    }

    @Override
    public void queryPurchaseOrderTransById(InputObject inputObject, OutputObject outputObject) {
        String id = inputObject.getParams().get("id").toString();
        PurchaseOrder purchaseOrder = selectById(id);
        // 该采购订单下的已经下达采购退货单(审核通过)的数量
        Map<String, Integer> normsReturnMap = purchaseReturnsService.calcMaterialNormsNumByFromId(purchaseOrder.getId());
        if (purchaseOrder.getQualityInspection() == OrderQualityInspectionType.NEED_QUALITYINS_INS.getKey()) {
            // 需要质检，计算未到货数量
            Map<String, Integer> normsNum = purchaseDeliveryService.calcMaterialNormsNumByFromId(id);
            // 设置未下达到货单的商品数量-----采购订单数量 - 已到货数量 - 已退货数量
            super.setOrCheckOperNumber(purchaseOrder.getErpOrderItemList(), true, normsNum, normsReturnMap);
        } else {
            // 免检，计算未入库的数量
            Map<String, Integer> normsNum = purchasePutService.calcMaterialNormsNumByFromId(id);
            // 设置未下达采购入库单的商品数量-----采购订单数量 - 已入库数量 - 已退货数量
            super.setOrCheckOperNumber(purchaseOrder.getErpOrderItemList(), true, normsNum, normsReturnMap);
        }
        // 过滤掉数量为0的进行生成采购入库单/到货单/退货单
        purchaseOrder.setErpOrderItemList(purchaseOrder.getErpOrderItemList().stream()
            .filter(erpOrderItem -> erpOrderItem.getOperNumber() > 0).collect(Collectors.toList()));
        outputObject.setBean(purchaseOrder);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    @Override
    public void editQualityInspection(String id, Integer qualityInspection) {
        UpdateWrapper<PurchaseOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, id);
        updateWrapper.set(MybatisPlusUtil.toColumns(PurchaseOrder::getQualityInspection), qualityInspection);
        update(updateWrapper);
        refreshCache(id);
    }

}
