package com.skyeye.eve.myquestionbank.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.myquestionbank.entity.MatrixMultipleChoice;
import com.skyeye.eve.myquestionbank.entity.MatrixMultipleChoiceQuestion;

import java.util.List;
import java.util.Map;


/**
 * @ClassName: MatrixMultipleChoiceService
 * @Description:服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/9/5 15:17
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface MatrixMultipleChoiceService extends SkyeyeBusinessService<MatrixMultipleChoice> {

    void addMatrixMultipleChoiceMation(InputObject inputObject, OutputObject outputObject);

    void queryMatrixMultipleChoiceMationToEditById(InputObject inputObject, OutputObject outputObject);


}
