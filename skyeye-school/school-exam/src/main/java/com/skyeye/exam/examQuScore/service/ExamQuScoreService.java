package com.skyeye.exam.examQuScore.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuScore.entity.ExamQuScore;

public interface ExamQuScoreService extends SkyeyeBusinessService<ExamQuScore> {
    void deleteQuScoreById(InputObject inputObject, OutputObject outputObject);

    void queryQuScoreListByQuId(InputObject inputObject, OutputObject outputObject);
}
