/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.deploy.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

/**
 * @ClassName: DeployJarService
 * @Description: 部署jar服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/20 9:28
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface DeployJarService {

    void insertDeployProject(InputObject inputObject, OutputObject outputObject);

    void queryDeployProject(InputObject inputObject, OutputObject outputObject);
}
