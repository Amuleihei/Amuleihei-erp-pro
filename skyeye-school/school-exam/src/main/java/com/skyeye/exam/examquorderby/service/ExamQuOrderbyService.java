package com.skyeye.exam.examquorderby.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examquorderby.entity.ExamQuOrderby;

import java.util.List;

public interface ExamQuOrderbyService extends SkyeyeBusinessService<ExamQuOrderby> {

    void saveList(List<ExamQuOrderby> score, String quId, String userId);
}
