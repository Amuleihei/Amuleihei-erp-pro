package com.skyeye.exam.examQuestionLogic.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.exam.examQuestionLogic.dao.ExamQuestionLogicDao;
import com.skyeye.exam.examQuestionLogic.entity.ExamQuestionLogic;
import com.skyeye.exam.examQuestionLogic.service.ExamQuestionLogicService;
import org.springframework.stereotype.Service;

@Service
@SkyeyeService(name = "题目逻辑设置管理", groupName = "题目逻辑设置管理")
public class ExamQuestionLogicServiceImpl extends SkyeyeBusinessServiceImpl<ExamQuestionLogicDao, ExamQuestionLogic> implements ExamQuestionLogicService  {
}
