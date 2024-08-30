package com.skyeye.school.topic.controller;

import com.skyeye.annotation.api.*;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.topic.entity.Topic;
import com.skyeye.school.topic.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TopicController
 * @Description: 话题信息管理
 * @author: lyj
 * @date: 2024/7/16 14:31
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "话题管理", tags = "话题管理", modelName = "话题管理")
public class TopicController {

    @Autowired
    private TopicService topicService;

    /**
     * 获取话题信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryTopicList", value = "获取话题信息列表", method = "POST", allUse = "0")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/TopicController/queryTopicList")
    public void queryTopicList(InputObject inputObject, OutputObject outputObject) {
        topicService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增话题信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertTopic", value = "新增话题信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Topic.class)
    @RequestMapping("/post/TopicController/insertTopic")
    public void insertTopic(InputObject inputObject, OutputObject outputObject) {
        topicService.createEntity(inputObject, outputObject);
    }

    /**
     * 删除话题信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteTopicById", value = "根据ID删除话题信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/TopicController/deleteTopicById")
    public void deleteTopicById(InputObject inputObject, OutputObject outputObject) {
        topicService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据ID获取话题信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryTopicById", value = "根据ID获取话题信息", method = "POST", allUse = "0")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/TopicController/queryTopicById")
    public void queryTopicById(InputObject inputObject, OutputObject outputObject) {
        topicService.selectById(inputObject, outputObject);
    }
}
