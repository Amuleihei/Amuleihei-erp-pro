package com.skyeye.exam.examanradio.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examanradio.entity.ExamAnRadio;

public interface ExamAnRadioService extends SkyeyeBusinessService<ExamAnRadio> {

    void queryExamAnRadioListById(InputObject inputObject, OutputObject outputObject);
}
