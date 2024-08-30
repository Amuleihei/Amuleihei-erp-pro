/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.material.dao.MaterialProcedureDao;
import com.skyeye.material.entity.MaterialProcedure;
import com.skyeye.material.service.MaterialProcedureService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: MaterialProcedureServiceImpl
 * @Description: ERP商品表与工序管理的关系服务类
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/17 22:44
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "商品关联工序信息", groupName = "商品管理", manageShow = false)
public class MaterialProcedureServiceImpl extends SkyeyeBusinessServiceImpl<MaterialProcedureDao, MaterialProcedure> implements MaterialProcedureService {

    /**
     * 根据商品id删除关联的工序信息
     *
     * @param materialId 商品id
     */
    @Override
    public void deleteMaterialProcedureByMaterialId(String materialId) {
        QueryWrapper<MaterialProcedure> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(MaterialProcedure::getMaterialId), materialId);
        remove(queryWrapper);
    }

    /**
     * 保存商品与工序的关联关系信息
     *
     * @param materialId
     * @param materialProcedureList
     * @param userId
     */
    @Override
    public void saveMaterialProcedure(String materialId, List<MaterialProcedure> materialProcedureList, String userId) {
        deleteMaterialProcedureByMaterialId(materialId);
        if (CollectionUtil.isNotEmpty(materialProcedureList)) {
            for (MaterialProcedure materialProcedure : materialProcedureList) {
                materialProcedure.setMaterialId(materialId);
            }
            createEntity(materialProcedureList, userId);
        }
    }

    /**
     * 根据商品id查询关联的工序信息
     *
     * @param materialId 商品id
     */
    @Override
    public List<MaterialProcedure> queryMaterialProcedureByMaterialId(String materialId) {
        QueryWrapper<MaterialProcedure> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(MaterialProcedure::getMaterialId), materialId);
        List<MaterialProcedure> materialProcedureList = list(queryWrapper);
        return materialProcedureList;
    }

    /**
     * 根据商品id查询关联的工序信息
     *
     * @param materialIds 商品id
     */
    @Override
    public Map<String, List<MaterialProcedure>> queryMaterialProcedureByMaterialIds(List<String> materialIds) {
        QueryWrapper<MaterialProcedure> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(MaterialProcedure::getMaterialId), materialIds);
        List<MaterialProcedure> materialProcedureList = list(queryWrapper);
        Map<String, List<MaterialProcedure>> listMap = materialProcedureList
            .stream().collect(Collectors.groupingBy(MaterialProcedure::getMaterialId));
        return listMap;
    }

}
