/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.shopmaterial.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.material.classenum.MaterialShelvesState;
import com.skyeye.material.entity.Material;
import com.skyeye.material.service.MaterialService;
import com.skyeye.shopmaterial.dao.ShopMaterialDao;
import com.skyeye.shopmaterial.entity.ShopMaterial;
import com.skyeye.shopmaterial.entity.ShopMaterialNorms;
import com.skyeye.shopmaterial.service.ShopMaterialNormsService;
import com.skyeye.shopmaterial.service.ShopMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ShopMaterialNormsService shopMaterialNormsService;

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
        } else if (material.getMaterialNorms().size() > entity.getShopMaterialNormsList().size()) {
            materialService.setShelvesState(material.getId(), MaterialShelvesState.PART_ON_SHELVE.getKey());
        } else if (material.getMaterialNorms().size() == entity.getShopMaterialNormsList().size()) {
            materialService.setShelvesState(material.getId(), MaterialShelvesState.ON_SHELVE.getKey());
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
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        Page pages = PageHelper.startPage(commonPageInfo.getPage(), commonPageInfo.getLimit());
        MPJLambdaWrapper<ShopMaterial> wrapper = new MPJLambdaWrapper<ShopMaterial>()
            .innerJoin(Material.class, Material::getId, ShopMaterial::getMaterialId)
            .like(StrUtil.isNotBlank(commonPageInfo.getKeyword()), Material::getName, commonPageInfo.getKeyword());

        List<ShopMaterial> shopMaterialList = skyeyeBaseMapper.selectJoinList(ShopMaterial.class, wrapper);
        List<String> idList = shopMaterialList.stream().map(ShopMaterial::getId).collect(Collectors.toList());
        List<ShopMaterial> shopMaterials = selectByIds(idList.toArray(new String[]{}));
        outputObject.setBeans(shopMaterials);
        outputObject.settotal(pages.getTotal());
    }

}
