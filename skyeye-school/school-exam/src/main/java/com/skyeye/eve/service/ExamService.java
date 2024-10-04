/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface ExamService {

    void queryExamList(InputObject inputObject, OutputObject outputObject);

//    void insertExamMation(InputObject inputObject, OutputObject outputObject);

    void queryExamMationById(InputObject inputObject, OutputObject outputObject);

    void addQuFillblankMation(InputObject inputObject, OutputObject outputObject);

    void addQuScoreMation(InputObject inputObject, OutputObject outputObject);

    void addQuOrderquMation(InputObject inputObject, OutputObject outputObject);

    void addQuPagetagMation(InputObject inputObject, OutputObject outputObject);

    void addQuRadioMation(InputObject inputObject, OutputObject outputObject);

    void addQuCheckBoxMation(InputObject inputObject, OutputObject outputObject);

    void addQuMultiFillblankMation(InputObject inputObject, OutputObject outputObject);

    void addQuParagraphMation(InputObject inputObject, OutputObject outputObject);

    void addQuChenMation(InputObject inputObject, OutputObject outputObject);

    void deleteQuestionMationById(InputObject inputObject, OutputObject outputObject);

    void deleteQuestionChenColumnMationById(InputObject inputObject, OutputObject outputObject);

    void deleteQuestionChenRowMationById(InputObject inputObject, OutputObject outputObject);

    void deleteQuestionRadioOptionMationById(InputObject inputObject, OutputObject outputObject);

    void deleteQuestionChedkBoxOptionMationById(InputObject inputObject, OutputObject outputObject);

    void deleteQuestionScoreOptionMationById(InputObject inputObject, OutputObject outputObject);

    void deleteQuestionOrderOptionMationById(InputObject inputObject, OutputObject outputObject);

    void deleteQuestionMultiFillblankOptionMationById(InputObject inputObject, OutputObject outputObject);

    void editExamStateToReleaseById(InputObject inputObject, OutputObject outputObject);

    void queryExamMationByIdToHTML(InputObject inputObject, OutputObject outputObject);

    void deleteExamMationById(InputObject inputObject, OutputObject outputObject);

    void queryExamFxMationById(InputObject inputObject, OutputObject outputObject);

    void insertExamMationCopyById(InputObject inputObject, OutputObject outputObject);

    void queryAnswerExamMationByIp(InputObject inputObject, OutputObject outputObject);

    void insertAnswerExamMationByIp(InputObject inputObject, OutputObject outputObject);

    void updateExamMationEndById(InputObject inputObject, OutputObject outputObject);

    void queryMyExamList(InputObject inputObject, OutputObject outputObject);

    void queryExamAnswerMationByAnswerId(InputObject inputObject, OutputObject outputObject);

    void queryExamAnswerMationToMarkingByAnswerId(InputObject inputObject, OutputObject outputObject);

    void insertExamAnswerResultMation(InputObject inputObject, OutputObject outputObject);

    void queryExamMationDetailById(InputObject inputObject, OutputObject outputObject);

    void queryExamAnswerMationDetailById(InputObject inputObject, OutputObject outputObject);

    void queryExamAndMarkPeopleMationDetailById(InputObject inputObject, OutputObject outputObject);

    void editMarkPeopleMationDetailById(InputObject inputObject, OutputObject outputObject);

}
