package com.skyeye.exam.examanradio.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.question.QuType;
import com.skyeye.eve.question.service.QuestionService;
import com.skyeye.exam.examanradio.dao.ExamAnRadioDao;
import com.skyeye.exam.examanradio.entity.ExamAnRadio;
import com.skyeye.exam.examanradio.service.ExamAnRadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@SkyeyeService(name = "单选题保存表管理", groupName = "单选题保存表管理")
public class ExamAnRadioServiceImpl extends SkyeyeBusinessServiceImpl<ExamAnRadioDao, ExamAnRadio> implements ExamAnRadioService {

    @Autowired
    private QuestionService questionService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        return beans;
    }

    @Override
    public void queryExamAnRadioListById(InputObject inputObject, OutputObject outputObject) {
        String examAnRadioId = inputObject.getParams().get("id").toString();
        QueryWrapper<ExamAnRadio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CommonConstants.ID,examAnRadioId);
        List<ExamAnRadio> examAnRadioList = list(queryWrapper);
        outputObject.setBean(examAnRadioList);
        outputObject.settotal(examAnRadioList.size());
    }
}
