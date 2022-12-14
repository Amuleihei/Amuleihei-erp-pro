/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.attr.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.attr.classenum.TransformShowType;
import com.skyeye.attr.dao.AttrTransformDao;
import com.skyeye.attr.entity.AttrTransform;
import com.skyeye.attr.entity.AttrTransformTable;
import com.skyeye.attr.service.AttrTransformService;
import com.skyeye.attr.service.AttrTransformTableService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: AttrTransformServiceImpl
 * @Description: 提交到流程的属性信息管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 13:11
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class AttrTransformServiceImpl extends SkyeyeBusinessServiceImpl<AttrTransformDao, AttrTransform> implements AttrTransformService {

    @Autowired
    private AttrTransformTableService attrTransformTableService;

    @Override
    public List<Map<String, Object>> queryDataList(InputObject inputObject) {
        Map<String, Object> params = inputObject.getParams();
        String className = params.get("className").toString();
        QueryWrapper<AttrTransform> queryWrapper = new QueryWrapper();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AttrTransform::getClassName), className);
        return list(queryWrapper).stream().map(bean -> BeanUtil.beanToMap(bean)).collect(Collectors.toList());
    }

    @Override
    public void writePostpose(AttrTransform entity, String userId) {
        super.writePostpose(entity, userId);
        if (entity.getShowType().equals(TransformShowType.TABLE.getKey())) {
            attrTransformTableService.saveAttrTransformTable(entity.getClassName(), entity.getAttrKey(), entity.getAttrTransformTableList());
        }
    }

    @Override
    public void deletePreExecution(String id) {
        AttrTransform attrTransform = selectById(id);
        attrTransformTableService.deleteAttrTransformTable(attrTransform.getClassName(), attrTransform.getAttrKey());
    }

    @Override
    public AttrTransform getDataFromDb(String id) {
        AttrTransform attrTransform = super.getDataFromDb(id);
        List<AttrTransformTable> attrTransformTableList = attrTransformTableService
            .queryAttrTransformTable(attrTransform.getClassName(), attrTransform.getAttrKey());
        attrTransform.setAttrTransformTableList(attrTransformTableList);
        return attrTransform;
    }

}
