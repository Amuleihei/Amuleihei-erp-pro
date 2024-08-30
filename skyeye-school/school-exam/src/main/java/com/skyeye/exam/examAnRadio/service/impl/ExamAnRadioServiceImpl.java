package com.skyeye.exam.examAnRadio.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exam.examAnRadio.dao.ExamAnRadioDao;
import com.skyeye.exam.examAnRadio.entity.ExamAnRadio;
import com.skyeye.exam.examAnRadio.service.ExamAnRadioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@SkyeyeService(name = "单选题保存表管理", groupName = "单选题保存表管理")
public class ExamAnRadioServiceImpl extends SkyeyeBusinessServiceImpl<ExamAnRadioDao, ExamAnRadio> implements ExamAnRadioService {

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
