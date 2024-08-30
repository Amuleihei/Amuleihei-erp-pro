package com.skyeye.exam.examQuChenrow.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuChenrow.entity.ExamQuChenRow;

public interface ExamQuChenRowService extends SkyeyeBusinessService<ExamQuChenRow> {

    void queryExamQuChenRowListById(InputObject inputObject, OutputObject outputObject);
}
