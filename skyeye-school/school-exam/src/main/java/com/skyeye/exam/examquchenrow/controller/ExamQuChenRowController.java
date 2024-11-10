package com.skyeye.exam.examquchenrow.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.exam.examquchenrow.service.ExamQuChenRowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "矩陈题-行选项管理", tags = "矩陈题-行选项管理", modelName = "矩陈题-行选项管理")
public class ExamQuChenRowController {

    @Autowired
    private ExamQuChenRowService examQuChenRowService;



}
