/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.activiti.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 自定义activiti mapper接口
 */
public interface ActivityMapper {

    void updateProcessDefinitionName(@Param("name") String name, @Param("processDefinitionId") String processDefinitionId);

    void deleteHisActivityInstanceByTaskId(@Param("taskId") String taskId);

    void deleteHisTaskInstanceByTaskId(@Param("taskId") String taskId);

}
