package com.skyeye.exam.examAnYesno.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examAnYesno.entity.ExamAnYesno;

public interface ExamAnYesnoService extends SkyeyeBusinessService<ExamAnYesno> {

    void queryExamAnYesnoListById(InputObject inputObject, OutputObject outputObject);
}
