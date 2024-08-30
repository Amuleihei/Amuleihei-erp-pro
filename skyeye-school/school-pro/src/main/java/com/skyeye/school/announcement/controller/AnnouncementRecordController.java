package com.skyeye.school.announcement.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.announcement.service.AnnouncementRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AnnouncementRecordController
 * @Description: 公告收到记录管理控制层
 * @author: skyeye云系列--lqy
 * @date: 2024/7/19 11:01
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */

@RestController
@Api(value = "公告收到记录管理", tags = "公告收到记录管理", modelName = "公告收到记录管理")
public class AnnouncementRecordController {

    @Autowired
    private AnnouncementRecordService announcementRecordService;

    /**
     * 根据announcementId查询公告记录信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryRecordByAnnouncementId", value = "根据announcementId查询公告记录信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "announcementId", name = "announcementId", value = "公告id", required = "required")})
    @RequestMapping("/post/AnnouncementRecordController/queryRecordByAnnouncementId")
    public void queryRecordByAnnouncementId(InputObject inputObject, OutputObject outputObject) {
        announcementRecordService.queryRecordByAnnouncementId(inputObject, outputObject);
    }

    /**
     * 根据announcementId查询未确认的学生信息公告记录信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryUnConfirmRecordByAnnouncementId", value = "根据subjectClassesId查询未确认的学生信息公告记录信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "announcementId", name = "announcementId", value = "公告id", required = "required")})
    @RequestMapping("/post/AnnouncementRecordController/queryUnConfirmRecordByAnnouncementId")
    public void queryUnConfirmRecordByAnnouncementId(InputObject inputObject, OutputObject outputObject) {
        announcementRecordService.queryUnConfirmRecordByAnnouncementId(inputObject, outputObject);
    }
}
