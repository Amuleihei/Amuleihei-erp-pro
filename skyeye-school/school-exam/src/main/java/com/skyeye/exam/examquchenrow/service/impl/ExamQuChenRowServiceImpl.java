package com.skyeye.exam.examquchenrow.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.exam.examquchenrow.dao.ExamQuChenRowDao;
import com.skyeye.exam.examquchenrow.entity.ExamQuChenRow;
import com.skyeye.exam.examquchenrow.service.ExamQuChenRowService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@SkyeyeService(name = "矩陈题-行选项管理", groupName = "矩陈题-行选项管理")
public class ExamQuChenRowServiceImpl extends SkyeyeBusinessServiceImpl<ExamQuChenRowDao, ExamQuChenRow> implements ExamQuChenRowService {

    @Override
    public void saveRowEntity(List<ExamQuChenRow> quRow, String userId) {
        createEntity(quRow, userId);
    }

    @Override
    public void updateRowEntity(List<ExamQuChenRow> editquRow, String userId) {
        updateEntity(editquRow, userId);
    }
}
