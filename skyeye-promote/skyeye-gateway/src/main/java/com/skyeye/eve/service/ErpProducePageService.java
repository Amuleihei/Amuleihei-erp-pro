/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface ErpProducePageService {

    void queryDepartmentPickMaterial(InputObject inputObject, OutputObject outputObject);

    void queryDepartmentPatchMaterial(InputObject inputObject, OutputObject outputObject);

    void queryDepartmentReturnMaterial(InputObject inputObject, OutputObject outputObject);

    void queryDepartmentMachin(InputObject inputObject, OutputObject outputObject);

}
