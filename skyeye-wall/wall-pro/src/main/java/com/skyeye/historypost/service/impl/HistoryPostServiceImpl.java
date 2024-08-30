/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.historypost.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.historypost.dao.HistoryPostDao;
import com.skyeye.historypost.entity.HistoryPost;
import com.skyeye.historypost.service.HistoryPostService;
import com.skyeye.popularpost.service.PopularPostService;
import com.skyeye.post.entity.Post;
import com.skyeye.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: HistoryPostServiceImpl
 * @Description: 历史帖子信息管理
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/9 14:31
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "历史帖子管理", groupName = "历史帖子管理")
public class HistoryPostServiceImpl extends SkyeyeBusinessServiceImpl<HistoryPostDao, HistoryPost> implements HistoryPostService {

    @Autowired
    private PostService postService;

    @Override
    public List<HistoryPost> getHistoryPostById(String userId) {
        QueryWrapper<HistoryPost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(HistoryPost::getCreateId), userId);
        queryWrapper.select(MybatisPlusUtil.toColumns(HistoryPost::getPostId));
        queryWrapper.groupBy(MybatisPlusUtil.toColumns(HistoryPost::getPostId));
        List<HistoryPost> historyPostList = list(queryWrapper);
        return historyPostList;
    }


    @Autowired
    private PopularPostService popularPostService;

    @Override
    public String createEntity(HistoryPost entity, String userId) {
        popularPostService.insertPopularPostList();
        QueryWrapper<HistoryPost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(HistoryPost::getPostId), entity.getPostId());
        queryWrapper.eq(MybatisPlusUtil.toColumns(HistoryPost::getCreateId), userId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(HistoryPost::getCreateTime), DateUtil.getYmdTimeAndToString());
        long length = count(queryWrapper);
        if (length > 0) {
            return StrUtil.EMPTY;
        }
        return super.createEntity(entity, userId);
    }

    @Override
    public void createPrepose(HistoryPost historyPost) {
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        historyPost.setCreateId(userId);
        historyPost.setCreateTime(DateUtil.getYmdTimeAndToString());
    }

    @Override
    public void createPostpose(HistoryPost historyPost, String userId) {
        Post post = postService.selectById(historyPost.getPostId());
        if (post.getViewNum() != null) {
            Integer flag = Integer.parseInt(post.getViewNum()) + CommonNumConstants.NUM_ONE;
            postService.updateViewCount(historyPost.getPostId(), String.valueOf(flag));
        } else {
            postService.updateViewCount(historyPost.getPostId(), String.valueOf(CommonNumConstants.NUM_ZERO));
        }
    }

    @Override
    public void deleteMyHistoryPost(InputObject inputObject, OutputObject outputObject) {
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        QueryWrapper<HistoryPost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(HistoryPost::getCreateId), userId);
        remove(queryWrapper);
    }

    @Override
    public void deleteHistoryPostByIds(InputObject inputObject, OutputObject outputObject) {
        String ids = inputObject.getParams().get("ids").toString();
        List<String> idList = Arrays.asList(ids.split(CommonCharConstants.COMMA_MARK));
        idList = idList.stream().filter(StrUtil::isNotEmpty).distinct().collect(Collectors.toList());
        if (CollectionUtil.isEmpty(idList)) {
            return;
        }
        QueryWrapper<HistoryPost> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(CommonConstants.ID, idList);
        remove(queryWrapper);
        outputObject.setBeans(idList);
        outputObject.settotal(idList.size());
    }
}