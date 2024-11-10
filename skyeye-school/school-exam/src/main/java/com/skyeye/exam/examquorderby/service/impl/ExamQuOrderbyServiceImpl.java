package com.skyeye.exam.examquorderby.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.exam.examquorderby.dao.ExamQuOrderbyDao;
import com.skyeye.exam.examquorderby.entity.ExamQuOrderby;
import com.skyeye.exam.examquorderby.service.ExamQuOrderbyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@SkyeyeService(name = "排序题行选项管理", groupName = "排序题行选项管理")
public class ExamQuOrderbyServiceImpl extends SkyeyeBusinessServiceImpl<ExamQuOrderbyDao, ExamQuOrderby> implements ExamQuOrderbyService {

    @Override
    public void saveList(List<ExamQuOrderby> score, String quId, String userId) {
        List<ExamQuOrderby> quOrderBy = new ArrayList<>();
        List<ExamQuOrderby> editquOrderBy = new ArrayList<>();
        for (int i = 0; i < score.size(); i++) {
            ExamQuOrderby object = score.get(i);
            ExamQuOrderby bean = new ExamQuOrderby();
            bean.setOrderById(object.getOrderById());
            bean.setOptionName(object.getOptionName());
            if (ToolUtil.isBlank(object.getOptionId())) {
                bean.setQuId(quId);
                bean.setVisibility(1);
                bean.setId(ToolUtil.getSurFaceId());
                bean.setCreateId(userId);
                bean.setCreateTime(DateUtil.getTimeAndToString());
                quOrderBy.add(bean);
            } else {
                bean.setId(object.getOptionId());
                editquOrderBy.add(bean);
            }
        }
        if (!quOrderBy.isEmpty()) {
            createEntity(quOrderBy, userId);
        }
        if (!editquOrderBy.isEmpty()) {
            updateEntity(editquOrderBy, userId);
        }
        quOrderBy.addAll(editquOrderBy);
    }
}
