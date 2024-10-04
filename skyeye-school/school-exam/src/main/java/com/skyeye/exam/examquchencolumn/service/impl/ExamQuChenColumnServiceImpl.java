package com.skyeye.exam.examquchencolumn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examquchencolumn.dao.ExamQuChenColumnDao;
import com.skyeye.exam.examquchencolumn.entity.ExamQuChenColumn;
import com.skyeye.exam.examquchencolumn.service.ExamQuChenColumnService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@SkyeyeService(name = "矩陈题-列选项管理", groupName = "矩陈题-列选项管理")
public class ExamQuChenColumnServiceImpl extends SkyeyeBusinessServiceImpl<ExamQuChenColumnDao, ExamQuChenColumn> implements ExamQuChenColumnService {

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        return beans;
    }

    @Override
    public void queryExamQuChenColumnListById(InputObject inputObject, OutputObject outputObject) {
        String examQuChenColumnId = inputObject.getParams().get("id").toString();
        QueryWrapper<ExamQuChenColumn> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CommonConstants.ID,examQuChenColumnId);
        List<ExamQuChenColumn> examQuChenColumnList = list(queryWrapper);
        outputObject.setBean(examQuChenColumnList);
        outputObject.settotal(examQuChenColumnList.size());
    }
}
