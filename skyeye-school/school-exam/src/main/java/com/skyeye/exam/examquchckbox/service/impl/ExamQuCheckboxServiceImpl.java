package com.skyeye.exam.examquchckbox.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.common.util.question.CheckType;
import com.skyeye.exam.examquchckbox.dao.ExamQuCheckboxDao;
import com.skyeye.exam.examquchckbox.entity.ExamQuCheckbox;
import com.skyeye.exam.examquchckbox.service.ExamQuCheckboxService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@SkyeyeService(name = "多选题选项表管理", groupName = "多选题选项表管理")
public class ExamQuCheckboxServiceImpl extends SkyeyeBusinessServiceImpl<ExamQuCheckboxDao, ExamQuCheckbox> implements ExamQuCheckboxService {

    @Override
    public void saveList(List<ExamQuCheckbox> list, String quId, String userId) {
        List<ExamQuCheckbox> quCheckbox = new ArrayList<>();
        List<ExamQuCheckbox> editquCheck = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ExamQuCheckbox object = list.get(i);
            ExamQuCheckbox bean = new ExamQuCheckbox();
            bean.setOrderById(object.getOrderById());
            bean.setOptionName(object.getOptionName());
            bean.setIsNote(object.getIsNote());
            bean.setIsDefaultAnswer(object.getIsDefaultAnswer());
            if (!ToolUtil.isNumeric(object.getCheckType().toString())) {
                bean.setCheckType( CheckType.valueOf(object.getCheckType().toString()).getIndex());
            } else {
                bean.setCheckType(object.getCheckType());
            }
            bean.setIsRequiredFill(object.getIsRequiredFill());
            if (ToolUtil.isBlank(object.getOptionId())) {
                bean.setQuId(object.getQuId());
                bean.setQuId(quId);
                bean.setVisibility(1);
                bean.setId(ToolUtil.getSurFaceId());
                bean.setCreateId(userId);
                bean.setCreateTime(DateUtil.getTimeAndToString());
                quCheckbox.add(bean);
            } else {
                bean.setId(object.getOptionId());
                editquCheck.add(bean);
            }
        }
        if (!quCheckbox.isEmpty()) {
            createEntity(quCheckbox, userId);
        }
        if (!editquCheck.isEmpty()) {
            updateEntity(editquCheck, userId);
        }
    }
}
