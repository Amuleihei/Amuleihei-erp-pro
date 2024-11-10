package com.skyeye.exam.examQuestion.service.impl;

import cn.hutool.core.util.StrUtil;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.exam.examQuestion.dao.ExamQuestionDao;
import com.skyeye.exam.examQuestion.entity.ExamQuestion;
import com.skyeye.exam.examQuestion.service.ExamQuestionService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ExamQuestionServiceImpl
 * @Description: 问题表管理服务层
 * @author: skyeye云系列--lqy
 * @date: 2024/7/19 11:01
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "问题表管理", groupName = "问题表管理")
public class ExamQuestionServiceImpl extends SkyeyeBusinessServiceImpl<ExamQuestionDao, ExamQuestion> implements ExamQuestionService {

    @Override
    public void createPrepose(ExamQuestion entity) {
        String quId = "";
        quId = ToolUtil.getSurFaceId();
        entity.setId(quId);
        entity.setQuTag(1);
        entity.setVisibility(1);
        Integer fileType = entity.getFileType() != null ? entity.getFileType() : 0;
        entity.setFileType(fileType);
        Integer whetherUpload = entity.getWhetherUpload() != null ? entity.getWhetherUpload() : 2;
        entity.setWhetherUpload(whetherUpload);
        entity.setCreateTime(DateUtil.getTimeAndToString());
    }

    @Override
    public String saveQuestion(ExamQuestion question, String id, String userId) {
        if (StrUtil.isBlank(id)) {
            createEntity(question, userId);
        } else {
            question.getId();
            updateEntity(question, userId);
        }
        return question.getId();
    }
}



