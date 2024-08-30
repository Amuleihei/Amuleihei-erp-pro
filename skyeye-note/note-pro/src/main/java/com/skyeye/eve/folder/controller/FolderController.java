/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.folder.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.folder.entity.Folder;
import com.skyeye.eve.folder.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: FolderController
 * @Description: 笔记文件夹管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/1/25 19:26
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@RestController
@Api(value = "笔记文件夹管理", tags = "笔记文件夹管理", modelName = "笔记管理")
public class FolderController {

    @Autowired
    private FolderService folderService;

    /**
     * 新增/编辑文件夹
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeFolder", value = "新增/编辑文件夹", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Folder.class)
    @RequestMapping("/post/FolderController/writeFolder")
    public void writeFolder(InputObject inputObject, OutputObject outputObject) {
        folderService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 获取当前用户的文件夹
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryFolderByUserId", value = "获取当前用户的文件夹", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "parentId", name = "parentId", value = "父文件夹id，默认为0", required = "required"),
        @ApiImplicitParam(id = "moveId", name = "moveId", value = "移动节点id")})
    @RequestMapping("/post/FolderController/queryFolderByUserId")
    public void queryFolderByUserId(InputObject inputObject, OutputObject outputObject) {
        folderService.queryFolderByUserId(inputObject, outputObject);
    }

}
