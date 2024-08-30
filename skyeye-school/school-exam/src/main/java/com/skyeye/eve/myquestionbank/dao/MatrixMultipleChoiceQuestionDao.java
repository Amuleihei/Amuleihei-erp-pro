package com.skyeye.eve.myquestionbank.dao;

import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.eve.myquestionbank.entity.MatrixMultipleChoice;
import com.skyeye.eve.myquestionbank.entity.MatrixMultipleChoiceQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MatrixMultipleChoiceQuestionDao extends SkyeyeBaseMapper<MatrixMultipleChoiceQuestion> {

    int addQuestionMation(Map<String, Object> question);

    int editQuestionMationById(Map<String, Object> question);

    int deleteOldBindingByQuId(@Param("questionId") String questionId);

    int insertNewBinding(List<Map<String, Object>> items);

    Map<String, Object> queryQuestionMationById(@Param("questionId") String questionId);

    List<Map<String, Object>> queryQuestionKnowledgeByQuestionId(@Param("questionId") String questionId);

    int deleteQuestionColumnOptionMationList(@Param("idsList") List<String> idList);

    int deleteQuestionRowOptionMationList(@Param("idsList") List<String> idList);

   

    List<Map<String, Object>> queryQuestionChenRowListByQuestionId(Map<String, Object> question);

    List<Map<String, Object>> queryQuestionChenColumnListByQuestionId(Map<String, Object> question);

    List<Map<String, Object>> queryQuestionChenOptionListByQuestionId(Map<String, Object> question);


    void addQuestionColumnMationList(List<Map<String, Object>> quColumn);

    void editQuestionColumnMationList(List<Map<String, Object>> editquColumn);

    void addQuestionRowMationList(List<Map<String, Object>> quRow);

    void editQuestionRowMationList(List<Map<String, Object>> editquRow);
}
