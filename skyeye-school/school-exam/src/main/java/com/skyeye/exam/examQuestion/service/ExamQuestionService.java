package com.skyeye.exam.examQuestion.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.question.entity.Question;
import com.skyeye.exam.examQuestion.entity.ExamQuestion;

/**
 * @ClassName: ExamQuestionService
 * @Description: 问题表管理服务接口层
 * @author: skyeye云系列--lqy
 * @date: 2024/7/19 11:01
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ExamQuestionService extends SkyeyeBusinessService<ExamQuestion> {

    String saveQuestion(ExamQuestion question, String id, String userId);
}
