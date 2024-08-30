package com.skyeye.exam.examQuChenoption.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuChenoption.dao.ExamQuChenOptionDao;
import com.skyeye.exam.examQuChenoption.entity.ExamQuChenOption;
import com.skyeye.exam.examQuChenoption.service.ExamQuChenOptionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@SkyeyeService(name = "矩陈题-题选项管理", groupName = "矩陈题-题选项管理")
public class ExamQuChenOptionServiceImpl extends SkyeyeBusinessServiceImpl<ExamQuChenOptionDao, ExamQuChenOption> implements ExamQuChenOptionService {

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        return beans;
    }

    @Override
    public void queryExamQuChenOptionListById(InputObject inputObject, OutputObject outputObject) {
       String examQuChenOptionId = inputObject.getParams().get("id").toString();
       QueryWrapper<ExamQuChenOption> queryWrapper = new QueryWrapper<>();
       queryWrapper.eq(CommonConstants.ID,examQuChenOptionId);
       List<ExamQuChenOption> examQuChenOptionList = list(queryWrapper);
       outputObject.setBean(examQuChenOptionList);
       outputObject.settotal(examQuChenOptionList.size());
    }
}
