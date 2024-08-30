/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.store.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: StoreIntercourseService
 * @Description: 门店往来管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/10 21:55
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface StoreIntercourseService {

    List<Map<String, Object>> queryStoreIntercourseByDay(String day);

    void insertStoreIntercourse(List<Map<String, Object>> shopStoreIntercourseMationList);

    void queryStoreIntercourseList(InputObject inputObject, OutputObject outputObject);

    void editStoreIntercourseState(InputObject inputObject, OutputObject outputObject);

    List<Map<String, Object>> queryStoreIntercourseListByDay(String day);
}
