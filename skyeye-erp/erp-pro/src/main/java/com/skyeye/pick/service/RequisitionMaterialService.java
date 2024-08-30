/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pick.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.pick.entity.RequisitionMaterial;

/**
 * @ClassName: RequisitionMaterialService
 * @Description: 领料申请单管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/27 12:50
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface RequisitionMaterialService extends ErpPickService<RequisitionMaterial> {

    void queryRequisitionMaterialTransById(InputObject inputObject, OutputObject outputObject);

    void insertRequisitionMaterialToTurnOut(InputObject inputObject, OutputObject outputObject);
}
