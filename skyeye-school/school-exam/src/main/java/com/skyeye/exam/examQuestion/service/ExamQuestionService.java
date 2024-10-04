package com.skyeye.exam.examQuestion.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuestion.entity.ExamQuestion;

public interface ExamQuestionService extends SkyeyeBusinessService<ExamQuestion> {
    void deleteQuestionById(InputObject inputObject, OutputObject outputObject);

    void queryQuestionsList(InputObject inputObject, OutputObject outputObject);
}
