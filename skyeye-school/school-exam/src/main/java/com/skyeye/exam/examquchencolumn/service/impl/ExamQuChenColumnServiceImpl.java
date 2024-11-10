package com.skyeye.exam.examquchencolumn.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.exam.examquchencolumn.dao.ExamQuChenColumnDao;
import com.skyeye.exam.examquchencolumn.entity.ExamQuChenColumn;
import com.skyeye.exam.examquchencolumn.service.ExamQuChenColumnService;
import com.skyeye.exam.examquchenrow.entity.ExamQuChenRow;
import com.skyeye.exam.examquchenrow.service.ExamQuChenRowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@SkyeyeService(name = "矩陈题-列选项管理", groupName = "矩陈题-列选项管理")
public class ExamQuChenColumnServiceImpl extends SkyeyeBusinessServiceImpl<ExamQuChenColumnDao, ExamQuChenColumn> implements ExamQuChenColumnService {

    @Autowired
    private ExamQuChenRowService examQuChenRowService;

    @Override
    public void saveList(List<ExamQuChenColumn> column, List<ExamQuChenRow> row, String quId, String userId) {
        List<ExamQuChenColumn> quColumn = new ArrayList<>();
        List<ExamQuChenColumn> editquColumn = new ArrayList<>();
        for (int i = 0; i < column.size(); i++) {
            ExamQuChenColumn object = column.get(i);
            ExamQuChenColumn bean = new ExamQuChenColumn();
            bean.setOrderById(object.getOrderById());
            bean.setOptionName(object.getOptionName());
            if (ToolUtil.isBlank(object.getOptionId())) {
                bean.setQuId(object.getQuId());
                bean.setVisibility(1);
                bean.setId(ToolUtil.getSurFaceId());
                bean.setCreateId(userId);
                bean.setCreateTime(DateUtil.getTimeAndToString());
                quColumn.add(bean);
            } else {
                bean.setId(object.getOptionId());
                editquColumn.add(bean);
            }
        }
        if (!quColumn.isEmpty()) {
            createEntity(quColumn, userId);

        }
        if (!editquColumn.isEmpty()) {
            updateEntity(editquColumn, userId);
        }

        List<ExamQuChenRow> quRow = new ArrayList<>();
        List<ExamQuChenRow> editquRow = new ArrayList<>();
        for (int i = 0; i < row.size(); i++) {
            ExamQuChenRow object = row.get(i);
            ExamQuChenRow bean = new ExamQuChenRow();
            bean.setOrderById(object.getOrderById());
            bean.setOptionName(object.getOptionName());
            if (ToolUtil.isBlank(object.getOptionId())) {
                bean.setQuId(object.getQuId());
                bean.setVisibility(1);
                bean.setId(ToolUtil.getSurFaceId());
                bean.setCreateId(userId);
                bean.setCreateTime(DateUtil.getTimeAndToString());
                quRow.add(bean);
            } else {
                bean.setId(object.getOptionId());
                editquRow.add(bean);
            }
        }
        if (!quRow.isEmpty()) {
            examQuChenRowService.saveRowEntity(quRow, userId);
        }
        if (!editquRow.isEmpty()) {
            examQuChenRowService.updateRowEntity(editquRow, userId);
        }
    }
}
