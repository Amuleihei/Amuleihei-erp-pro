/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.classenum.ReadState;
import com.skyeye.eve.dao.JobDiaryReceivedDao;
import com.skyeye.eve.entity.JobDiaryReceived;
import com.skyeye.eve.service.JobDiaryReceivedService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: JobDiaryReceivedServiceImpl
 * @Description: 工作日志接收人服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/24 13:41
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Service
@SkyeyeService(name = "工作日志接收人", groupName = "工作日志接收人", manageShow = false)
public class JobDiaryReceivedServiceImpl extends SkyeyeBusinessServiceImpl<JobDiaryReceivedDao, JobDiaryReceived> implements JobDiaryReceivedService {

    @Override
    public void deleteByDiaryId(String diaryId) {
        QueryWrapper<JobDiaryReceived> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(JobDiaryReceived::getDiaryId), diaryId);
        remove(queryWrapper);
    }

    @Override
    public void save(String diaryId, List<String> receivedIdList) {
        deleteByDiaryId(diaryId);
        receivedIdList = receivedIdList.stream()
            .filter(receivedId -> StrUtil.isNotEmpty(receivedId)).distinct().collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(receivedIdList)) {
            List<JobDiaryReceived> jobDiaryReceiveds = new ArrayList<>();
            for (String receivedId : receivedIdList) {
                JobDiaryReceived jobDiaryReceived = new JobDiaryReceived();
                jobDiaryReceived.setReceivedId(receivedId);
                jobDiaryReceived.setDiaryId(diaryId);
                jobDiaryReceived.setState(ReadState.NOT_READ.getKey());
                jobDiaryReceiveds.add(jobDiaryReceived);
            }
            createEntity(jobDiaryReceiveds, StrUtil.EMPTY);
        }
    }

    @Override
    public List<String> queryByDiaryId(String diaryId) {
        QueryWrapper<JobDiaryReceived> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(JobDiaryReceived::getDiaryId), diaryId);
        List<JobDiaryReceived> jobDiaryReceiveds = list(queryWrapper);
        return jobDiaryReceiveds.stream().map(JobDiaryReceived::getReceivedId).collect(Collectors.toList());
    }
}
