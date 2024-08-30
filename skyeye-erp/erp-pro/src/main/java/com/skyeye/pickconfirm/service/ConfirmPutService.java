/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pickconfirm.service;

import com.skyeye.business.service.SkyeyeErpOrderService;
import com.skyeye.pickconfirm.entity.ConfirmPut;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ConfirmPutService
 * @Description: 物料接收单服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/27 10:03
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ConfirmPutService extends SkyeyeErpOrderService<ConfirmPut> {

    Map<String, List<String>> calcMaterialNormsCodeByFromId(String fromId);

}
