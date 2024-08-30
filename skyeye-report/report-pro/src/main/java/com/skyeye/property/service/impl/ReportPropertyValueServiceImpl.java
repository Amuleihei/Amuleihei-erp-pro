/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.property.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.enumeration.WhetherEnum;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.property.dao.ReportPropertyValueDao;
import com.skyeye.property.entity.PropertyValue;
import com.skyeye.property.service.ReportPropertyValueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ReportPropertyValueServiceImpl
 * @Description: 模型---样式属性值服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/26 20:09
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Service
@SkyeyeService(name = "样式属性值管理", groupName = "样式属性管理", manageShow = false)
public class ReportPropertyValueServiceImpl extends SkyeyeBusinessServiceImpl<ReportPropertyValueDao, PropertyValue> implements ReportPropertyValueService {

    @Override
    public void deleteByPropertyId(String propertyId) {
        QueryWrapper<PropertyValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(PropertyValue::getPropertyId), propertyId);
        remove(queryWrapper);
    }

    @Override
    public void save(String propertyId, List<PropertyValue> propertyValueList) {
        deleteByPropertyId(propertyId);
        if (CollectionUtil.isNotEmpty(propertyValueList)) {
            Boolean hasChooseFlag = false;
            Integer orderBy = 1;
            for (PropertyValue propertyValue : propertyValueList) {
                propertyValue.setPropertyId(propertyId);
                if (!hasChooseFlag) {
                    // 还没有设置默认值
                    if (WhetherEnum.ENABLE_USING.getKey().equals(propertyValue.getDefaultChoose())) {
                        // 是默认值
                        hasChooseFlag = true;
                    }
                } else {
                    propertyValue.setDefaultChoose(WhetherEnum.DISABLE_USING.getKey());
                }
                propertyValue.setOrderBy(orderBy);
                orderBy++;
            }
            createEntity(propertyValueList, StrUtil.EMPTY);
        }
    }

    @Override
    public List<PropertyValue> queryByPropertyId(String propertyId) {
        QueryWrapper<PropertyValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(PropertyValue::getPropertyId), propertyId);
        queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(PropertyValue::getOrderBy));
        List<PropertyValue> propertyValueList = list(queryWrapper);
        return propertyValueList;
    }

    @Override
    public Map<String, List<PropertyValue>> queryByPropertyId(List<String> propertyIds) {
        QueryWrapper<PropertyValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(PropertyValue::getPropertyId), propertyIds);
        queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(PropertyValue::getOrderBy));
        List<PropertyValue> propertyValueList = list(queryWrapper);
        return propertyValueList.stream().collect(Collectors.groupingBy(PropertyValue::getPropertyId));
    }

}
