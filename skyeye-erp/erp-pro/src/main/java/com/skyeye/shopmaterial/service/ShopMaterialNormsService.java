/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.shopmaterial.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.shopmaterial.entity.ShopMaterialNorms;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ShopMaterialNormsService
 * @Description: 商城商品规格参数服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/4 17:09
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ShopMaterialNormsService extends SkyeyeBusinessService<ShopMaterialNorms> {

    void deleteByMaterialId(String materialId);

    List<ShopMaterialNorms> selectByMaterialId(String materialId);

    Map<String, List<ShopMaterialNorms>> selectByMaterialId(List<String> materialId);

    void saveList(String materialId, List<ShopMaterialNorms> shopMaterialNormsList);

    List<ShopMaterialNorms> queryShopMaterialByNormsIdList(List<String> normsIdList);

}
