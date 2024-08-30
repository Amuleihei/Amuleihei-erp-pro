/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.entity.Email;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: EmailDao
 * @Description: 邮件管理数据接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/8 9:13
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface EmailDao extends SkyeyeBaseMapper<Email> {

    List<Map<String, Object>> queryEmailListByEmailId(CommonPageInfo commonPageInfo);

    List<Map<String, Object>> queryEmailListByEmailAddress(@Param("userAddress") String userAddress, @Param("state") Integer state);

    int insertEmailListToServer(List<Map<String, Object>> enclosureBeans);

    int insertEmailEnclosureListToServer(List<Map<String, Object>> beans);

    int editEmailMessageIdByEmailId(Map<String, Object> emailEditMessageId);

    List<Map<String, Object>> queryEmailListByEmailFromAddress(@Param("userAddress") String userAddress, @Param("state") Integer state);


}
