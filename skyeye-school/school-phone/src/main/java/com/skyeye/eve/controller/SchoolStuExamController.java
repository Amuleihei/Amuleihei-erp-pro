/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SchoolStuExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolStuExamController {

    @Autowired
    private SchoolStuExamService schoolStuExamService;

    /**
     * 获取我的待考试试卷列表
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SchoolStuExamController/queryMyWaitExamList")
    public void queryMyWaitExamList(InputObject inputObject, OutputObject outputObject) {
        schoolStuExamService.queryMyWaitExamList(inputObject, outputObject);
    }

    /**
     * 获取我的已考试试卷列表
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SchoolStuExamController/queryMyEndExamList")
    public void queryMyEndExamList(InputObject inputObject, OutputObject outputObject) {
        schoolStuExamService.queryMyEndExamList(inputObject, outputObject);
    }

}
