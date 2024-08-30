package com.skyeye.exam.examQuChencolumn.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuChencolumn.entity.ExamQuChenColumn;

public interface ExamQuChenColumnService extends SkyeyeBusinessService<ExamQuChenColumn> {

    void queryExamQuChenColumnListById(InputObject inputObject, OutputObject outputObject);
}
