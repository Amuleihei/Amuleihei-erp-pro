package com.skyeye.school.topiccomment.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Joiner;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.rest.wall.user.service.IUserService;
import com.skyeye.school.topic.service.TopicService;
import com.skyeye.school.topiccomment.dao.TopicCommentDao;
import com.skyeye.school.topiccomment.entity.TopicComment;
import com.skyeye.school.topiccomment.service.TopicCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@SkyeyeService(name = "话题评论管理", groupName = "话题评论管理")
public class TopicCommentServiceImpl extends SkyeyeBusinessServiceImpl<TopicCommentDao, TopicComment> implements TopicCommentService {

    @Autowired
    private TopicService topicService;

    @Autowired
    private IUserService iUserService;

    public void createPostpose(TopicComment topicComment, String userId) {
        QueryWrapper<TopicComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(TopicComment::getTopicId), topicComment.getTopicId());
        topicService.updateCommentNum(topicComment.getTopicId(), (int) (count(queryWrapper)));
    }

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        if (CollectionUtil.isEmpty(beans)){
            return CollectionUtil.newArrayList();
        }
        List<String> createIdList = beans.stream()
            .map(bean -> bean.get("createId").toString()).distinct().collect(Collectors.toList());
        Map<String, Map<String, Object>> studentMation = iUserService.queryDataMationForMapByIds(
            Joiner.on(CommonCharConstants.COMMA_MARK).join(createIdList));
        Map<String, Map<String, Object>> userMation = iAuthUserService.queryDataMationForMapByIds(
            Joiner.on(CommonCharConstants.COMMA_MARK).join(createIdList));
        beans.forEach(bean -> {
            String createId = bean.get("createId").toString();
            if (studentMation.containsKey(createId) && studentMation.get(createId) != null){
                bean.put("createMation", studentMation.get(createId));
            } else {
                bean.put("createMation", userMation.get(createId));
            }
        });
        return beans;
    }

    @Override
    public QueryWrapper<TopicComment> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<TopicComment> queryWrapper = super.getQueryWrapper(commonPageInfo);
        if (StrUtil.isNotEmpty(commonPageInfo.getHolderId())) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(TopicComment::getTopicId), commonPageInfo.getHolderId());
        }
        return queryWrapper;
    }

    @Override
    public void deletePostpose(TopicComment topicComment) {
        QueryWrapper<TopicComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(TopicComment::getTopicId), topicComment.getTopicId());
        topicService.updateCommentNum(topicComment.getTopicId(), (int) count(queryWrapper));
    }
}