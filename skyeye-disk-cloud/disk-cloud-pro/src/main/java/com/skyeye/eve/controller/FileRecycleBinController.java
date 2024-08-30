/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.FileRecycleBin;
import com.skyeye.eve.service.FileRecycleBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: FileRecycleBinController
 * @Description: 回收站控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/17 22:29
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@RestController
@Api(value = "回收站管理", tags = "回收站管理", modelName = "回收站管理")
public class FileRecycleBinController {

    @Autowired
    private FileRecycleBinService fileRecycleBinService;

    /**
     * 加入回收站
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertFileCatalogToRecycle", value = "加入回收站", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = FileRecycleBin.class)
    @RequestMapping("/post/FileRecycleBinController/insertFileCatalogToRecycle")
    public void insertFileCatalogToRecycle(InputObject inputObject, OutputObject outputObject) {
        fileRecycleBinService.createEntity(inputObject, outputObject);
    }

    /**
     * 我的回收站
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "fileconsole014", value = "我的回收站", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/FileRecycleBinController/queryFileRecycleBinList")
    public void queryFileRecycleBinList(InputObject inputObject, OutputObject outputObject) {
        fileRecycleBinService.queryPageList(inputObject, outputObject);
    }

    /**
     * 根据id从回收站删除数据
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteFileRecycleBinById", value = "根据id从回收站删除数据", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/FileRecycleBinController/deleteFileRecycleBinById")
    public void deleteFileRecycleBinById(InputObject inputObject, OutputObject outputObject) {
        fileRecycleBinService.deleteById(inputObject, outputObject);
    }

}
