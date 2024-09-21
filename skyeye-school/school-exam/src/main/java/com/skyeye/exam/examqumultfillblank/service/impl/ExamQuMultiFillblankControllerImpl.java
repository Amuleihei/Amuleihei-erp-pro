package com.skyeye.exam.examqumultfillblank.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examqumultfillblank.dao.ExamQuMultiFillblankDao;
import com.skyeye.exam.examqumultfillblank.entity.ExamQuMultiFillblank;
import com.skyeye.exam.examqumultfillblank.service.ExamQuMultiFillblankService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@SkyeyeService(name = "多行填空题管理", groupName = "多行填空题管理")
public class ExamQuMultiFillblankControllerImpl extends SkyeyeBusinessServiceImpl<ExamQuMultiFillblankDao, ExamQuMultiFillblank> implements ExamQuMultiFillblankService {

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        return beans;
    }

    @Override
    public void queryExamQuMultiFillblankListById(InputObject inputObject, OutputObject outputObject) {
        String examQuMultiFillblankId = inputObject.getParams().get("id").toString();
        QueryWrapper<ExamQuMultiFillblank> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CommonConstants.ID,examQuMultiFillblankId);
        List<ExamQuMultiFillblank> examQuMultiFillblankList = list(queryWrapper);
        outputObject.setBean(examQuMultiFillblankList);
        outputObject.settotal(examQuMultiFillblankList.size());
    }
}
