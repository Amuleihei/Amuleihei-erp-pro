/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.post.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.post.entity.Post;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: PostDao
 * @Description: 帖子信息数据层
 * @author: xqz
 * @date: 2024/3/9 14:31
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface PostDao extends SkyeyeBaseMapper<Post> {
//    List<Map<String, Object>> queryPostList(CommonPageInfo commonPageInfo);
}