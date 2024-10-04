package com.skyeye.exam.examSurveyAnswer.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examSurveyAnswer.entity.ExamSurveyAnswer;

public interface ExamSurveyAnswerService extends SkyeyeBusinessService<ExamSurveyAnswer> {
    void queryMySurveyAnswerList(InputObject inputObject, OutputObject outputObject);
}
