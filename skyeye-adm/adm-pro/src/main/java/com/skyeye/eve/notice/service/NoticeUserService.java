/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.notice.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.eve.notice.entity.NoticeUser;

import java.util.List;

/**
 * @ClassName: NoticeUserService
 * @Description: 公告群发对象管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/1/31 10:57
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public interface NoticeUserService extends SkyeyeBusinessService<NoticeUser> {

    void deleteNoticeUserByNoticeId(String noticeId);

    void saveNoticeUser(String noticeId, List<String> userIdList);

    List<String> queryNoticeUserByNoticeId(String noticeId);

}
