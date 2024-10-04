package com.skyeye.school.groups.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.groups.entity.Groups;
import com.skyeye.school.groups.service.GroupsService;
import com.skyeye.school.subject.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "学生分组管理", tags = "学生分组管理", modelName = "分组管理")
public class GroupsController {

    @Autowired
    private GroupsService groupsService;

    /**
     * 获取学生分组列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryGroupsList", value = "获取学生分组列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/GroupsController/queryGroupsList")
    public void queryGroupsList(InputObject inputObject, OutputObject outputObject) {
        groupsService.queryPageList(inputObject, outputObject);
    }

    /**
     * 根据Id删除学生分组
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteGroupsById", value = "根据Id删除学生分组", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/GroupsController/deleteGroupsById")
    public void deleteGroupsById(InputObject inputObject, OutputObject outputObject) {
        groupsService.deleteGroupsById(inputObject, outputObject);
    }

    /**
     * 改变组状态
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "changeState", value = "改变组状态", method = "POST", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "编码", required = "required"),
            @ApiImplicitParam(id = "state", name = "state", value = "组状态", required = "required")})
    @RequestMapping("/post/GroupsController/changeState")
    public void changeState(InputObject inputObject, OutputObject outputObject) {
        groupsService.changeState(inputObject, outputObject);
    }

}
