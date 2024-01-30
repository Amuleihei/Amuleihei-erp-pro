/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MainPageDao {

    String queryCheckOnWorkNumByUserId(@Param("userId") String userId);

    String queryDiskCloudFileNumByUserId(@Param("userId") String userId);

    String queryForumNumByUserId(@Param("userId") String userId);

    List<Map<String, Object>> queryHotForumList(Map<String, Object> map);

}
