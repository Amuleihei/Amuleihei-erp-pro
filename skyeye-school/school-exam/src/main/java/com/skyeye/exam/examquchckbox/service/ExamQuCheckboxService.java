package com.skyeye.exam.examquchckbox.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examquchckbox.entity.ExamQuCheckbox;

public interface ExamQuCheckboxService extends SkyeyeBusinessService<ExamQuCheckbox> {

    void queryExamQuCheckboxListById(InputObject inputObject, OutputObject outputObject);
}
