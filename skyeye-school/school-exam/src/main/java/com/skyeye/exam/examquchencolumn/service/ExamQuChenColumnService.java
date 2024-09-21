package com.skyeye.exam.examquchencolumn.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examquchencolumn.entity.ExamQuChenColumn;

public interface ExamQuChenColumnService extends SkyeyeBusinessService<ExamQuChenColumn> {

    void queryExamQuChenColumnListById(InputObject inputObject, OutputObject outputObject);
}
