/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.JobDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobDiaryController {

    @Autowired
    private JobDiaryService jobDiaryService;

    /**
     * 遍历我收到的日志
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/queryJobDiaryDayReceived")
    public void queryJobDiaryDayReceived(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.queryJobDiaryDayReceived(inputObject, outputObject);
    }

    /**
     * 发表日志
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/insertDayJobDiary")
    public void insertDayJobDiary(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.insertDayJobDiary(inputObject, outputObject);
    }

    /**
     * 查出所有有账户的员工
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/querySysEveUserStaff")
    public void querySysEveUserStaff(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.querySysEveUserStaff(inputObject, outputObject);
    }

    /**
     * 日报详情
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/queryJobDiaryDetails")
    public void queryJobDiaryDetails(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.queryJobDiaryDetails(inputObject, outputObject);
    }

    /**
     * 遍历我发出的所有日志
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/queryJobDiaryDayMysend")
    public void queryJobDiaryDayMysend(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.queryJobDiaryDayMysend(inputObject, outputObject);
    }

    /**
     * 撤销我发出的日志
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/deleteJobDiaryDayMysend")
    public void deleteJobDiaryDayMysend(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.deleteJobDiaryDayMysend(inputObject, outputObject);
    }

    /**
     * 阅读我发出的日报详情
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/selectMysendDetails")
    public void selectMysendDetails(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.selectMysendDetails(inputObject, outputObject);
    }

    /**
     * 删除我收到的日志
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/editMyReceivedJobDiary")
    public void editMyReceivedJobDiary(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.editMyReceivedJobDiary(inputObject, outputObject);
    }

    /**
     * 发表周报
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/insertWeekJobDiary")
    public void insertWeekJobDiary(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.insertWeekJobDiary(inputObject, outputObject);
    }

    /**
     * 阅读我发出的周报详情
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/selectMysendWeekDetails")
    public void selectMysendWeekDetails(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.selectMysendWeekDetails(inputObject, outputObject);
    }

    /**
     * 阅读我收到的周报
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/queryWeekJobDiaryDetails")
    public void queryWeekJobDiaryDetails(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.queryWeekJobDiaryDetails(inputObject, outputObject);
    }

    /**
     * 发表月报
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/insertMonthJobDiary")
    public void insertMonthJobDiary(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.insertMonthJobDiary(inputObject, outputObject);
    }

    /**
     * 阅读我发出的月报详情
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/selectMysendMonthDetails")
    public void selectMysendMonthDetails(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.selectMysendMonthDetails(inputObject, outputObject);
    }

    /**
     * 阅读我收到的月报
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/queryMonthJobDiaryDetails")
    public void queryMonthJobDiaryDetails(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.queryMonthJobDiaryDetails(inputObject, outputObject);
    }

    /**
     * 删除我发出的日志
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/editJobDiaryDayMysend")
    public void editJobDiaryDayMysend(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.editJobDiaryDayMysend(inputObject, outputObject);
    }

    /**
     * 回显我撤回的日报
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/queryJobDiaryDayMysendToEdit")
    public void queryJobDiaryDayMysendToEdit(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.queryJobDiaryDayMysendToEdit(inputObject, outputObject);
    }

    /**
     * 提交撤回的日报
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/editDayJobDiary")
    public void editDayJobDiary(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.editDayJobDiary(inputObject, outputObject);
    }

    /**
     * 回显我撤回的周报
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/queryWeekJobDiaryDayMysendToEdit")
    public void queryWeekJobDiaryDayMysendToEdit(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.queryWeekJobDiaryDayMysendToEdit(inputObject, outputObject);
    }

    /**
     * 提交撤回的周报
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/editWeekDayJobDiary")
    public void editWeekDayJobDiary(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.editWeekDayJobDiary(inputObject, outputObject);
    }

    /**
     * 回显我撤回的月报
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/queryMonthJobDiaryDayMysendToEdit")
    public void queryMonthJobDiaryDayMysendToEdit(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.queryMonthJobDiaryDayMysendToEdit(inputObject, outputObject);
    }

    /**
     * 提交撤回的月报
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/editMonthDayJobDiary")
    public void editMonthDayJobDiary(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.editMonthDayJobDiary(inputObject, outputObject);
    }

    /**
     * 查询日志类型各个类型的条数
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/queryJobDiaryDayNumber")
    public void queryJobDiaryDayNumber(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.queryJobDiaryDayNumber(inputObject, outputObject);
    }

    /**
     * 获取日志列表展示位时间树
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/queryJobDiaryListToTimeTree")
    public void queryJobDiaryListToTimeTree(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.queryJobDiaryListToTimeTree(inputObject, outputObject);
    }

    /**
     * 我收到的日志全部设置为已读
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/JobDiaryController/editReceivedJobDiaryToAlreadyRead")
    public void editReceivedJobDiaryToAlreadyRead(InputObject inputObject, OutputObject outputObject) {
        jobDiaryService.editReceivedJobDiaryToAlreadyRead(inputObject, outputObject);
    }

}
