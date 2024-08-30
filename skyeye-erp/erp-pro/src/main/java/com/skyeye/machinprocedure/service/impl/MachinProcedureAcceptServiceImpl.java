/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.machinprocedure.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Joiner;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeFlowableServiceImpl;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.enumeration.FlowableStateEnum;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.depot.classenum.DepotPutOutType;
import com.skyeye.exception.CustomException;
import com.skyeye.machin.entity.Machin;
import com.skyeye.machinprocedure.classenum.MachinProcedureAcceptChildType;
import com.skyeye.machinprocedure.classenum.MachinProcedureFarmState;
import com.skyeye.machinprocedure.dao.MachinProcedureAcceptDao;
import com.skyeye.machinprocedure.entity.MachinProcedureAccept;
import com.skyeye.machinprocedure.entity.MachinProcedureAcceptChild;
import com.skyeye.machinprocedure.entity.MachinProcedureAcceptChildCode;
import com.skyeye.machinprocedure.entity.MachinProcedureFarm;
import com.skyeye.machinprocedure.service.*;
import com.skyeye.material.classenum.MaterialItemCode;
import com.skyeye.material.classenum.MaterialNormsCodeInDepot;
import com.skyeye.material.entity.Material;
import com.skyeye.material.entity.MaterialNorms;
import com.skyeye.material.entity.MaterialNormsCode;
import com.skyeye.material.service.MaterialNormsCodeService;
import com.skyeye.material.service.MaterialNormsService;
import com.skyeye.material.service.MaterialService;
import com.skyeye.pick.classenum.PickNormsCodeUseState;
import com.skyeye.pick.service.DepartmentStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: MachinProcedureAcceptServiceImpl
 * @Description: 工序验收服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/24 20:13
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "工序验收", groupName = "工序验收", flowable = true)
public class MachinProcedureAcceptServiceImpl extends SkyeyeFlowableServiceImpl<MachinProcedureAcceptDao, MachinProcedureAccept> implements MachinProcedureAcceptService {

    @Autowired
    private MachinProcedureService machinProcedureService;

    @Autowired
    private MachinProcedureFarmService machinProcedureFarmService;

    @Autowired
    private MachinProcedureAcceptChildService machinProcedureAcceptChildService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterialNormsService materialNormsService;

    @Autowired
    private DepartmentStockService departmentStockService;

    @Autowired
    protected MaterialNormsCodeService materialNormsCodeService;

    @Autowired
    private MachinProcedureAcceptChildCodeService machinProcedureAcceptChildCodeService;

    @Override
    public void validatorEntity(MachinProcedureAccept entity) {
        if (StrUtil.isEmpty(entity.getId())) {
            MachinProcedureFarm machinProcedureFarm = machinProcedureFarmService.selectById(entity.getMachinProcedureFarmId());
            entity.setMachinId(machinProcedureFarm.getMachinId());
            entity.setDepartmentId(machinProcedureFarm.getFarmMation().getDepartmentId());
            entity.setFarmId(machinProcedureFarm.getFarmId());
            entity.setMachinProcedureId(machinProcedureFarm.getMachinProcedureId());
        } else {
            MachinProcedureAccept oldEntity = super.selectById(entity.getId());
            entity.setMachinId(oldEntity.getMachinId());
            entity.setDepartmentId(oldEntity.getDepartmentId());
            entity.setFarmId(oldEntity.getFarmId());
            entity.setMachinProcedureId(oldEntity.getMachinProcedureId());
        }

        // 实际验收总数量 = 合格数量 + 返工数量 + 报废数量
        int tempNum = entity.getQualifiedNum() + entity.getReworkNum() + entity.getScrapNum();
        if (entity.getAcceptNum() != tempNum) {
            throw new CustomException("验收数量不等于【合格数量】 + 【返工数量】 + 【报废数量】，请确认.");
        }

        boolean isCompleted = machinProcedureService.checkPrevMachinProcedureIsCompleted(entity.getMachinProcedureId());
        if (!isCompleted) {
            throw new CustomException("请先完成上一个工序的验收");
        }

        // 校验并修改条形码信息
        checkNormsCodeAndSave(entity, true);
    }

    @Override
    public void writePostpose(MachinProcedureAccept entity, String userId) {
        List<MachinProcedureAcceptChild> childList = new ArrayList<>();
        mergeAcceptChild(entity, childList);
        machinProcedureAcceptChildService.saveList(entity.getId(), childList);
        // 保存商品规格编码信息
        saveErpOrderItemCode(entity);
        super.writePostpose(entity, userId);
    }

    private void saveErpOrderItemCode(MachinProcedureAccept entity) {
        List<String> materialIdList = entity.getMachinProcedureAcceptChildList().stream()
            .map(MachinProcedureAcceptChild::getMaterialId).distinct().collect(Collectors.toList());
        List<String> scrapMaterialIdList = entity.getMachinScrapProcedureAcceptChildList().stream()
            .map(MachinProcedureAcceptChild::getMaterialId).distinct().collect(Collectors.toList());
        materialIdList.addAll(scrapMaterialIdList);
        materialIdList = materialIdList.stream().distinct().collect(Collectors.toList());
        Map<String, Material> materialMap = materialService.selectMapByIds(materialIdList);
        // 保存单据子表关联的条形码编号信息
        List<MachinProcedureAcceptChildCode> machinProcedureAcceptChildCodeList = new ArrayList<>();
        // 正常耗材
        for (MachinProcedureAcceptChild machinProcedureAcceptChild : entity.getMachinProcedureAcceptChildList()) {
            Material material = materialMap.get(machinProcedureAcceptChild.getMaterialId());
            if (material.getItemCode() == MaterialItemCode.ONE_ITEM_CODE.getKey()) {
                // 一物一码
                // 过滤掉空的，并且去重
                List<String> normsCodeList = Arrays.asList(machinProcedureAcceptChild.getNormsCode().split("\n")).stream()
                    .filter(str -> StrUtil.isNotEmpty(str)).distinct().collect(Collectors.toList());
                if (machinProcedureAcceptChild.getOperNumber() != normsCodeList.size()) {
                    throw new CustomException(
                        String.format(Locale.ROOT, "商品【%s】的条形码数量与明细数量不一致，请确认", material.getName()));
                }
                normsCodeList.forEach(normsCode -> {
                    MachinProcedureAcceptChildCode erpOrderItemCode = new MachinProcedureAcceptChildCode();
                    erpOrderItemCode.setNormsCode(normsCode);
                    erpOrderItemCode.setMaterialId(machinProcedureAcceptChild.getMaterialId());
                    erpOrderItemCode.setNormsId(machinProcedureAcceptChild.getNormsId());
                    erpOrderItemCode.setParentId(machinProcedureAcceptChild.getId());
                    machinProcedureAcceptChildCodeList.add(erpOrderItemCode);
                });
            }
        }
        // 报废耗材
        for (MachinProcedureAcceptChild machinProcedureAcceptChild : entity.getMachinScrapProcedureAcceptChildList()) {
            Material material = materialMap.get(machinProcedureAcceptChild.getMaterialId());
            if (material.getItemCode() == MaterialItemCode.ONE_ITEM_CODE.getKey()) {
                // 一物一码
                // 过滤掉空的，并且去重
                List<String> normsCodeList = Arrays.asList(machinProcedureAcceptChild.getNormsCode().split("\n")).stream()
                    .filter(str -> StrUtil.isNotEmpty(str)).distinct().collect(Collectors.toList());
                if (machinProcedureAcceptChild.getOperNumber() != normsCodeList.size()) {
                    throw new CustomException(
                        String.format(Locale.ROOT, "商品【%s】的条形码数量与明细数量不一致，请确认", material.getName()));
                }
                normsCodeList.forEach(normsCode -> {
                    MachinProcedureAcceptChildCode erpOrderItemCode = new MachinProcedureAcceptChildCode();
                    erpOrderItemCode.setNormsCode(normsCode);
                    erpOrderItemCode.setMaterialId(machinProcedureAcceptChild.getMaterialId());
                    erpOrderItemCode.setNormsId(machinProcedureAcceptChild.getNormsId());
                    erpOrderItemCode.setParentId(machinProcedureAcceptChild.getId());
                    machinProcedureAcceptChildCodeList.add(erpOrderItemCode);
                });
            }
        }
        machinProcedureAcceptChildCodeService.saveList(entity.getId(), machinProcedureAcceptChildCodeList);
    }

    @Override
    public MachinProcedureAccept getDataFromDb(String id) {
        MachinProcedureAccept machinProcedureAccept = super.getDataFromDb(id);
        // 设置耗材信息
        List<MachinProcedureAcceptChild> machinProcedureAcceptChildList = machinProcedureAcceptChildService.selectByParentId(id);
        // 查询单据子表关联的条形码编号信息
        Map<String, List<String>> codeMap = machinProcedureAcceptChildCodeService.selectMapByOrderId(id);
        machinProcedureAcceptChildList.forEach(machinProcedureAcceptChild -> {
            String key = String.format(Locale.ROOT, "%s_%s", machinProcedureAcceptChild.getId(), machinProcedureAcceptChild.getNormsId());
            List<String> codeList = codeMap.get(key);
            if (CollectionUtil.isNotEmpty(codeList)) {
                machinProcedureAcceptChild.setNormsCodeList(codeList);
                machinProcedureAcceptChild.setNormsCode(Joiner.on("\n").join(codeList));
            }
        });

        Map<Integer, List<MachinProcedureAcceptChild>> childMap = machinProcedureAcceptChildList.stream()
            .collect(Collectors.groupingBy(MachinProcedureAcceptChild::getType));
        machinProcedureAccept.setMachinProcedureAcceptChildList(childMap.get(MachinProcedureAcceptChildType.NORMAL.getKey()));
        machinProcedureAccept.setMachinScrapProcedureAcceptChildList(childMap.get(MachinProcedureAcceptChildType.SCRAP.getKey()));
        return machinProcedureAccept;
    }

    @Override
    public MachinProcedureAccept selectById(String id) {
        MachinProcedureAccept machinProcedureAccept = super.selectById(id);
        // 设置产品/规格信息
        materialService.setDataMation(machinProcedureAccept.getMachinProcedureAcceptChildList(), MachinProcedureAcceptChild::getMaterialId);
        materialNormsService.setDataMation(machinProcedureAccept.getMachinProcedureAcceptChildList(), MachinProcedureAcceptChild::getNormsId);

        materialService.setDataMation(machinProcedureAccept.getMachinScrapProcedureAcceptChildList(), MachinProcedureAcceptChild::getMaterialId);
        materialNormsService.setDataMation(machinProcedureAccept.getMachinScrapProcedureAcceptChildList(), MachinProcedureAcceptChild::getNormsId);

        if (CollectionUtil.isNotEmpty(machinProcedureAccept.getMachinProcedureAcceptChildList())) {
            machinProcedureAccept.getMachinProcedureAcceptChildList().forEach(machinProcedureAcceptChild -> {
                machinProcedureAcceptChild.setTypeMation(MachinProcedureAcceptChildType.getMation(machinProcedureAcceptChild.getType()));
            });
        }

        if (CollectionUtil.isNotEmpty(machinProcedureAccept.getMachinScrapProcedureAcceptChildList())) {
            machinProcedureAccept.getMachinScrapProcedureAcceptChildList().forEach(machinProcedureAcceptChild -> {
                machinProcedureAcceptChild.setTypeMation(MachinProcedureAcceptChildType.getMation(machinProcedureAcceptChild.getType()));
            });
        }

        iAuthUserService.setDataMation(machinProcedureAccept, MachinProcedureAccept::getAcceptUserId);

        return machinProcedureAccept;
    }

    @Override
    public void deletePostpose(String id) {
        machinProcedureAcceptChildService.deleteByParentId(id);
        // 删除关联的编码信息
        machinProcedureAcceptChildCodeService.deleteByOrderId(id);
    }

    @Override
    public void approvalEndIsSuccess(MachinProcedureAccept entity) {
        // 获取车间任务
        MachinProcedureFarm machinProcedureFarm = machinProcedureFarmService.selectById(entity.getMachinProcedureFarmId());
        // 获取该任务下已经完成的量
        Integer allComplateNum = calcNumByMachinProcedureFarmId(entity.getMachinProcedureFarmId());
        // 计算未完成的量 = 车间任务目标量 - 已完成的量 - 当前单据合格的量
        Integer noComplateNum = machinProcedureFarm.getTargetNum() - allComplateNum - entity.getQualifiedNum();
        if (noComplateNum == 0) {
            machinProcedureFarmService.editStateById(machinProcedureFarm.getId(), MachinProcedureFarmState.ALL_COMPLETED.getKey());
        } else if (noComplateNum > 0) {
            machinProcedureFarmService.editStateById(machinProcedureFarm.getId(), MachinProcedureFarmState.PARTIAL_COMPLETION.getKey());
        } else if (noComplateNum < 0) {
            machinProcedureFarmService.editStateById(machinProcedureFarm.getId(), MachinProcedureFarmState.EXCESS_COMPLETED.getKey());
        }
        // 校验并修改条形码信息
        checkNormsCodeAndSave(entity, false);
    }

    /**
     * 校验商品规格条形码与单据明细的参数是否匹配
     *
     * @param entity
     * @param onlyCheck 是否只进行校验，true：是；false：否
     */
    public List<String> checkNormsCodeAndSave(MachinProcedureAccept entity, Boolean onlyCheck) {
        // 合并正常消耗和报废耗材
        List<MachinProcedureAcceptChild> childList = new ArrayList<>();
        mergeAcceptChild(entity, childList);
        // 查询商品/规格信息
        List<String> materialIdList = childList.stream().map(MachinProcedureAcceptChild::getMaterialId).distinct().collect(Collectors.toList());
        List<String> normsIdList = childList.stream().map(MachinProcedureAcceptChild::getNormsId).distinct().collect(Collectors.toList());
        Map<String, Material> materialMap = materialService.selectMapByIds(materialIdList);
        Map<String, MaterialNorms> normsMap = materialNormsService.selectMapByIds(normsIdList);
        // 所有需要进行耗材处理的条形码编码
        List<String> allNormsCodeList = new ArrayList<>();
        Map<String, Integer> normsCodeType = new HashMap<>();
        int allCodeNum = checkErpOrderItemDetail(childList, materialMap, normsMap, allNormsCodeList, normsCodeType);
        if (CollectionUtil.isNotEmpty(allNormsCodeList)) {
            allNormsCodeList = allNormsCodeList.stream().distinct().collect(Collectors.toList());
            if (allCodeNum != allNormsCodeList.size()) {
                throw new CustomException("商品明细中存在相同的条形码编号，请确认");
            }
            // 1. 校验数量
            Map<String, Integer> stock = departmentStockService.queryNormsDepartmentStock(entity.getDepartmentId(), entity.getFarmId(), normsIdList);
            Map<String, Integer> collect = childList.stream()
                .collect(Collectors.groupingBy(MachinProcedureAcceptChild::getNormsId, Collectors.summingInt(MachinProcedureAcceptChild::getOperNumber)));
            collect.forEach((normsId, changeNum) -> {
                Integer departmentFarmStock = stock.containsKey(normsId) ? stock.get(normsId) : 0;
                if (changeNum > departmentFarmStock) {
                    throw new CustomException(
                        String.format(Locale.ROOT, "商品【%s】超出当前仓库的库存，请确认", normsMap.get(normsId).getName()));
                }
            });
            // 2. 校验条形码
            //  2.1 从数据库查询出库状态的条形码信息，
            //  2.2 只有部门信息不为空的说明已经领料，才可以进行工序耗材。
            List<MaterialNormsCode> materialNormsCodeList = materialNormsCodeService.queryMaterialNormsCodeByCodeNum(StrUtil.EMPTY, allNormsCodeList,
                MaterialNormsCodeInDepot.OUTBOUND.getKey());
            materialNormsCodeList = materialNormsCodeList.stream()
                .filter(bean -> StrUtil.isNotEmpty(bean.getDepartmentId()) && StrUtil.equals(entity.getDepartmentId(), bean.getDepartmentId()))
                .collect(Collectors.toList());
            //  2.3 如果车间不为空，则需要获取过滤出当前车间的库存
            if (StrUtil.isNotEmpty(entity.getFarmId())) {
                materialNormsCodeList = materialNormsCodeList.stream()
                    .filter(bean -> StrUtil.isNotEmpty(bean.getFarmId()) && StrUtil.equals(entity.getFarmId(), bean.getFarmId()))
                    .collect(Collectors.toList());
            }
            //  1.4 只有未使用的可以进行工序耗材
            materialNormsCodeList = materialNormsCodeList.stream()
                .filter(bean -> PickNormsCodeUseState.WAIT_USE.getKey() == bean.getPickUseState())
                .collect(Collectors.toList());
            List<String> inSqlNormsCodeList = materialNormsCodeList.stream().map(MaterialNormsCode::getCodeNum).collect(Collectors.toList());
            // 获取所有前端传递过来的条形码信息，求差集(在入参中有，但是在数据库中不包含的条形码信息)
            List<String> diffList = allNormsCodeList.stream()
                .filter(num -> !inSqlNormsCodeList.contains(num)).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(diffList)) {
                throw new CustomException(
                    String.format(Locale.ROOT, "编码【%s】不存在或已被使用，请确认", Joiner.on(CommonCharConstants.COMMA_MARK).join(diffList)));
            }
            if (!onlyCheck) {
                // 批量修改条形码信息
                materialNormsCodeList.forEach(materialNormsCode -> {
                    materialNormsCode.setPickUseState(PickNormsCodeUseState.USED.getKey());
                    materialNormsCode.setPickState(normsCodeType.get(materialNormsCode.getCodeNum()));
                });
                materialNormsCodeService.updateEntityPick(materialNormsCodeList);
            }
        }
        if (!onlyCheck) {
            // 修改部门/车间的库存
            childList.forEach(acceptChild -> {
                departmentStockService.updateDepartmentStock(entity.getDepartmentId(), entity.getFarmId(), acceptChild.getMaterialId(),
                    acceptChild.getNormsId(), acceptChild.getOperNumber(), DepotPutOutType.OUT.getKey());
            });
        }
        return allNormsCodeList;
    }

    private int checkErpOrderItemDetail(List<MachinProcedureAcceptChild> childList, Map<String, Material> materialMap,
                                        Map<String, MaterialNorms> normsMap, List<String> allNormsCodeList,
                                        Map<String, Integer> normsCodeType) {
        int allCodeNum = 0;
        for (MachinProcedureAcceptChild acceptChild : childList) {
            Material material = materialMap.get(acceptChild.getMaterialId());
            MaterialNorms norms = normsMap.get(acceptChild.getNormsId());
            if (acceptChild.getOperNumber() == 0) {
                throw new CustomException(
                    String.format(Locale.ROOT, "商品【%s】【%s】的数量不能为0，请确认", material.getName(), norms.getName()));
            }
            if (material.getItemCode() == MaterialItemCode.ONE_ITEM_CODE.getKey()) {
                // 一物一码
                // 过滤掉空的，并且去重
                List<String> normsCodeList = Arrays.asList(acceptChild.getNormsCode().split("\n")).stream()
                    .filter(str -> StrUtil.isNotEmpty(str)).distinct().collect(Collectors.toList());
                if (acceptChild.getOperNumber() != normsCodeList.size()) {
                    throw new CustomException(
                        String.format(Locale.ROOT, "商品【%s】【%s】的条形码数量与明细数量不一致，请确认", material.getName(), norms.getName()));
                }
                allCodeNum += normsCodeList.size();
                acceptChild.setNormsCodeList(normsCodeList);
                normsCodeList.forEach(normsCode -> {
                    normsCodeType.put(normsCode, acceptChild.getType());
                });
                allNormsCodeList.addAll(normsCodeList);
            }
        }
        return allCodeNum;
    }

    private static void mergeAcceptChild(MachinProcedureAccept entity, List<MachinProcedureAcceptChild> childList) {
        if (CollectionUtil.isNotEmpty(entity.getMachinProcedureAcceptChildList())) {
            entity.getMachinProcedureAcceptChildList().forEach(child -> {
                child.setType(MachinProcedureAcceptChildType.NORMAL.getKey());
                childList.add(child);
            });
        }
        if (CollectionUtil.isNotEmpty(entity.getMachinScrapProcedureAcceptChildList())) {
            entity.getMachinScrapProcedureAcceptChildList().forEach(child -> {
                child.setType(MachinProcedureAcceptChildType.SCRAP.getKey());
                childList.add(child);
            });
        }
    }

    @Override
    public Integer calcNumByMachinProcedureFarmId(String machinProcedureFarmId) {
        QueryWrapper<MachinProcedureAccept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(MachinProcedureAccept::getMachinProcedureFarmId), machinProcedureFarmId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(Machin::getState), FlowableStateEnum.PASS.getKey());
        List<MachinProcedureAccept> machinList = list(queryWrapper);
        Integer allNum = machinList.stream()
            .collect(Collectors.summingInt(MachinProcedureAccept::getQualifiedNum));
        return allNum;
    }

    @Override
    public Map<String, List<MachinProcedureAccept>> queryMachinProcedureAcceptByMachinProcedureFarmId(String... machinProcedureFarmId) {
        List<String> machinProcedureFarmIdList = Arrays.asList(machinProcedureFarmId);
        if (CollectionUtil.isEmpty(machinProcedureFarmIdList)) {
            return MapUtil.newHashMap();
        }
        QueryWrapper<MachinProcedureAccept> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(MachinProcedureAccept::getMachinProcedureFarmId), machinProcedureFarmIdList);
        List<MachinProcedureAccept> machinList = list(queryWrapper);
        Map<String, List<MachinProcedureAccept>> map = machinList.stream()
            .collect(Collectors.groupingBy(MachinProcedureAccept::getMachinProcedureFarmId));
        return map;
    }
}
