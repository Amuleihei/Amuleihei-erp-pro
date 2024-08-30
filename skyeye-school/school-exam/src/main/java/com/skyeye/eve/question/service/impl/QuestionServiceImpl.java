/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.question.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.question.dao.QuestionDao;
import com.skyeye.eve.question.entity.Question;
import com.skyeye.eve.question.service.QuestionService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: QuestionServiceImpl
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2024/1/15 15:18
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "填空题", groupName = "题库管理")
public class QuestionServiceImpl extends SkyeyeBusinessServiceImpl<QuestionDao, Question> implements QuestionService {

    @Override
    public String saveQuestion(Question question, String id, String userId) {
        if (ToolUtil.isBlank(id)) {
            question.setQuTag(1);
            question.setVisibility(1);
            return createEntity(question, userId);
        } else {
            question.setId(id);
            return updateEntity(question, userId);
        }
    }
}
