/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.procedure.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.procedure.entity.WorkProcedureEquipment;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: WorkProcedureEquipmentService
 * @Description: 工序下的设备清单管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/18 8:46
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface WorkProcedureEquipmentService extends SkyeyeBusinessService<WorkProcedureEquipment> {

    void saveList(String parentId, List<WorkProcedureEquipment> beans);

    void deleteByParentId(String parentId);

    List<WorkProcedureEquipment> selectByParentId(String parentId);

    Map<String, List<WorkProcedureEquipment>> selectByParentId(List<String> parentIds);

}
