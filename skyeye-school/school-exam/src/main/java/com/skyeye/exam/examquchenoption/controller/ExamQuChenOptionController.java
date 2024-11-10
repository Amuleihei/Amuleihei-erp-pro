package com.skyeye.exam.examquchenoption.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examquchenoption.entity.ExamQuChenOption;
import com.skyeye.exam.examquchenoption.service.ExamQuChenOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "矩陈题-题选项管理", tags = "矩陈题-题选项管理", modelName = "矩陈题-题选项管理")
public class ExamQuChenOptionController {

    @Autowired
    private ExamQuChenOptionService examQuChenOptionService;



}
