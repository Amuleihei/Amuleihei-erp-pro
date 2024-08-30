/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.service.SchoolTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolTeacherController {

    @Autowired
    private SchoolTeacherService schoolTeacherService;

    /**
     * 获取教师列表
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SchoolTeacherController/querySchoolTeacherList")
    public void querySchoolTeacherList(InputObject inputObject, OutputObject outputObject) {
        schoolTeacherService.querySchoolTeacherList(inputObject, outputObject);
    }

    /**
     * 获取教师列表供table表格选择
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SchoolTeacherController/querySchoolTeacherToTableList")
    public void querySchoolTeacherToTableList(InputObject inputObject, OutputObject outputObject) {
        schoolTeacherService.querySchoolTeacherToTableList(inputObject, outputObject);
    }

    /**
     * 根据staffId串获取教师列表详情
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SchoolTeacherController/querySchoolTeacherListByStaffIds")
    public void querySchoolTeacherListByStaffIds(InputObject inputObject, OutputObject outputObject) {
        schoolTeacherService.querySchoolTeacherListByStaffIds(inputObject, outputObject);
    }

}
