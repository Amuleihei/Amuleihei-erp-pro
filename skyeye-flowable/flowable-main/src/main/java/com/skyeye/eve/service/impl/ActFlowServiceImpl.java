/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.activiti.service.ActivitiModelService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.dao.ActFlowDao;
import com.skyeye.eve.entity.ActFlowMation;
import com.skyeye.eve.service.ActFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ActFlowServiceImpl
 * @Description: 流程模型管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2022/10/4 22:53
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class ActFlowServiceImpl extends SkyeyeBusinessServiceImpl<ActFlowDao, ActFlowMation> implements ActFlowService {

    @Autowired
    private ActivitiModelService activitiModelService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        // 设置流程模型信息
        activitiModelService.setActivitiModelList(beans);
        return beans;
    }

    @Override
    public void createPrepose(ActFlowMation entity) {
        // 新增工作流模型信息
        String modelId = activitiModelService.insertNewActivitiModel(entity.getFlowName(), entity.getModelKey());
        entity.setModelId(modelId);
    }

    @Override
    public void updatePostpose(ActFlowMation entity, String userId) {
        // 修改工作流模型信息
        activitiModelService.editModelByModelId(entity.getModelId(), entity.getFlowName(), entity.getModelKey());
    }

    @Override
    public void deletePreExecution(String id) {
        ActFlowMation actFlowMation = selectById(id);
        // 删除模型信息
        activitiModelService.deleteActivitiModelById(actFlowMation.getModelId());
    }

    /**
     * 根据id批量获取工作流模型信息
     *
     * @param ids
     * @return
     */
    @Override
    public Map<String, ActFlowMation> actIdToFlowNameByIds(List<String> ids) {
        ids = ids.stream().filter(StrUtil::isNotEmpty).distinct().collect(Collectors.toList());
        if (CollectionUtil.isEmpty(ids)) {
            return new HashMap<>();
        }
        List<ActFlowMation> actFlowMationList = selectByIds(ids.toArray(new String[]{}));
        Map<String, ActFlowMation> actFlowMationMap = actFlowMationList.stream().collect(Collectors.toMap(bean -> bean.getId(), bean -> bean));
        ids.forEach(id -> {
            if (!actFlowMationMap.containsKey(id)) {
                actFlowMationMap.put(id, new ActFlowMation());
            }
        });
        return actFlowMationMap;
    }

    /**
     * 根据服务类名获取流程模型信息
     *
     * @param serviceClassName 服务类名
     * @return
     */
    @Override
    public ActFlowMation getActFlow(String serviceClassName) {
        QueryWrapper<ActFlowMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ActFlowMation::getApplyServiceClassName), serviceClassName);
        return getOne(queryWrapper, false);
    }

    /**
     * 根据适用对象获取流程模型列表
     *
     * @param inputObject 入参以及用户信息等获取对象
     */
    @Override
    public List<Map<String, Object>> queryDataList(InputObject inputObject) {
        String className = inputObject.getParams().get("className").toString();
        QueryWrapper<ActFlowMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ActFlowMation::getApplyServiceClassName), className);
        List<ActFlowMation> list = list(queryWrapper);
        List<Map<String, Object>> beans = JSONUtil.toList(JSONUtil.toJsonStr(list), null);
        // 设置流程模型信息
        activitiModelService.setActivitiModelList(beans);
        return beans;
    }

}
