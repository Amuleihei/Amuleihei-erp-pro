package com.skyeye.exam.examquchckbox.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examquchckbox.dao.ExamQuCheckboxDao;
import com.skyeye.exam.examquchckbox.entity.ExamQuCheckbox;
import com.skyeye.exam.examquchckbox.service.ExamQuCheckboxService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@SkyeyeService(name = "多选题选项表管理", groupName = "多选题选项表管理")
public class ExamQuCheckboxServiceImpl extends SkyeyeBusinessServiceImpl<ExamQuCheckboxDao, ExamQuCheckbox> implements ExamQuCheckboxService {

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        return beans;
    }

    @Override
    public void queryExamQuCheckboxListById(InputObject inputObject, OutputObject outputObject) {
        String examQuChenboxId = inputObject.getParams().get("id").toString();
        QueryWrapper<ExamQuCheckbox> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CommonConstants.ID,examQuChenboxId);
        List<ExamQuCheckbox> examQuCheckboxList = list(queryWrapper);
        outputObject.setBean(examQuCheckboxList);
        outputObject.settotal(examQuCheckboxList.size());
    }
}
