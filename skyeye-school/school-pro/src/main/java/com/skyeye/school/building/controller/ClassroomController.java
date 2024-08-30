/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.building.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.building.entity.Classroom;
import com.skyeye.school.building.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ClassroomController
 * @Description: 教室管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/9/5 17:12
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "教室管理", tags = "教室管理", modelName = "教学楼管理")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    /**
     * 获取教室信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryClassroomList", value = "获取教室信息", method = "POST", allUse = "1")
//    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/ClassroomController/queryClassroomList")
    public void queryClassroomList(InputObject inputObject, OutputObject outputObject) {
        classroomService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加或修改教室信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeClassroom", value = "新增/编辑教室信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = Classroom.class)
    @RequestMapping("/post/ClassroomController/writeClassroom")
    public void writeClassroom(InputObject inputObject, OutputObject outputObject) {
        classroomService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除教室信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteClassroomById", value = "根据ID删除教室信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ClassroomController/deleteClassroomById")
    public void deleteClassroomById(InputObject inputObject, OutputObject outputObject) {
        classroomService.deleteById(inputObject, outputObject);
    }

}
