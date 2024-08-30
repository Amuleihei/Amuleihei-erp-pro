package com.skyeye.exam.examQuChckbox.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuChckbox.entity.ExamQuCheckbox;

public interface ExamQuCheckboxService extends SkyeyeBusinessService<ExamQuCheckbox> {

    void queryExamQuCheckboxListById(InputObject inputObject, OutputObject outputObject);
}
