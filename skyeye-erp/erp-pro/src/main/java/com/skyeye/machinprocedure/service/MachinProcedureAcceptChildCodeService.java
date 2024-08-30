/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.machinprocedure.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.machinprocedure.entity.MachinProcedureAcceptChildCode;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: MachinProcedureAcceptChildCodeService
 * @Description: 加工单子单据工序验收耗材表关联的条形码编号服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/30 14:42
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface MachinProcedureAcceptChildCodeService extends SkyeyeBusinessService<MachinProcedureAcceptChildCode> {

    void saveList(String orderId, List<MachinProcedureAcceptChildCode> beans);

    void deleteByOrderId(String orderId);

    List<MachinProcedureAcceptChildCode> selectByOrderId(String... orderId);

    Map<String, List<String>> selectMapByOrderId(String... orderId);

}
