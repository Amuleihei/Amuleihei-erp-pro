package com.skyeye.school.score.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exception.CustomException;
import com.skyeye.school.score.dao.ScoreDao;
import com.skyeye.school.score.entity.Score;
import com.skyeye.school.score.service.ScoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@SkyeyeService(name = "成绩管理", groupName = "成绩管理")
public class ScoreServiceImpl extends SkyeyeBusinessServiceImpl<ScoreDao, Score> implements ScoreService {

    @Override
    public void queryMyScoreListByNo(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String no = map.get("no").toString();
        QueryWrapper<Score> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("no", no);
        List<Score> list = list(queryWrapper);
        if(CollectionUtil.isEmpty(list)){
            throw new CustomException("暂无次学号学生的成绩");
        }
        outputObject.setBeans(list);
        outputObject.settotal(list.size());
    }
}
