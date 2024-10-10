/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.skyeye.eve.entity.CodeModelGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CodeModelGroupDao
 * @Description: 模板分组管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 11:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface CodeModelGroupDao extends SkyeyeBaseMapper<CodeModelGroup> {

    Map<String, Object> queryCodeModelNumById(@Param("groupId") String groupId);

    List<Map<String, Object>> queryTableParameterByTableName(Map<String, Object> map);

    List<Map<String, Object>> queryCodeModelListByGroupId(Map<String, Object> map);

    Map<String, Object> queryTableBzByTableName(Map<String, Object> map);

}
