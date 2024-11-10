package com.skyeye.exam.examquchenoption.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.exam.examquchenoption.dao.ExamQuChenOptionDao;
import com.skyeye.exam.examquchenoption.entity.ExamQuChenOption;
import com.skyeye.exam.examquchenoption.service.ExamQuChenOptionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@SkyeyeService(name = "矩陈题-题选项管理", groupName = "矩陈题-题选项管理")
public class ExamQuChenOptionServiceImpl extends SkyeyeBusinessServiceImpl<ExamQuChenOptionDao, ExamQuChenOption> implements ExamQuChenOptionService {

    @Override
    public void saveList(List<ExamQuChenOption> list, String quId, String userId) {
        List<ExamQuChenOption> quOption = new ArrayList<>();
        List<ExamQuChenOption> editquOption = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ExamQuChenOption object = list.get(i);
            ExamQuChenOption bean = new ExamQuChenOption();
            bean.setOrderById(object.getOrderById());
            bean.setOptionName(object.getOptionName());
            if (ToolUtil.isBlank(object.getOptionId())) {
                bean.setQuId(object.getQuId());
                bean.setId(ToolUtil.getSurFaceId());
                bean.setCreateId(userId);
                bean.setCreateTime(DateUtil.getTimeAndToString());
                quOption.add(bean);
            } else {
                bean.setId(object.getOptionId());
                editquOption.add(bean);
            }
        }
        if (!quOption.isEmpty()) {
            createEntity(quOption, userId);
        }
        if (!editquOption.isEmpty()) {
            updateEntity(editquOption, userId);
        }
    }
}
