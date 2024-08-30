package com.skyeye.exam.examQuMultfillblank.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuMultfillblank.entity.ExamQuMultiFillblank;

public interface ExamQuMultiFillblankService extends SkyeyeBusinessService<ExamQuMultiFillblank> {

    void queryExamQuMultiFillblankListById(InputObject inputObject, OutputObject outputObject);
}
