/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.project.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.project.entity.AutoProject;

/**
 * @ClassName: AutoProjectService
 * @Description: 项目管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/20 19:27
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface AutoProjectService extends SkyeyeBusinessService<AutoProject> {
    void queryAllAutoProjectList(InputObject inputObject, OutputObject outputObject);
}
