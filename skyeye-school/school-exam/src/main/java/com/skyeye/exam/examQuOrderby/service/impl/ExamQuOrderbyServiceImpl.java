package com.skyeye.exam.examQuOrderby.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuMultfillblank.entity.ExamQuMultiFillblank;
import com.skyeye.exam.examQuOrderby.dao.ExamQuOrderbyDao;
import com.skyeye.exam.examQuOrderby.entity.ExamQuOrderby;
import com.skyeye.exam.examQuOrderby.service.ExamQuOrderbyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@SkyeyeService(name = "排序题行选项管理", groupName = "排序题行选项管理")
public class ExamQuOrderbyServiceImpl extends SkyeyeBusinessServiceImpl<ExamQuOrderbyDao, ExamQuOrderby> implements ExamQuOrderbyService {

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        return beans;
    }

    @Override
    public void queryExamQuOrderbyListById(InputObject inputObject, OutputObject outputObject) {
        String examQuOrderbyId = inputObject.getParams().get("id").toString();
        QueryWrapper<ExamQuOrderby> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CommonConstants.ID,examQuOrderbyId);
        List<ExamQuOrderby> examQuOrderbyList = list(queryWrapper);
        outputObject.setBean(examQuOrderbyList);
        outputObject.settotal(examQuOrderbyList.size());
    }
}
