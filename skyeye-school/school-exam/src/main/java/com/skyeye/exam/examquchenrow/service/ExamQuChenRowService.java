package com.skyeye.exam.examquchenrow.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.exam.examquchenrow.entity.ExamQuChenRow;

import java.util.List;

public interface ExamQuChenRowService extends SkyeyeBusinessService<ExamQuChenRow> {

    void saveRowEntity(List<ExamQuChenRow> quRow, String userId);

    void updateRowEntity(List<ExamQuChenRow> editquRow, String userId);
}
