package com.skyeye.school.interaction.service;


import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.interaction.entity.QuestionAnswer;

/**
 * @ClassName: questionAnswerService
 * @Description: 互动答题题目答案服务接口层
 * @author: skyeye云系列--lqy
 * @date: 2024/7/17 14:45
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface QuestionAnswerService extends SkyeyeBusinessService<QuestionAnswer> {
    void queryAnswerByQuestionId(InputObject inputObject, OutputObject outputObject);
}
