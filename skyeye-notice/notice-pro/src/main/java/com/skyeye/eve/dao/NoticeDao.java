/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: NoticeDao
 * @Description: 公告管理数据交互层
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 21:36
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface NoticeDao extends SkyeyeBaseMapper<Notice> {

    List<Map<String, Object>> queryNoticeList(CommonPageInfo pageInfo);

    List<Map<String, Object>> queryAllUserList(@Param("userIds") List<String> userIds, @Param("stateList") List<String> stateList);

}
