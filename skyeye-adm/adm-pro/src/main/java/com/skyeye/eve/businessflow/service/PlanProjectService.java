/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.businessflow.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface PlanProjectService {

    void queryPlanProjectList(InputObject inputObject, OutputObject outputObject);

    void insertPlanProjectMation(InputObject inputObject, OutputObject outputObject);

    void deletePlanProjectMationById(InputObject inputObject, OutputObject outputObject);

    void queryPlanProjectMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editPlanProjectMationById(InputObject inputObject, OutputObject outputObject);

}
