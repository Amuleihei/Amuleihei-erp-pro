package com.skyeye.school.location.service.impl;


import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.school.location.dao.LocationTypeDao;
import com.skyeye.school.location.entity.LocationType;
import com.skyeye.school.location.service.LocationTypeService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: LocationTypeServiceImpl
 * @Description: 地点类型管理服务层实现类
 * @author: skyeye云系列--lqy
 * @date: 2024/11/6 14:55
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "地点类型管理", groupName = "地点类型管理")
public class LocationTypeServiceImpl extends SkyeyeBusinessServiceImpl<LocationTypeDao, LocationType> implements LocationTypeService {
}
