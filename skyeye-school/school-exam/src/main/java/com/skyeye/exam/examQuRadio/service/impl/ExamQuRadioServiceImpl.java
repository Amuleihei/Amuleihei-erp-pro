package com.skyeye.exam.examQuRadio.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuRadio.dao.ExamQuRadioDao;
import com.skyeye.exam.examQuRadio.entity.ExamQuRadio;
import com.skyeye.exam.examQuRadio.service.ExamQuRadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@SkyeyeService(name = "单选题选项表管理", groupName = "单选题选项表管理")
public class ExamQuRadioServiceImpl extends SkyeyeBusinessServiceImpl<ExamQuRadioDao, ExamQuRadio> implements ExamQuRadioService {

    @Autowired
    private ExamQuRadioService examQuRadioService;

    @Override
    public void deleteQuRadioById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        ExamQuRadio examQuRadio = selectById(id);
        examQuRadio.setVisibility(CommonNumConstants.NUM_ZERO);
        updateById(examQuRadio);
        refreshCache(id);
    }

    @Override
    public void queryQuRadioListByQuId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String quId = map.get("quId").toString();
        LambdaQueryWrapper<ExamQuRadio> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExamQuRadio::getQuId, quId);
        List<ExamQuRadio> list = examQuRadioService.list(queryWrapper);
        List<ExamQuRadio> bean = list.stream()
            .filter(visibility -> visibility.getVisibility() == CommonNumConstants.NUM_ONE)
            .collect(Collectors.toList());
        outputObject.setBeans(bean);
        outputObject.settotal(bean.size());
    }
}
