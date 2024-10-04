package com.skyeye.exam.examSurveyClass.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examSurveyAnswer.dao.ExamSurveyAnswerDao;
import com.skyeye.exam.examSurveyAnswer.entity.ExamSurveyAnswer;
import com.skyeye.exam.examSurveyAnswer.service.ExamSurveyAnswerService;
import com.skyeye.exam.examSurveyClass.dao.ExamSurveyClassDao;
import com.skyeye.exam.examSurveyClass.entity.ExamSurveyClass;
import com.skyeye.exam.examSurveyClass.service.ExamSurveyClassService;
import org.springframework.stereotype.Service;

@Service
@SkyeyeService(name = "试卷与班级关系表管理", groupName = "试卷与班级关系表管理")
public class ExamSurveyClassServiceImpl extends SkyeyeBusinessServiceImpl<ExamSurveyClassDao, ExamSurveyClass> implements ExamSurveyClassService {


}
