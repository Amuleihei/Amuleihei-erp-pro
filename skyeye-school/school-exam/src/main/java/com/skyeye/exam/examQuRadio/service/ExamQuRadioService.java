package com.skyeye.exam.examQuRadio.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuRadio.entity.ExamQuRadio;

public interface ExamQuRadioService extends SkyeyeBusinessService<ExamQuRadio> {
    void deleteQuRadioById(InputObject inputObject, OutputObject outputObject);

    void queryQuRadioListByQuId(InputObject inputObject, OutputObject outputObject);
}
