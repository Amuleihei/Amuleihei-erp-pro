/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysStaffRewardPunishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysStaffRewardPunishController {

    @Autowired
    private SysStaffRewardPunishService sysStaffRewardPunishService;

    /**
     * 查询所有奖惩列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffRewardPunishController/queryAllSysStaffRewardPunishList")
    public void queryAllSysStaffRewardPunishList(InputObject inputObject, OutputObject outputObject) {
        sysStaffRewardPunishService.queryAllSysStaffRewardPunishList(inputObject, outputObject);
    }

    /**
     * 员工奖惩信息录入
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffRewardPunishController/insertSysStaffRewardPunishMation")
    public void insertSysStaffRewardPunishMation(InputObject inputObject, OutputObject outputObject) {
        sysStaffRewardPunishService.insertSysStaffRewardPunishMation(inputObject, outputObject);
    }

    /**
     * 编辑员工奖惩信息时回显
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffRewardPunishController/querySysStaffRewardPunishMationToEdit")
    public void querySysStaffRewardPunishMationToEdit(InputObject inputObject, OutputObject outputObject) {
        sysStaffRewardPunishService.querySysStaffRewardPunishMationToEdit(inputObject, outputObject);
    }

    /**
     * 编辑员工奖惩信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffRewardPunishController/editSysStaffRewardPunishMationById")
    public void editSysStaffRewardPunishMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffRewardPunishService.editSysStaffRewardPunishMationById(inputObject, outputObject);
    }

    /**
     * 删除员工奖惩信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffRewardPunishController/deleteSysStaffRewardPunishMationById")
    public void deleteSysStaffRewardPunishMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffRewardPunishService.deleteSysStaffRewardPunishMationById(inputObject, outputObject);
    }

    /**
     * 查询指定员工的奖惩列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffRewardPunishController/queryPointStaffSysStaffRewardPunishList")
    public void queryPointStaffSysStaffRewardPunishList(InputObject inputObject, OutputObject outputObject) {
        sysStaffRewardPunishService.queryPointStaffSysStaffRewardPunishList(inputObject, outputObject);
    }

}
