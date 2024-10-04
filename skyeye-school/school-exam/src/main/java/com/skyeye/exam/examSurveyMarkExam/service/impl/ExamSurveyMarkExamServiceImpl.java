package com.skyeye.exam.examSurveyMarkExam.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.exam.examSurveyMarkExam.dao.ExamSurveyMarkExamDao;
import com.skyeye.exam.examSurveyMarkExam.entity.ExamSurveyMarkExam;
import com.skyeye.exam.examSurveyMarkExam.service.ExamSurveyMarkExamService;
import org.springframework.stereotype.Service;

@Service
@SkyeyeService(name = "试卷与阅卷人关系表管理", groupName = "试卷与阅卷人关系表管理")
public class ExamSurveyMarkExamServiceImpl extends SkyeyeBusinessServiceImpl<ExamSurveyMarkExamDao, ExamSurveyMarkExam> implements ExamSurveyMarkExamService {
}
