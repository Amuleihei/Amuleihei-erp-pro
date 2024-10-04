package com.skyeye.exam.examQuestion.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuestion.dao.ExamQuestionDao;
import com.skyeye.exam.examQuestion.entity.ExamQuestion;
import com.skyeye.exam.examQuestion.service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@SkyeyeService(name = "问题表管理", groupName = "问题表管理")
public class ExamQuestionServiceImpl extends SkyeyeBusinessServiceImpl<ExamQuestionDao, ExamQuestion> implements ExamQuestionService {

    @Autowired
    private ExamQuestionService examQuestionService;

    @Override
    public void deleteQuestionById(InputObject inputObject, OutputObject outputObject) {
        Map<String,Object> map = inputObject.getParams();
        String questionId = map.get("id").toString();
        LambdaQueryWrapper<ExamQuestion> queryWrapper = new LambdaQueryWrapper<ExamQuestion>();
        queryWrapper.eq(ExamQuestion::getId,questionId);
        ExamQuestion examQuestion = examQuestionService.getOne(queryWrapper);
        // 设置问题isDelete = 0 表示已经删除
        examQuestion.setIsDelete(CommonNumConstants.NUM_ZERO);
        examQuestionService.updateById(examQuestion);
        refreshCache(questionId);
    }

    @Override
    public void queryQuestionsList(InputObject inputObject, OutputObject outputObject) {
        List<ExamQuestion> examQuestionList = queryAllData();
        List<ExamQuestion> bean = examQuestionList.stream().filter(isDelete -> isDelete.getIsDelete() == CommonNumConstants.NUM_ONE).collect(Collectors.toList());
        outputObject.setBeans(bean);
        outputObject.settotal(bean.size());
    }
}
