package com.skyeye.exam.examquchenrow.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examquchenrow.entity.ExamQuChenRow;

public interface ExamQuChenRowService extends SkyeyeBusinessService<ExamQuChenRow> {

    void queryExamQuChenRowListById(InputObject inputObject, OutputObject outputObject);
}
