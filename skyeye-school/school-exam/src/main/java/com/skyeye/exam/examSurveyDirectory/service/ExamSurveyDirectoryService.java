package com.skyeye.exam.examSurveyDirectory.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examSurveyDirectory.entity.ExamSurveyDirectory;

public interface ExamSurveyDirectoryService extends SkyeyeBusinessService<ExamSurveyDirectory> {

    void queryAllOrMyExamList(InputObject inputObject, OutputObject outputObject);

    void deleteDirectoryById(InputObject inputObject, OutputObject outputObject);
}
