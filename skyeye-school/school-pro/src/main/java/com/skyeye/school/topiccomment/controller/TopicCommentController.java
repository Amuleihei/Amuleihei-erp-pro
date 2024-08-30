package com.skyeye.school.topiccomment.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.topiccomment.entity.TopicComment;
import com.skyeye.school.topiccomment.service.TopicCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TopicCommentController
 * @Description: 话题评论信息管理
 * @author: lyj
 * @date: 2024/7/16 14:31
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "话题评论管理", tags = "话题评论管理", modelName = "话题评论管理")
public class TopicCommentController {

    @Autowired
    private TopicCommentService topicCommentService;
    /**
     * 新增评论信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertTopicComment", value = "新增话题评论信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = TopicComment.class)
    @RequestMapping("/post/TopicCommentController/insertTopicComment")
    public void insertTopicComment(InputObject inputObject, OutputObject outputObject) {
        topicCommentService.createEntity(inputObject, outputObject);
    }

    /**
     * 获取话题评论信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryTopicCommentList", value = "获取话题评论信息列表", method = "POST", allUse = "0")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/TopicCommentController/queryTopicCommentList")
    public void queryTopicCommentList(InputObject inputObject, OutputObject outputObject) {
        topicCommentService.queryPageList(inputObject, outputObject);
    }

    /**
     * 删除话题评论信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteTopicCommentById", value = "根据ID删除话题评论信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/TopicCommentController/deleteTopicCommentById")
    public void deleteTopicCommentById(InputObject inputObject, OutputObject outputObject) {
        topicCommentService.deleteById(inputObject, outputObject);
    }
}
