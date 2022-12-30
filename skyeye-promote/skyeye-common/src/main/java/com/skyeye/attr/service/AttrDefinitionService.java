/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.attr.service;

import com.skyeye.attr.entity.AttrDefinition;
import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AttrDefinitionService
 * @Description: 服务类属性管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 13:11
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface AttrDefinitionService extends SkyeyeBusinessService<AttrDefinition> {

    /**
     * 系统启动时，批量扫描业务对象属性入库
     *
     * @param appId
     * @param attrDefinitionList
     */
    void saveBarchAttrDefinition(String appId, List<AttrDefinition> attrDefinitionList);

    void queryAttrDefinitionList(InputObject inputObject, OutputObject outputObject);

    void queryChildAttrDefinitionList(InputObject inputObject, OutputObject outputObject);

    /**
     * 批量获取业务对象指定的属性信息
     *
     * @param className
     * @param attrKey
     * @return
     */
    List<AttrDefinition> queryAttrDefinitionList(String className, List<String> attrKey);

    Map<String, AttrDefinition> queryAttrDefinitionMap(String className, List<String> attrKey);

    AttrDefinition queryAttrDefinition(String className, String attrKey);
}
