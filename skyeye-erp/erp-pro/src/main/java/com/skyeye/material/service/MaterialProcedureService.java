/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.material.entity.MaterialProcedure;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: MaterialProcedureService
 * @Description: ERP商品表与工序管理的关系服务接口类
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/17 22:44
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface MaterialProcedureService extends SkyeyeBusinessService<MaterialProcedure> {

    /**
     * 根据商品id删除关联的工序信息
     *
     * @param materialId 商品id
     */
    void deleteMaterialProcedureByMaterialId(String materialId);

    /**
     * 保存商品与工序的关联关系信息
     *
     * @param materialId
     * @param materialProcedureList
     * @param userId
     */
    void saveMaterialProcedure(String materialId, List<MaterialProcedure> materialProcedureList, String userId);

    /**
     * 根据商品id查询关联的工序信息
     *
     * @param materialId 商品id
     */
    List<MaterialProcedure> queryMaterialProcedureByMaterialId(String materialId);

    /**
     * 根据商品id查询关联的工序信息
     *
     * @param materialIds 商品id
     */
    Map<String, List<MaterialProcedure>> queryMaterialProcedureByMaterialIds(List<String> materialIds);

}
