package com.skyeye.school.interaction.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.rest.wall.user.service.IUserService;
import com.skyeye.school.interaction.dao.QuestionsDao;
import com.skyeye.school.interaction.entity.QuestionAnswer;
import com.skyeye.school.interaction.entity.QuestionCategories;
import com.skyeye.school.interaction.entity.QuestionStuRecord;
import com.skyeye.school.interaction.entity.Questions;
import com.skyeye.school.interaction.service.QuestionAnswerService;
import com.skyeye.school.interaction.service.QuestionCategoriesService;
import com.skyeye.school.interaction.service.QuestionStuRecordService;
import com.skyeye.school.interaction.service.QuestionsService;
import com.skyeye.school.subject.entity.SubjectClasses;
import com.skyeye.school.subject.service.SubjectClassesService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: questionsServiceImpl
 * @Description: 互动答题题目管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/17 10:46
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "互动答题题目管理", groupName = "互动答题题目管理")
public class QuestionsServiceImpl extends SkyeyeBusinessServiceImpl<QuestionsDao, Questions> implements QuestionsService {

    @Autowired
    private SubjectClassesService subjectClassesService;

    @Autowired
    private QuestionCategoriesService questionCategoriesService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private QuestionStuRecordService questionStuRecordService;

    @Autowired
    private QuestionAnswerService questionAnswerService;

    @Override
    public void validatorEntity(Questions questions){
        String id = questions.getId();
        String questionText = questions.getQuestionText();
        String categoriesId = questions.getCategoriesId();
        String subjectClassesId = questions.getSubjectClassesId();
        QueryWrapper<Questions> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(Questions::getQuestionText), questionText);
        List<Questions> questionsList = list(queryWrapper);
        QuestionCategories questionCategories = questionCategoriesService.selectById(categoriesId);
        SubjectClasses subjectClasses = subjectClassesService.selectById(subjectClassesId);
        if(StrUtil.isEmpty(id)){//新增
            if(CollectionUtil.isNotEmpty(questionsList)){
                throw new CustomException("题目已存在");
            }
            if(ObjectUtil.isNull(questionCategories.getId())){
                throw new CustomException("CategoriesId不存在");
            }
            if(ObjectUtil.isNull(subjectClasses.getId())){
                throw new CustomException("subjectClassesId不存在");
            }
        }else {//编辑
            Questions questions1 = questionsService.selectById(id);
            if(ObjectUtil.isNull(questions1.getId())){
                throw new CustomException("该题目id不存在");
            }
            if(CollectionUtil.isNotEmpty(questionsList)&&!questionsList.get(0).getId().equals(id)){
                throw new CustomException("题目已存在");
            }
            if(ObjectUtil.isNull(questionCategories.getId())){
                throw new CustomException("CategoriesId不存在");
            }
            if(ObjectUtil.isNull(subjectClasses.getId())){
                throw new CustomException("subjectClassesId不存在");
            }
            questionsService.refreshCache(id);
        }
    }

    @Override
    public void createPrepose(Questions questions){
        String subjectClassId = questions.getSubjectClassesId();
        String categoriesId = questions.getCategoriesId();
        QuestionCategories questionCategories = questionCategoriesService.selectById(categoriesId);
        SubjectClasses subjectClasses = subjectClassesService.selectById(subjectClassId);
        questions.setObjectId(subjectClasses.getObjectId());
        questions.setObjectKey(subjectClasses.getObjectKey());
        questions.setCategoriesName(questionCategories.getCategoriesName());
    }

    @Override
    public void updatePrepose(Questions questions){
        String id = questions.getId();
        String subjectClassId = questions.getSubjectClassesId();
        String categoriesId = questions.getCategoriesId();
        QuestionCategories questionCategories = questionCategoriesService.selectById(categoriesId);
        SubjectClasses subjectClasses = subjectClassesService.selectById(subjectClassId);
        questions.setCategoriesName(questionCategories.getCategoriesName());
        UpdateWrapper<Questions> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        updateWrapper.set(MybatisPlusUtil.toColumns(Questions::getSubjectClassesId),subjectClassId);
        updateWrapper.set(MybatisPlusUtil.toColumns(Questions::getObjectId),subjectClasses.getObjectId());
        updateWrapper.set(MybatisPlusUtil.toColumns(Questions::getObjectKey),subjectClasses.getObjectKey());
        questionsService.update(updateWrapper);
    }

    @Override
    public void queryQuestionListBySubjectClassesId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String subjectClassId = map.get("subjectClassesId").toString();
        QueryWrapper<Questions> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(Questions::getSubjectClassesId), subjectClassId);
        List<Questions> questionsList = list(queryWrapper);
        if(CollectionUtil.isEmpty(questionsList)){
            iUserService.setDataMation(questionsList,Questions::getCreateId);
        }else {
            iAuthUserService.setDataMation(questionsList,Questions::getCreateId);
        }
        outputObject.setBeans(questionsList);
        outputObject.settotal(questionsList.size());
    }

    @Override
    public Questions selectById(String id){
        Questions questions = super.selectById(id);
        iAuthUserService.setDataMation(questions,Questions::getCreateId);
        return questions;
    }

    @Override
    public void deleteById(InputObject inputObject, OutputObject outputObject){
        String questionId = inputObject.getParams().get("id").toString();
        Questions questions = questionsService.selectById(questionId);
        if(ObjectUtil.isNull(questions.getId())){
            throw new CustomException("改题目id不存在");
        }
        List<QuestionAnswer> questionAnswerList = questionAnswerService.queryAllData();
        QueryWrapper<QuestionStuRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(QuestionStuRecord::getQuestionId), questionId);
        List<QuestionStuRecord> questionStuRecordList = questionStuRecordService.list(queryWrapper);
        if(CollectionUtil.isNotEmpty(questionStuRecordList)){
            questionStuRecordService.deleteById(inputObject, outputObject);
            deleteQuestionAndAnswer(inputObject, outputObject, questionId, questionAnswerList);
        }else {
            deleteQuestionAndAnswer(inputObject, outputObject, questionId, questionAnswerList);
        }

    }

    private void deleteQuestionAndAnswer(InputObject inputObject, OutputObject outputObject, String questionId, List<QuestionAnswer> questionAnswerList) {
        if(CollectionUtil.isNotEmpty(questionAnswerList)){
            int flag  = 0;
            for(QuestionAnswer questionAnswer : questionAnswerList){
                if(questionAnswer.getQuestionId().equals(questionId)){
                    questionAnswerService.deleteById(questionAnswer.getId());
                    super.deleteById(inputObject, outputObject);
                    break;
                }
                flag++;
            }
            if(flag==questionAnswerList.size()){
                super.deleteById(inputObject, outputObject);
            }
        }else {
            super.deleteById(inputObject, outputObject);
        }
    }
}
