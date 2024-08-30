package com.skyeye.exam.examQuOrderby.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuOrderby.entity.ExamQuOrderby;

public interface ExamQuOrderbyService extends SkyeyeBusinessService<ExamQuOrderby> {

    void queryExamQuOrderbyListById(InputObject inputObject, OutputObject outputObject);
}
