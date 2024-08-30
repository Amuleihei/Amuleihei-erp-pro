package com.skyeye.school.interaction.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Joiner;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.client.ExecuteFeignClient;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.rest.wall.certification.rest.ICertificationRest;
import com.skyeye.school.interaction.dao.QuestionStuRecordDao;
import com.skyeye.school.interaction.entity.QuestionStuRecord;
import com.skyeye.school.interaction.entity.Questions;
import com.skyeye.school.interaction.service.QuestionStuRecordService;
import com.skyeye.school.interaction.service.QuestionsService;
import com.skyeye.school.subject.entity.SubjectClassesStu;
import com.skyeye.school.subject.service.SubjectClassesStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: questionsServiceImpl
 * @Description: 互动答题学生答题记录管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/17 15:46
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "互动答题学生答题记录管理", groupName = "互动答题学生答题记录管理")
public class QuestionStuRecordServiceImpl extends SkyeyeBusinessServiceImpl<QuestionStuRecordDao, QuestionStuRecord> implements QuestionStuRecordService {

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private QuestionStuRecordService questionStuRecordService;

    @Autowired
    private SubjectClassesStuService subjectClassesStuService;

    @Autowired
    private ICertificationRest iCertificationRest;

    @Override
    public void submitQuestionAnswer(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String questionId = map.get("questionId").toString();
        Questions questions = questionsService.selectById(questionId);
        if(ObjectUtil.isNull(questions.getId())){
            throw new CustomException("题目id不存在");
        }
        questionStuRecordService.createEntity(inputObject, outputObject);
    }

    @Override
    public void queryRecordByQuestionId(InputObject inputObject, OutputObject outputObject) {
        String questionId = inputObject.getParams().get("questionId").toString();
        QueryWrapper<QuestionStuRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(QuestionStuRecord::getQuestionId), questionId);
        List<QuestionStuRecord> questionStuRecords = questionStuRecordService.list(queryWrapper);
        List<String> stuNoList = new ArrayList<>();
        if(CollectionUtil.isEmpty(questionStuRecords)){
            throw new CustomException("提交记录中无该questionId");
        }
        for(QuestionStuRecord questionStuRecord : questionStuRecords){
            stuNoList.add(questionStuRecord.getStuNo());
        }
        List<Map<String, Object>> userList = ExecuteFeignClient.get(() ->
            iCertificationRest.queryUserByStudentNumber(Joiner.on(CommonCharConstants.COMMA_MARK).join(stuNoList))).getRows();
        outputObject.setBeans(userList);
        outputObject.settotal(userList.size());
    }

    @Override
    public void validatorEntity(QuestionStuRecord questionStuRecord) {
        String stuNo = questionStuRecord.getStuNo();
        String questionId = questionStuRecord.getQuestionId();
        Questions questions = questionsService.selectById(questionId);
        QueryWrapper<SubjectClassesStu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(SubjectClassesStu::getStuNo), stuNo);
        List<SubjectClassesStu> subjectClassesStus = subjectClassesStuService.list(queryWrapper);
        if(ObjectUtil.isNull(questions.getId())){
            throw new CustomException("questionId有误");
        }
        if(CollectionUtil.isEmpty(subjectClassesStus)){
            throw new CustomException("stuNo学生学号有误");
        }
    }

    @Override
    public void createPrepose(QuestionStuRecord questionStuRecord) {
        String questionId = questionStuRecord.getQuestionId();
        Questions questions = questionsService.selectById(questionId);
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = formatter.format(now);
        questionStuRecord.setSubmit(formattedDateTime);
        questionStuRecord.setQuestionText(questions.getQuestionText());
        questionStuRecord.setSubjectClassesId(questions.getSubjectClassesId());
        questionStuRecord.setObjectId(questions.getObjectId());
        questionStuRecord.setObjectKey(questions.getObjectKey());
    }

    @Override
    public void deleteById(InputObject inputObject, OutputObject outputObject) {
        String questionId = inputObject.getParams().get("id").toString();
        List<String> ids = new ArrayList<>();
        QueryWrapper<QuestionStuRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(QuestionStuRecord::getQuestionId), questionId);
        List<QuestionStuRecord> questionStuRecords = questionStuRecordService.list(queryWrapper);
        for(QuestionStuRecord questionStuRecord : questionStuRecords){
            ids.add(questionStuRecord.getId());
        }
        questionStuRecordService.deleteById(ids);
    }
}
