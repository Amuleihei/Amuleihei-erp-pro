/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.teaching.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.teaching.entity.Teaching;
import com.skyeye.school.teaching.service.TeachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TeachingController
 * @Description: 授课管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/29 17:41
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "授课管理", tags = "授课管理", modelName = "授课管理")
public class TeachingController {

    @Autowired
    private TeachingService teachingService;

    /**
     * 获取授课列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryTeachingList", value = "获取授课信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/TeachingController/queryTeachingList")
    public void queryTeachingList(InputObject inputObject, OutputObject outputObject) {
        teachingService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加或修改授课
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeTeaching", value = "新增/编辑授课信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = Teaching.class)
    @RequestMapping("/post/TeachingController/writeTeaching")
    public void writeTeaching(InputObject inputObject, OutputObject outputObject) {
        teachingService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除授课信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteTeachingById", value = "根据ID删除授课信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/TeachingController/deleteTeachingById")
    public void deleteTeachingById(InputObject inputObject, OutputObject outputObject) {
        teachingService.deleteById(inputObject, outputObject);
    }

}
