/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SchoolQuestionBankService {

    void querySchoolQuestionBankMationList(InputObject inputObject, OutputObject outputObject);

    void addQuRadioMation(InputObject inputObject, OutputObject outputObject);

    void deleteSchoolQuestionBankMationById(InputObject inputObject, OutputObject outputObject);

    void queryQuRadioMationToEditById(InputObject inputObject, OutputObject outputObject);

    void addQuCheckBoxMation(InputObject inputObject, OutputObject outputObject);

    void queryQuCheckBoxMationToEditById(InputObject inputObject, OutputObject outputObject);

    void addQuFillblankMation(InputObject inputObject, OutputObject outputObject);

    void queryQuFillblankMationToEditById(InputObject inputObject, OutputObject outputObject);

    void addQuScoreMation(InputObject inputObject, OutputObject outputObject);

    void queryQuScoreMationToEditById(InputObject inputObject, OutputObject outputObject);

    void addQuOrderbyMation(InputObject inputObject, OutputObject outputObject);

    void queryQuOrderbyMationToEditById(InputObject inputObject, OutputObject outputObject);

    void addQuMultiFillblankAddMation(InputObject inputObject, OutputObject outputObject);

    void queryQuMultiFillblankMationToEditById(InputObject inputObject, OutputObject outputObject);

    void addQuChenMation(InputObject inputObject, OutputObject outputObject);

    void queryQuChenMationToEditById(InputObject inputObject, OutputObject outputObject);

    void querySchoolQuestionBankMationListToChoose(InputObject inputObject, OutputObject outputObject);

    void querySchoolQuestionBankMationListByIds(InputObject inputObject, OutputObject outputObject);

    void querySchoolQuestionBankMationAllList(InputObject inputObject, OutputObject outputObject);

}
