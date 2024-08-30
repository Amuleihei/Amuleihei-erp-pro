package com.skyeye.eve.myquestionbank.dao;

import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.eve.myquestionbank.entity.MatrixMultipleChoice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @ClassName: MatrixMultipleChoiceDao
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2023/9/5 15:17
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface MatrixMultipleChoiceDao extends SkyeyeBaseMapper<MatrixMultipleChoice> {


    



    int deleteOldBindingByQuId(@Param("questionId") String questionId);

    int insertNewBinding(List<Map<String, Object>> items);


    


    int deleteQuestionColumnOptionMationList(@Param("idsList") List<String> idList);

    int deleteQuestionRowOptionMationList(@Param("idsList") List<String> idList);

    int addQuestionColumnMationList(List<Map<String, Object>> quColumn);

    int editQuestionColumnMationList(List<Map<String, Object>> editquColumn);

    int addQuestionRowMationList(List<Map<String, Object>> quRow);

    int editQuestionRowMationList(List<Map<String, Object>> editquRow);


    List<Map<String, Object>> queryQuestionKnowledgeByQuestionId(@Param("questionId") String questionId);

    void addQuestionMation(Map<String, Object> question);

    void editQuestionMationById(Map<String, Object> question);

    Map<String, Object> queryQuestionMationById(String quId);

    List<Map<String, Object>> queryQuestionChenRowListByQuestionId(Map<String, Object> question);

    List<Map<String, Object>> queryQuestionChenColumnListByQuestionId(Map<String, Object> question);

    List<Map<String, Object>> queryQuestionChenOptionListByQuestionId(Map<String, Object> question);
}
