package com.skyeye.eve.myquestionbank.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.myquestionbank.entity.MatrixMultipleChoice;
import com.skyeye.eve.myquestionbank.entity.MatrixMultipleChoiceQuestion;

public interface MatrixMultipleChoiceQuestionService extends SkyeyeBusinessService<MatrixMultipleChoiceQuestion> {


    void addMatrixMultiplechoiceQuestionMation(InputObject inputObject, OutputObject outputObject);


    void queryMatrixMultiplechoiceQuestionMationToEditById(InputObject inputObject, OutputObject outputObject);

}





