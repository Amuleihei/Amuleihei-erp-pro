/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.assets.service;

import com.skyeye.base.business.service.SkyeyeFlowableService;
import com.skyeye.eve.assets.entity.AssetPurchaseReturn;

import java.util.Map;

/**
 * @ClassName: AssetPurchaseReturnService
 * @Description: 资产采购退货服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/19 20:31
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface AssetPurchaseReturnService extends SkyeyeFlowableService<AssetPurchaseReturn> {

    Map<String, Integer> calcAssetNumByFromId(String fromId);

}
