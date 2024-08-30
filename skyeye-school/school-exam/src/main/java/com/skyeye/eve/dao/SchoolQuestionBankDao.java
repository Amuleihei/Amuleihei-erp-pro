/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SchoolQuestionBankDao {

    List<Map<String, Object>> querySchoolQuestionBankMationList(Map<String, Object> map);

    int addQuestionMation(Map<String, Object> question);

    int editQuestionMationById(Map<String, Object> question);

    int addQuestionRadioMationList(List<Map<String, Object>> quRadio);

    int editQuestionRadioMationList(List<Map<String, Object>> editquRadio);

    int deleteOldBindingByQuId(@Param("questionId") String questionId);

    int insertNewBinding(List<Map<String, Object>> items);

    int deleteSchoolQuestionBankMationById(Map<String, Object> map);

    Map<String, Object> queryQuestionMationById(@Param("questionId") String questionId);

    List<Map<String, Object>> queryQuestionRadioListByQuestionId(Map<String, Object> question);

    List<Map<String, Object>> queryQuestionKnowledgeByQuestionId(@Param("questionId") String questionId);

    int deleteQuestionRadioOptionMationList(@Param("idsList") List<String> idList);

    int addQuestionCheckBoxMationList(List<Map<String, Object>> quCheckbox);

    int editQuestionCheckBoxMationList(List<Map<String, Object>> editquCheckbox);

    int deleteQuestionCheckBoxOptionMationList(@Param("idsList") List<String> idList);

    List<Map<String, Object>> queryQuestionCheckBoxListByQuestionId(Map<String, Object> question);

    int addQuestionScoreMationList(List<Map<String, Object>> quScore);

    int editQuestionScoreMationList(List<Map<String, Object>> editquScore);

    int deleteQuestionScoreOptionMationList(@Param("idsList") List<String> idList);

    List<Map<String, Object>> queryQuestionScoreListByQuestionId(Map<String, Object> question);

    int deleteQuestionOrderOptionMationList(@Param("idsList") List<String> idList);

    int addQuestionOrderquMationList(List<Map<String, Object>> quOrderqu);

    int editQuestionOrderquMationList(List<Map<String, Object>> editquOrderqu);

    List<Map<String, Object>> queryQuestionOrderByListByQuestionId(Map<String, Object> question);

    int deleteQuestionMultiFillblankOptionMationList(@Param("idsList") List<String> idList);

    int addQuestionMultiFillblankMationList(List<Map<String, Object>> quMultiFillblank);

    int editQuestionMultiFillblankMationList(List<Map<String, Object>> editquMultiFillblank);

    List<Map<String, Object>> queryQuestionMultiFillBlankListByQuestionId(Map<String, Object> question);

    int deleteQuestionColumnOptionMationList(@Param("idsList") List<String> idList);

    int deleteQuestionRowOptionMationList(@Param("idsList") List<String> idList);

    int addQuestionColumnMationList(List<Map<String, Object>> quColumn);

    int editQuestionColumnMationList(List<Map<String, Object>> editquColumn);

    int addQuestionRowMationList(List<Map<String, Object>> quRow);

    int editQuestionRowMationList(List<Map<String, Object>> editquRow);

    List<Map<String, Object>> queryQuestionChenRowListByQuestionId(Map<String, Object> question);

    List<Map<String, Object>> queryQuestionChenColumnListByQuestionId(Map<String, Object> question);

    List<Map<String, Object>> queryQuestionChenOptionListByQuestionId(Map<String, Object> question);

    List<Map<String, Object>> querySchoolQuestionBankMationListToChoose(Map<String, Object> map);

    List<Map<String, Object>> querySchoolQuestionBankMationListByIds(@Param("idsList") List<String> idsList);

    List<Map<String, Object>> querySchoolQuestionBankMationAllList(Map<String, Object> map);

}
