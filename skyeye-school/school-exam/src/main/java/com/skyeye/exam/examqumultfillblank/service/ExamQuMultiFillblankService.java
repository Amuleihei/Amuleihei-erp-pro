package com.skyeye.exam.examqumultfillblank.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examqumultfillblank.entity.ExamQuMultiFillblank;

public interface ExamQuMultiFillblankService extends SkyeyeBusinessService<ExamQuMultiFillblank> {

    void queryExamQuMultiFillblankListById(InputObject inputObject, OutputObject outputObject);
}
