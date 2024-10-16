package com.skyeye.exam.examQuScore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examQuScore.dao.ExamQuScoreDao;
import com.skyeye.exam.examQuScore.entity.ExamQuScore;
import com.skyeye.exam.examQuScore.service.ExamQuScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ExamQuScoreServiceImpl
 * @Description: 公评分题行选项管理服务层
 * @author: skyeye云系列--lqy
 * @date: 2024/7/19 11:01
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "评分题行选项管理", groupName = "评分题行选项管理")
public class ExamQuScoreServiceImpl extends SkyeyeBusinessServiceImpl<ExamQuScoreDao, ExamQuScore> implements ExamQuScoreService {

    @Autowired
    private ExamQuScoreService examQuScoreService;

    @Override
    public void deleteQuScoreById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        ExamQuScore examQuScore = selectById(id);
        examQuScore.setVisibility(CommonNumConstants.NUM_ZERO);
        updateById(examQuScore);
        refreshCache(id);
    }

    @Override
    public void queryQuScoreListByQuId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String quId = map.get("quId").toString();
        LambdaQueryWrapper<ExamQuScore> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExamQuScore::getQuId, quId);
        List<ExamQuScore> list = examQuScoreService.list(queryWrapper);
        List<ExamQuScore> bean = list.stream()
                .filter(visibility -> visibility.getVisibility() == CommonNumConstants.NUM_ONE)
                .collect(Collectors.toList());
        outputObject.setBeans(bean);
        outputObject.settotal(bean.size());
    }
}
