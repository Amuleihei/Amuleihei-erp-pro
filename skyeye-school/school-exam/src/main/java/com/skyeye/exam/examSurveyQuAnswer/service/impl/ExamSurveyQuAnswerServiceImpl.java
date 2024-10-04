package com.skyeye.exam.examSurveyQuAnswer.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.exam.examSurveyQuAnswer.dao.ExamSurveyQuAnswerDao;
import com.skyeye.exam.examSurveyQuAnswer.entity.ExamSurveyQuAnswer;
import com.skyeye.exam.examSurveyQuAnswer.service.ExamSurveyQuAnswerService;
import org.springframework.stereotype.Service;

@Service
@SkyeyeService(name = "答卷 题目和所得分数的关联表管理", groupName = "答卷 题目和所得分数的关联表管理")
public class ExamSurveyQuAnswerServiceImpl extends SkyeyeBusinessServiceImpl<ExamSurveyQuAnswerDao, ExamSurveyQuAnswer> implements ExamSurveyQuAnswerService {
}
