/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.shopmaterial.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.brand.entity.Brand;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.material.classenum.MaterialShelvesState;
import com.skyeye.material.entity.Material;
import com.skyeye.material.service.MaterialNormsService;
import com.skyeye.material.service.MaterialService;
import com.skyeye.shopmaterial.dao.ShopMaterialDao;
import com.skyeye.shopmaterial.entity.ShopMaterial;
import com.skyeye.shopmaterial.entity.ShopMaterialNorms;
import com.skyeye.shopmaterial.entity.ShopMaterialStore;
import com.skyeye.shopmaterial.service.ShopMaterialNormsService;
import com.skyeye.shopmaterial.service.ShopMaterialService;
import com.skyeye.shopmaterial.service.ShopMaterialStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ShopMaterialServiceImpl
 * @Description: 商城商品服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/4 17:54
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "商城商品", groupName = "商城商品")
public class ShopMaterialServiceImpl extends SkyeyeBusinessServiceImpl<ShopMaterialDao, ShopMaterial> implements ShopMaterialService {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterialNormsService materialNormsService;

    @Autowired
    private ShopMaterialNormsService shopMaterialNormsService;

    @Autowired
    private ShopMaterialStoreService shopMaterialStoreService;

    @Override
    public void createPrepose(ShopMaterial entity) {
        entity.setRealSales(CommonNumConstants.NUM_ZERO.toString());
    }

    @Override
    public void writePostpose(ShopMaterial entity, String userId) {
        // 保存商城商品规格
        shopMaterialNormsService.saveList(entity.getMaterialId(), entity.getShopMaterialNormsList());

        // 更新商品的上架状态
        Material material = materialService.selectById(entity.getMaterialId());
        if (CollectionUtil.isEmpty(entity.getShopMaterialNormsList())) {
            materialService.setShelvesState(material.getId(), MaterialShelvesState.NOT_ON_SHELVE.getKey());
            shopMaterialStoreService.deleteByMaterialId(entity.getMaterialId());
        } else if (material.getMaterialNorms().size() > entity.getShopMaterialNormsList().size()) {
            materialService.setShelvesState(material.getId(), MaterialShelvesState.PART_ON_SHELVE.getKey());
            shopMaterialStoreService.addAllStoreForMaterial(entity.getMaterialId());
        } else if (material.getMaterialNorms().size() == entity.getShopMaterialNormsList().size()) {
            materialService.setShelvesState(material.getId(), MaterialShelvesState.ON_SHELVE.getKey());
            shopMaterialStoreService.addAllStoreForMaterial(entity.getMaterialId());
        }
    }

    @Override
    public void queryTransMaterialById(InputObject inputObject, OutputObject outputObject) {
        String materialId = inputObject.getParams().get("id").toString();
        ShopMaterial shopMaterial = selectByMaterialId(materialId);
        if (ObjectUtil.isEmpty(shopMaterial) || StrUtil.isEmpty(shopMaterial.getId())) {
            shopMaterial = new ShopMaterial();
            Material material = materialService.selectById(materialId);
            shopMaterial.setMaterialId(materialId);
            shopMaterial.setMaterialMation(material);
        }
        outputObject.setBean(shopMaterial);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    @Override
    public ShopMaterial getDataFromDb(String id) {
        ShopMaterial shopMaterial = super.getDataFromDb(id);
        if (ObjectUtil.isEmpty(shopMaterial) || StrUtil.isEmpty(shopMaterial.getId())) {
            return shopMaterial;
        }
        List<ShopMaterialNorms> shopMaterialNormsList = shopMaterialNormsService.selectByMaterialId(shopMaterial.getMaterialId());
        shopMaterial.setShopMaterialNormsList(shopMaterialNormsList);
        return shopMaterial;
    }

    private ShopMaterial selectByMaterialId(String materialId) {
        QueryWrapper<ShopMaterial> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopMaterial::getMaterialId), materialId);
        ShopMaterial shopMaterial = super.getOne(queryWrapper);
        if (ObjectUtil.isEmpty(shopMaterial)) {
            return null;
        }
        return selectById(shopMaterial.getId());
    }

    @Override
    public ShopMaterial selectById(String id) {
        ShopMaterial shopMaterial = super.selectById(id);
        if (StrUtil.isEmpty(shopMaterial.getId())) {
            return null;
        }
        materialService.setDataMation(shopMaterial, ShopMaterial::getMaterialId);
        return shopMaterial;
    }

    @Override
    public List<ShopMaterial> getDataFromDb(List<String> idList) {
        List<ShopMaterial> shopMaterialList = super.getDataFromDb(idList);
        if (CollectionUtil.isEmpty(shopMaterialList)) {
            return shopMaterialList;
        }
        List<String> materialIdList = shopMaterialList.stream().map(ShopMaterial::getMaterialId).distinct().collect(Collectors.toList());
        Map<String, List<ShopMaterialNorms>> collectMap = shopMaterialNormsService.selectByMaterialId(materialIdList);
        shopMaterialList.forEach(shopMaterial -> {
            shopMaterial.setShopMaterialNormsList(collectMap.get(shopMaterial.getMaterialId()));
        });
        return shopMaterialList;
    }

    @Override
    public List<ShopMaterial> selectByIds(String... ids) {
        List<ShopMaterial> shopMaterialList = super.selectByIds(ids);
        materialService.setDataMation(shopMaterialList, ShopMaterial::getMaterialId);
        return shopMaterialList;
    }

    @Override
    public void queryShopMaterialList(InputObject inputObject, OutputObject outputObject) {
        List<ShopMaterialStore> shopMaterialStoreList = shopMaterialStoreService.queryShopMaterialList(inputObject, outputObject);
        List<String> materialIdList = shopMaterialStoreList.stream().map(ShopMaterialStore::getMaterialId).collect(Collectors.toList());
        // 根据商品id查询上架的商品信息
        QueryWrapper<ShopMaterial> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(ShopMaterial::getMaterialId), materialIdList);
        List<ShopMaterial> shopMaterialList = list(queryWrapper);
        // 根据id批量查询详细的商品信息
        List<String> idList = shopMaterialList.stream().map(ShopMaterial::getId).collect(Collectors.toList());
        List<ShopMaterial> shopMaterials = selectByIds(idList.toArray(new String[]{}));
        Map<String, ShopMaterial> materialMap = shopMaterials.stream().collect(
            Collectors.toMap(ShopMaterial::getMaterialId, shopMaterial -> shopMaterial));
        shopMaterialStoreList.forEach(shopMaterialStore -> {
            ShopMaterial shopMaterial = materialMap.get(shopMaterialStore.getMaterialId());
            shopMaterial.getMaterialMation().setMaterialNorms(null);
            shopMaterial.getMaterialMation().setUnitGroupMation(null);
            shopMaterial.getMaterialMation().setMaterialProcedure(null);
            shopMaterial.getMaterialMation().setNormsSpec(null);
            shopMaterialStore.setShopMaterial(shopMaterial);
        });

        outputObject.setBeans(shopMaterialStoreList);
    }

    @Override
    public void queryShopMaterialByNormsIdList(InputObject inputObject, OutputObject outputObject) {
        List<String> normsIdList = Arrays.asList(inputObject.getParams().get("normsIds").toString()
            .split(CommonCharConstants.COMMA_MARK));
        List<ShopMaterialNorms> shopMaterialNormsList = shopMaterialNormsService.queryShopMaterialByNormsIdList(normsIdList);
        outputObject.setBeans(shopMaterialNormsList);
        outputObject.settotal(shopMaterialNormsList.size());
    }

    @Override
    public void queryShopMaterialById(InputObject inputObject, OutputObject outputObject) {
        String id = inputObject.getParams().get("id").toString();
        ShopMaterial shopMaterial = selectById(id);
        shopMaterial.getMaterialMation().setMaterialNorms(null);
        shopMaterial.getMaterialMation().setUnitGroupMation(null);
        shopMaterial.getMaterialMation().setMaterialProcedure(null);
        shopMaterial.getMaterialMation().setNormsSpec(null);
        materialNormsService.setDataMation(shopMaterial.getShopMaterialNormsList(), ShopMaterialNorms::getNormsId);
        shopMaterial.getShopMaterialNormsList().forEach(shopMaterialNorms -> {
            shopMaterialNorms.setEstimatePurchasePrice(null);
        });
        outputObject.setBean(shopMaterial);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    @Override
    public void queryBrandShopMaterialList(InputObject inputObject, OutputObject outputObject) {
        MPJLambdaWrapper<ShopMaterial> wrapper = new MPJLambdaWrapper<ShopMaterial>()
            .innerJoin(Material.class, Material::getId, ShopMaterial::getMaterialId)
            .innerJoin(Brand.class, Brand::getId, Material::getBrandId)
            .eq(Brand::getEnabled, EnableEnum.ENABLE_USING.getKey());
        List<ShopMaterial> shopMaterialList = skyeyeBaseMapper.selectJoinList(ShopMaterial.class, wrapper);
        // 根据id批量查询详细的商品信息
        List<String> idList = shopMaterialList.stream().map(ShopMaterial::getId).collect(Collectors.toList());
        shopMaterialList = selectByIds(idList.toArray(new String[]{}));
        shopMaterialList.forEach(shopMaterial -> {
            shopMaterial.getMaterialMation().setMaterialNorms(null);
            shopMaterial.getMaterialMation().setUnitGroupMation(null);
            shopMaterial.getMaterialMation().setMaterialProcedure(null);
            shopMaterial.getMaterialMation().setNormsSpec(null);
        });

        // 根据品牌id进行分组，并且每个品牌下只取8条数据
        Map<String, List<ShopMaterial>> collectMap = shopMaterialList.stream().collect(Collectors.groupingBy(bean -> bean.getMaterialMation().getBrandId(), Collectors.collectingAndThen(
            Collectors.toList(), // 分组的 downstream
            list -> {
                if (list.size() > 8) {
                    return list.subList(0, 8); // 只取前8个元素
                }
                return list;
            }
        )));
        outputObject.setBean(collectMap);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

}
