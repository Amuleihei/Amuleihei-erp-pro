/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.CodeModelGroup;

/**
 * @ClassName: CodeModelGroupService
 * @Description: 模板分组服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/10/9 20:57
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface CodeModelGroupService extends SkyeyeBusinessService<CodeModelGroup> {

    void queryTableParameterByTableName(InputObject inputObject, OutputObject outputObject);

    void queryTableMationByTableName(InputObject inputObject, OutputObject outputObject);

    void queryCodeModelListByGroupId(InputObject inputObject, OutputObject outputObject);

}
