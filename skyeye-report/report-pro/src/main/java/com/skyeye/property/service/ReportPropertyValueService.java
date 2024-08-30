/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.property.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.property.entity.PropertyValue;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ReportPropertyValueService
 * @Description: 模型---样式属性值服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/26 20:08
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public interface ReportPropertyValueService extends SkyeyeBusinessService<PropertyValue> {

    void deleteByPropertyId(String propertyId);

    void save(String propertyId, List<PropertyValue> propertyValueList);

    List<PropertyValue> queryByPropertyId(String propertyId);

    Map<String, List<PropertyValue>> queryByPropertyId(List<String> propertyIds);

}
