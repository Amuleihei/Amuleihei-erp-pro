/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.question.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.common.util.question.QuType;
import com.skyeye.eve.question.dao.QuestionDao;
import com.skyeye.eve.question.entity.Question;
import com.skyeye.eve.question.service.QuestionService;
import com.skyeye.exam.examQuRadio.entity.ExamQuRadio;
import com.skyeye.exam.examQuRadio.service.ExamQuRadioService;
import com.skyeye.exam.examQuScore.entity.ExamQuScore;
import com.skyeye.exam.examQuScore.service.ExamQuScoreService;
import com.skyeye.exam.examquchckbox.entity.ExamQuCheckbox;
import com.skyeye.exam.examquchckbox.service.ExamQuCheckboxService;
import com.skyeye.exam.examquchencolumn.entity.ExamQuChenColumn;
import com.skyeye.exam.examquchencolumn.service.ExamQuChenColumnService;
import com.skyeye.exam.examquchenrow.entity.ExamQuChenRow;
import com.skyeye.exam.examquchenrow.service.ExamQuChenRowService;
import com.skyeye.exam.examqumultfillblank.entity.ExamQuMultiFillblank;
import com.skyeye.exam.examqumultfillblank.service.ExamQuMultiFillblankService;
import com.skyeye.exam.examquorderby.entity.ExamQuOrderby;
import com.skyeye.exam.examquorderby.service.ExamQuOrderbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: QuestionServiceImpl
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2024/1/15 15:18
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "题目", groupName = "题库管理")
public class QuestionServiceImpl extends SkyeyeBusinessServiceImpl<QuestionDao, Question> implements QuestionService {

    @Autowired
    private ExamQuRadioService examQuRadioService;

    @Autowired
    private ExamQuScoreService quScoreService;

    @Autowired
    private ExamQuCheckboxService examQuCheckboxService;

    @Autowired
    private ExamQuMultiFillblankService examQuMultiFillblankService;

    @Autowired
    private ExamQuOrderbyService examQuOrderbyService;

    @Autowired
    private ExamQuChenColumnService examQuChenColumnService;

    @Autowired
    private ExamQuChenRowService examQuChenRowService;

    @Override
    public void createPrepose(Question entity) {
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        String quId = ToolUtil.getSurFaceId();
        entity.setId(quId);
        entity.setQuTag(1);
        entity.setVisibility(1);
        Integer fileType = entity.getFileType() != null ? entity.getFileType() : 0;
        entity.setFileType(fileType);
        Integer whetherUpload = entity.getWhetherUpload() != null ? entity.getWhetherUpload() : 2;
        entity.setWhetherUpload(whetherUpload);
        entity.setCreateTime(DateUtil.getTimeAndToString());
        List<ExamQuRadio> radioTd = entity.getRadioTd();
        if(!radioTd.isEmpty()){
            entity.setQuType(QuType.RADIO.getIndex());
            examQuRadioService.saveList(radioTd, quId, userId);
        }

        List<ExamQuScore> ScoreTd = entity.getScoreTd();
        if(!ScoreTd.isEmpty()){
            entity.setQuType(QuType.SCORE.getIndex());
            quScoreService.saveList(ScoreTd, quId, userId);
        }

        List<ExamQuCheckbox> checkboxTd = entity.getCheckboxTd();
        if(!checkboxTd.isEmpty()){
            entity.setQuType(QuType.CHECKBOX.getIndex());
            examQuCheckboxService.saveList(checkboxTd, quId, userId);
        }

        List<ExamQuMultiFillblank> multiFillblankTd = entity.getMultifillblankTd();
        if(!multiFillblankTd.isEmpty()){
            entity.setQuType(QuType.MULTIFILLBLANK.getIndex());
            examQuMultiFillblankService.saveList(multiFillblankTd, quId, userId);
        }

        List<ExamQuOrderby> orderbyTd = entity.getOrderbyTd();
        if(!orderbyTd.isEmpty()){
            entity.setQuType(QuType.ORDERQU.getIndex());
            examQuOrderbyService.saveList(orderbyTd, quId, userId);
        }

        List<ExamQuChenColumn> columnTd = entity.getColumnTd();
        List<ExamQuChenRow> rowTd = entity.getRowTd();
        if (!columnTd.isEmpty() && !rowTd.isEmpty()){
            entity.setQuType(QuType.CHENFBK.getIndex());
            examQuChenColumnService.saveList(columnTd, rowTd, quId, userId);
        }
    }

    @Override
    public void createPostpose(Question entity, String userId) {
        String id = entity.getId();
        List<ExamQuChenColumn> columnTd = entity.getColumnTd();
        for (ExamQuChenColumn examQuChenColumn : columnTd) {
            examQuChenColumn.setQuId(id);
        }
        examQuChenColumnService.createEntity(columnTd, userId);
        List<ExamQuChenRow> rowTd = entity.getRowTd();
        for (ExamQuChenRow examQuChenRow : rowTd) {
            examQuChenRow.setQuId(id);
        }
        examQuChenRowService.createEntity(rowTd, userId);
//        List<ExamQuRadio> radioTd = entity.getRadioTd();
//        for (ExamQuRadio examQuRadio : radioTd) {
//            examQuRadio.setQuId(id);
//        }
//        examQuRadioService.createEntity(radioTd, userId);
    }

    @Override
    public void updatePrepose(Question entity) {
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        List<ExamQuRadio> radioTd = entity.getRadioTd();
        if(!radioTd.isEmpty()){
            entity.setQuType(entity.getQuType());
            examQuRadioService.saveList(radioTd, entity.getId(), userId);
        }
        List<ExamQuScore> ScoreTd = entity.getScoreTd();
        if(!ScoreTd.isEmpty()){
            entity.setQuType(entity.getQuType());
            quScoreService.saveList(ScoreTd, entity.getId(), userId);
        }
        List<ExamQuCheckbox> checkboxTd = entity.getCheckboxTd();
        if(!checkboxTd.isEmpty()){
            entity.setQuType(entity.getQuType());
            examQuCheckboxService.saveList(checkboxTd, entity.getId(), userId);
        }
    }


//    @Override
//    public String saveQuestion(Question question, String id, String userId) {
//        if (StrUtil.isBlank(id)) {
//            createEntity(question, userId);
//        } else {
//            question.getId();
//            updateEntity(question, userId);
//        }
//        return question.getId();
//    }

}
