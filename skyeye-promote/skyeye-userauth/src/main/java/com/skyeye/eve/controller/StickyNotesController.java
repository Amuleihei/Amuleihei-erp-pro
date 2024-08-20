/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.StickyNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: StickyNotesController
 * @Description: 便签模块控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/20 22:00
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "便签模块", tags = "便签模块", modelName = "便签模块")
public class StickyNotesController {

    @Autowired
    private StickyNotesService stickyNotesService;

    /**
     * 新增便签
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/StickyNotesController/insertStickyNotesMation")
    public void insertStickyNotesMation(InputObject inputObject, OutputObject outputObject) {
        stickyNotesService.insertStickyNotesMation(inputObject, outputObject);
    }

    /**
     * 查询便签
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryStickyNotesList", value = "查询便签", method = "GET", allUse = "2")
    @RequestMapping("/post/StickyNotesController/queryStickyNotesList")
    public void queryStickyNotesList(InputObject inputObject, OutputObject outputObject) {
        stickyNotesService.queryStickyNotesList(inputObject, outputObject);
    }

    /**
     * 编辑便签
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/StickyNotesController/editStickyNotesMation")
    public void editStickyNotesMation(InputObject inputObject, OutputObject outputObject) {
        stickyNotesService.editStickyNotesMation(inputObject, outputObject);
    }

    /**
     * 删除便签
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/StickyNotesController/deleteStickyNotesMation")
    public void deleteStickyNotesMation(InputObject inputObject, OutputObject outputObject) {
        stickyNotesService.deleteStickyNotesMation(inputObject, outputObject);
    }

}
