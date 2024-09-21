package com.skyeye.exam.examquorderby.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examquorderby.entity.ExamQuOrderby;

public interface ExamQuOrderbyService extends SkyeyeBusinessService<ExamQuOrderby> {

    void queryExamQuOrderbyListById(InputObject inputObject, OutputObject outputObject);
}
