/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.usercase.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.usercase.entity.AutoStepInput;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AutoStepInputService
 * @Description: 用例步骤前置条件管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:21
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
public interface AutoStepInputService extends SkyeyeBusinessService<AutoStepInput> {

    void saveList(String objectId, List<AutoStepInput> autoStepInputList);

    void deleteByObjectId(String objectId);

    Map<String, List<AutoStepInput>> selectByObjectId(String objectId);
}