/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.dao.NoticeUserDao;
import com.skyeye.eve.entity.NoticeUser;
import com.skyeye.eve.service.NoticeUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: NoticeUserServiceImpl
 * @Description: 公告群发对象管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2024/1/31 10:58
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Service
@SkyeyeService(name = "公告群发对象管理", groupName = "公告管理", manageShow = false)
public class NoticeUserServiceImpl extends SkyeyeBusinessServiceImpl<NoticeUserDao, NoticeUser> implements NoticeUserService {

    @Override
    public void deleteNoticeUserByNoticeId(String noticeId) {
        QueryWrapper<NoticeUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(NoticeUser::getNoticeId), noticeId);
        remove(queryWrapper);
    }

    @Override
    public void saveNoticeUser(String noticeId, List<String> userIdList) {
        deleteNoticeUserByNoticeId(noticeId);
        userIdList = userIdList.stream()
            .filter(userId -> StrUtil.isNotEmpty(userId)).distinct().collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(userIdList)) {
            List<NoticeUser> noticeUserList = new ArrayList<>();
            for (String userId : userIdList) {
                NoticeUser noticeUser = new NoticeUser();
                noticeUser.setUserId(userId);
                noticeUser.setNoticeId(noticeId);
                noticeUserList.add(noticeUser);
            }
            createEntity(noticeUserList, StrUtil.EMPTY);
        }
    }

    @Override
    public List<String> queryNoticeUserByNoticeId(String noticeId) {
        QueryWrapper<NoticeUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(NoticeUser::getNoticeId), noticeId);
        List<NoticeUser> noticeUserList = list(queryWrapper);
        return noticeUserList.stream().map(NoticeUser::getUserId).collect(Collectors.toList());
    }

}
