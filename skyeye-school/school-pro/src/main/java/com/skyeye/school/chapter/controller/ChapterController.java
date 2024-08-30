/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.chapter.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.chapter.entity.Chapter;
import com.skyeye.school.chapter.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ChapterController
 * @Description: 章节控制层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/25 15:29
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "章节管理", tags = "章节管理", modelName = "章节管理")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    /**
     * 添加或修改章节
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeChapter", value = "新增/编辑章节信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Chapter.class)
    @RequestMapping("/post/ChapterController/writeChapter")
    public void writeChapter(InputObject inputObject, OutputObject outputObject) {
        chapterService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id查询章节信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryChapterById", value = "根据id查询章节信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ChapterController/queryChapterById")
    public void queryChapterById(InputObject inputObject, OutputObject outputObject) {
        chapterService.selectById(inputObject, outputObject);
    }

    /**
     * 删除章节信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteChapterById", value = "根据ID删除章节信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ChapterController/deleteChapterById")
    public void deleteChapterById(InputObject inputObject, OutputObject outputObject) {
        chapterService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据科目表与班级表的关系id获取章节列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryChapterListBySubjectClassesId", value = "根据科目表与班级表的关系id获取章节列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "subjectClassesId", name = "subjectClassesId", value = "科目表与班级表的关系id", required = "required")})
    @RequestMapping("/post/ChapterController/queryChapterListBySubjectClassesId")
    public void queryChapterListBySubjectClassesId(InputObject inputObject, OutputObject outputObject) {
        chapterService.queryChapterListBySubjectClassesId(inputObject, outputObject);
    }
}
