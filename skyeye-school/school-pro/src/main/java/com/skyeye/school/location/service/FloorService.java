package com.skyeye.school.location.service;


import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.location.entity.Floor;

/**
 * @ClassName: FloorService
 * @Description: 楼层教室管理服务层
 * @author: skyeye云系列--lqy
 * @date: 2024/11/6 14:55
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface FloorService extends SkyeyeBusinessService<Floor> {
    void queryFloorListByHolderId(InputObject inputObject, OutputObject outputObject);

    void queryClassesByFloorNumAndLocationId(InputObject inputObject, OutputObject outputObject);
}
