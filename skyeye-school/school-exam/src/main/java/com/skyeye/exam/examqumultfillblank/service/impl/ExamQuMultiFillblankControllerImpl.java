package com.skyeye.exam.examqumultfillblank.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.exam.examqumultfillblank.dao.ExamQuMultiFillblankDao;
import com.skyeye.exam.examqumultfillblank.entity.ExamQuMultiFillblank;
import com.skyeye.exam.examqumultfillblank.service.ExamQuMultiFillblankService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@SkyeyeService(name = "多行填空题管理", groupName = "多行填空题管理")
public class ExamQuMultiFillblankControllerImpl extends SkyeyeBusinessServiceImpl<ExamQuMultiFillblankDao, ExamQuMultiFillblank> implements ExamQuMultiFillblankService {

    @Override
    public void saveList(List<ExamQuMultiFillblank> list, String quId, String userId) {
        List<ExamQuMultiFillblank> quMultiFillblank = new ArrayList<>();
        List<ExamQuMultiFillblank> editquMultiFillblank = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ExamQuMultiFillblank object = list.get(i);
            ExamQuMultiFillblank bean = new ExamQuMultiFillblank();
            bean.setOrderById(object.getOrderById());
            bean.setOptionName(object.getOptionName());
            bean.setIsDefaultAnswer(object.getIsDefaultAnswer());
            if (ToolUtil.isBlank(object.getOptionId())) {
                bean.setQuId(quId);
                bean.setVisibility(1);
                bean.setId(ToolUtil.getSurFaceId());
                bean.setCreateId(userId);
                bean.setCreateTime(DateUtil.getTimeAndToString());
                quMultiFillblank.add(bean);
            } else {
                bean.setId(object.getOptionId());
                editquMultiFillblank.add(bean);
            }
        }
        if (!quMultiFillblank.isEmpty()) {
            createEntity(quMultiFillblank, userId);
        }
        if (!editquMultiFillblank.isEmpty()) {
            updateEntity(editquMultiFillblank, userId);
        }
    }
}
