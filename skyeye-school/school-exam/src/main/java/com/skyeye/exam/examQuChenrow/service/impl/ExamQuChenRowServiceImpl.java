package com.skyeye.exam.examQuChenrow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuChenrow.dao.ExamQuChenRowDao;
import com.skyeye.exam.examQuChenrow.entity.ExamQuChenRow;
import com.skyeye.exam.examQuChenrow.service.ExamQuChenRowService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@SkyeyeService(name = "矩陈题-行选项管理", groupName = "矩陈题-行选项管理")
public class ExamQuChenRowServiceImpl extends SkyeyeBusinessServiceImpl<ExamQuChenRowDao, ExamQuChenRow> implements ExamQuChenRowService {

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        return beans;
    }

    @Override
    public void queryExamQuChenRowListById(InputObject inputObject, OutputObject outputObject) {
        String examQuChenRowId = inputObject.getParams().get("id").toString();
        QueryWrapper<ExamQuChenRow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CommonConstants.ID,examQuChenRowId);
        List<ExamQuChenRow> examQuChenRowList = list(queryWrapper);
        outputObject.setBean(examQuChenRowList);
        outputObject.settotal(examQuChenRowList.size());
    }
}
