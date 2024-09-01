/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.businessflow.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface PlanProjectFlowService {

    void queryPlanProjectFlowList(InputObject inputObject, OutputObject outputObject);

    void insertPlanProjectFlowMation(InputObject inputObject, OutputObject outputObject);

    void deletePlanProjectFlowMationById(InputObject inputObject, OutputObject outputObject);

    void queryPlanProjectFlowMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editPlanProjectFlowMationById(InputObject inputObject, OutputObject outputObject);

    void queryPlanProjectFlowJsonContentMationById(InputObject inputObject, OutputObject outputObject);

    void editPlanProjectFlowJsonContentMationById(InputObject inputObject, OutputObject outputObject);

}
