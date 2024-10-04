package com.skyeye.exam.examquchenoption.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examquchenoption.entity.ExamQuChenOption;

public interface ExamQuChenOptionService extends SkyeyeBusinessService<ExamQuChenOption> {

    void queryExamQuChenOptionListById(InputObject inputObject, OutputObject outputObject);
}
