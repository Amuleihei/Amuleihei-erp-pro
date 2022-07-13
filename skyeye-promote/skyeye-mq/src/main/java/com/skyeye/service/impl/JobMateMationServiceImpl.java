/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.service.impl;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.constans.MqConstants;
import com.skyeye.common.constans.SocketConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.dao.JobMateMationDao;
import com.skyeye.eve.entity.mq.JobMateQueryDO;
import com.skyeye.exception.CustomException;
import com.skyeye.service.JobMateMationService;
import com.skyeye.websocket.TalkWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Queue;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: JobMateMationServiceImpl
 * @Description: 任务管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:56
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class JobMateMationServiceImpl implements JobMateMationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobMateMationServiceImpl.class);

    @Autowired
    private JobMateMationDao jobMateMationDao;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private TalkWebSocket talkWebSocket;

    @Autowired
    private Queue queue;

    /**
     * 根据大类获取任务信息
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void queryJobMateMationByBigTypeList(InputObject inputObject, OutputObject outputObject) {
        JobMateQueryDO jobMateQuery = inputObject.getParams(JobMateQueryDO.class);
        jobMateQuery.setUserId(inputObject.getLogParams().get("id").toString());
        Page pages = PageHelper.startPage(jobMateQuery.getPage(), jobMateQuery.getLimit());
        List<Map<String, Object>> beans = jobMateMationDao.queryJobMateMationByBigTypeList(jobMateQuery);
        beans.forEach(bean -> bean.put("jobTypeName", MqConstants.JobMateMationJobType.getTypeNameByJobType(bean.get("jobType").toString())));
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 其他模块同步生产消息
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void sendMQProducer(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String jsonStr = map.get("jsonStr").toString();
        String userId = map.get("userId").toString();
        this.sendMQProducer(jsonStr, userId);
    }

    /**
     * 开始生产消息
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void sendMQProducer(String jsonStr, String userId) {
        // 父任务
        Map<String, Object> parentJob;
        try {
            parentJob = resetParentJobMation(jsonStr, userId);
        } catch (UnknownHostException e) {
            LOGGER.warn("sendMQProducer failed, reason is {}.", e);
            throw new CustomException(e);
        }
        jobMateMationDao.insertJobMation(parentJob);
        LOGGER.info("create job mation is {}", JSONUtil.toJsonStr(parentJob));
        // 重置请求体中的任务id
        Map<String, Object> map = JSONUtil.toBean(jsonStr, null);
        map.put("jobMateId", parentJob.get("jobId"));
        parentJob.put("requestBody", JSONUtil.toJsonStr(map));
        jobMateMationDao.editJobRequestBodyMation(parentJob);
        // 发起消息
        jmsMessagingTemplate.convertAndSend(queue, parentJob.get("requestBody").toString());
    }

    /**
     * 构造父任务对象
     *
     * @param jsonStr
     * @param userId
     * @return
     * @throws UnknownHostException
     */
    public Map<String, Object> resetParentJobMation(String jsonStr, String userId) throws UnknownHostException {
        Map<String, Object> map = JSONUtil.toBean(jsonStr, null);
        String jobType = map.get("type").toString();
        Map<String, Object> job = new HashMap<>();
        job.put("title", map.containsKey("title") ? map.get("title") : MqConstants.JobMateMationJobType.getTypeNameByJobType(jobType));
        job.put("jobType", jobType);
        job.put("bigType", MqConstants.JobMateMationJobType.getBigTypeByJobType(jobType));
        // 父任务
        job.put("type", 1);
        job.put("parentId", 0);
        job.put("requestBody", jsonStr);
        // 父任务默认处理中，子任务默认等待处理
        job.put("status", MqConstants.JOB_TYPE_IS_PROCESSING);
        job.put("createId", userId);
        InetAddress address = InetAddress.getLocalHost();
        job.put("createIp", address.getHostAddress());
        job.put("createTime", DateUtil.getTimeAndToString());
        return job;
    }

    /**
     * 任务状态修改
     *
     * @param jobId
     * @param status
     * @param responseBody
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void comMQJobMation(String jobId, String status, String responseBody) {
        Map<String, Object> jobMation = jobMateMationDao.queryJobMationByJobId(jobId);
        // 类型  1.父任务2.子任务
        String type = jobMation.get("type").toString();
        jobMateMationDao.editJobMationByJobId(jobId, status, responseBody, DateUtil.getTimeAndToString());
        if (MqConstants.JOB_TYPE_IS_SUCCESS.equals(status) || MqConstants.JOB_TYPE_IS_FAIL.equals(status)) {
            // 任务修改为成功或者失败时执行
            if ("2".equals(type)) {
                // 子任务类型
                String parentJobId = jobMation.get("jobId").toString();
                // 获取未完成的子任务
                List<Map<String, Object>> noComChildJob = jobMateMationDao.queryNoComChildJobMationByJobId(parentJobId);
                if (noComChildJob == null || noComChildJob.isEmpty()) {
                    // 如果已经没有未完成的子任务,获取失败的子任务
                    List<Map<String, Object>> failChildJob = jobMateMationDao.queryFailChildJobMationByJobId(parentJobId);
                    if (failChildJob == null || failChildJob.isEmpty()) {
                        // 如果没有失败的子任务,则父任务执行成功
                        jobMateMationDao.editJobMationByJobId(parentJobId, MqConstants.JOB_TYPE_IS_SUCCESS, responseBody, DateUtil.getTimeAndToString());
                        sendJobResultMseeage(parentJobId, MqConstants.JOB_TYPE_IS_SUCCESS);
                    } else {
                        // 父任务部分成功
                        jobMateMationDao.editJobMationByJobId(parentJobId, MqConstants.JOB_TYPE_IS_PARTIAL_SUCCESS, responseBody, DateUtil.getTimeAndToString());
                        sendJobResultMseeage(parentJobId, MqConstants.JOB_TYPE_IS_PARTIAL_SUCCESS);
                    }
                }
            } else {
                // 父任务类型
                sendJobResultMseeage(jobId, status);
            }
        }
    }

    /**
     * 推送任务消息给前台
     *
     * @param jobId
     * @param status
     */
    private void sendJobResultMseeage(String jobId, String status) {
        Map<String, Object> jobMation = jobMateMationDao.queryJobMationByJobId(jobId);
        String userId = jobMation.get("createId").toString();
        LOGGER.info("job is success, jobId is {}", jobId);
        if (MqConstants.JOB_TYPE_IS_SUCCESS.equals(status) || MqConstants.JOB_TYPE_IS_FAIL.equals(status)
            || MqConstants.JOB_TYPE_IS_PARTIAL_SUCCESS.equals(status)) {
            // 成功/失败/部分成功
            String jobType = jobMation.get("jobType").toString();
            // 所属大类
            int bigType = MqConstants.JobMateMationJobType.getBigTypeByJobType(jobType);
            boolean sendMsgToPage = MqConstants.JobMateMationJobType.getSendMsgToPageByJobType(jobType);
            if (sendMsgToPage) {
                try {
                    talkWebSocket.sendMessageTo(JSONUtil.toJsonStr(getMsg(status, bigType, jobType)), userId);
                } catch (IOException e) {
                    LOGGER.warn("sendJobResultMseeage failed, reason is {}.", e);
                    throw new CustomException(e);
                }
            }
        }
    }

    private Map<String, Object> getMsg(String status, int bigType, String jobType) {
        Map<String, Object> result = new HashMap<>();
        result.put("messageType", SocketConstants.MessageType.Fifth.getType());
        result.put("bigType", bigType);
        result.put("jobType", jobType);
        result.put("state", status);
        return result;
    }

}
