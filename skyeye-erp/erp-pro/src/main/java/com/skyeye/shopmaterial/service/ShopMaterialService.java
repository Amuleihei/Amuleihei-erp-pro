/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.shopmaterial.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.shopmaterial.entity.ShopMaterial;

/**
 * @ClassName: ShopMaterialService
 * @Description: 商城商品服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/4 17:53
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ShopMaterialService extends SkyeyeBusinessService<ShopMaterial> {

    void queryTransMaterialById(InputObject inputObject, OutputObject outputObject);

    void queryShopMaterialList(InputObject inputObject, OutputObject outputObject);

    void queryShopMaterialByNormsIdList(InputObject inputObject, OutputObject outputObject);

    void queryShopMaterialById(InputObject inputObject, OutputObject outputObject);
}
