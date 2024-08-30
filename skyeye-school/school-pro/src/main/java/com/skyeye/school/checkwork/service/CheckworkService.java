/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.checkwork.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.checkwork.entity.Checkwork;

/**
 * @ClassName: CheckworkService
 * @Description: 考勤管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/24 10:46
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface CheckworkService extends SkyeyeBusinessService<Checkwork> {

    void queryCheckworkBySourceCode(InputObject inputObject, OutputObject outputObject);

    Checkwork queryCheckworkBySourceCode(String sourceCode);
}
