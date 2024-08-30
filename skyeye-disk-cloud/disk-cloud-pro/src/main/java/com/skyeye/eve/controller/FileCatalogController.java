/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.FileCatalog;
import com.skyeye.eve.service.FileCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: FileCatalogController
 * @Description: 文件夹管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/17 11:28
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@RestController
@Api(value = "文件夹管理", tags = "文件夹管理", modelName = "文件夹管理")
public class FileCatalogController {

    @Autowired
    private FileCatalogService fileCatalogService;

    /**
     * 新增文件夹
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertFileCatalog", value = "新增文件夹", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = FileCatalog.class)
    @RequestMapping("/post/FileCatalogController/insertFileCatalog")
    public void insertFileCatalog(InputObject inputObject, OutputObject outputObject) {
        fileCatalogService.createEntity(inputObject, outputObject);
    }

}
