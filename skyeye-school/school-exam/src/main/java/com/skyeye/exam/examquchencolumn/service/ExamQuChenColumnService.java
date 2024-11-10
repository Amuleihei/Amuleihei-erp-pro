package com.skyeye.exam.examquchencolumn.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.exam.examquchencolumn.entity.ExamQuChenColumn;
import com.skyeye.exam.examquchenrow.entity.ExamQuChenRow;

import java.util.List;

public interface ExamQuChenColumnService extends SkyeyeBusinessService<ExamQuChenColumn> {

    void saveList(List<ExamQuChenColumn> column, List<ExamQuChenRow> row, String quId, String userId);
}
