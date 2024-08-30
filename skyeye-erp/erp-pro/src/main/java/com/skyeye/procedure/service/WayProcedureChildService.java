/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.procedure.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.procedure.entity.WayProcedureChild;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: WayProcedureChildService
 * @Description: 工艺路线关联的工序服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/3/26 20:24
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface WayProcedureChildService extends SkyeyeBusinessService<WayProcedureChild> {

    void deleteWayProcedureByWayId(String wayId);

    void saveWayProcedure(String wayId, List<WayProcedureChild> wayProcedureList, String userId);

    List<WayProcedureChild> queryWayProcedureByWayId(String wayId);

    String calcOrderAllTotalPrice(List<WayProcedureChild> wayProcedureChildList);

    Map<String, List<WayProcedureChild>> queryWayProcedureByWayId(List<String> wayIds);


}
