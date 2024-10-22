/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.shopmaterial.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.shopmaterial.entity.ShopMaterialStore;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ShopMaterialStoreService
 * @Description: 商城商品上线的门店服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/18 14:10
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ShopMaterialStoreService extends SkyeyeBusinessService<ShopMaterialStore> {

    void deleteByMaterialId(String materialId);

    List<ShopMaterialStore> selectByMaterialId(String materialId);

    Map<String, List<ShopMaterialStore>> selectByMaterialId(List<String> materialId);

    void saveList(String materialId, List<ShopMaterialStore> shopMaterialStoreList);

    /**
     * 添加指定商品到所有门店
     *
     * @param materialId 商品ID
     */
    void addAllStoreForMaterial(String materialId);

    void saveShopMaterialStore(InputObject inputObject, OutputObject outputObject);

    List<ShopMaterialStore> queryShopMaterialList(InputObject inputObject, OutputObject outputObject);

    Map<String, ShopMaterialStore> queryShopMaterialStoreByMaterialIds(String... materialIds);

    void queryShopMaterialById(InputObject inputObject, OutputObject outputObject);

}
