package com.skyeye.school.location.service;


import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.location.entity.Location;

/**
 * @ClassName: LocationServeDao
 * @Description: 地点管理数据层
 * @author: skyeye云系列--lqy
 * @date: 2024/11/6 11:40
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface LocationService extends SkyeyeBusinessService<Location> {
    void queryLocationListByHolderId(InputObject inputObject, OutputObject outputObject);
}
