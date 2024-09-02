/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.notice.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Joiner;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.*;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.enumeration.DeleteFlagEnum;
import com.skyeye.common.enumeration.UserStaffState;
import com.skyeye.common.enumeration.WhetherEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.notice.classenum.NoticeRealLinesType;
import com.skyeye.eve.notice.classenum.NoticeState;
import com.skyeye.eve.notice.classenum.NoticeTimeSend;
import com.skyeye.eve.notice.dao.NoticeDao;
import com.skyeye.eve.notice.entity.Notice;
import com.skyeye.eve.notice.service.NoticeService;
import com.skyeye.eve.notice.service.NoticeUserService;
import com.skyeye.eve.rest.mq.JobMateMation;
import com.skyeye.eve.rest.quartz.SysQuartzMation;
import com.skyeye.eve.service.IJobMateMationService;
import com.skyeye.eve.service.IQuartzService;
import com.skyeye.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: NoticeServiceImpl
 * @Description: 公告管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 21:36
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "公告管理", groupName = "公告管理")
public class NoticeServiceImpl extends SkyeyeBusinessServiceImpl<NoticeDao, Notice> implements NoticeService {

    @Autowired
    private IQuartzService iQuartzService;

    @Autowired
    private IJobMateMationService iJobMateMationService;

    @Autowired
    private NoticeUserService noticeUserService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo pageInfo = inputObject.getParams(CommonPageInfo.class);
        pageInfo.setDeleteFlag(DeleteFlagEnum.NOT_DELETE.getKey());
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryNoticeList(pageInfo);
        return beans;
    }

    @Override
    public void validatorEntity(Notice entity) {
        super.validatorEntity(entity);
        if (entity.getSendType().equals(WhetherEnum.DISABLE_USING.getKey())) {
            // 群发指定人
            if (CollectionUtil.isEmpty(entity.getReceiver())) {
                throw new CustomException("请选择公告接收人.");
            }
        }

        if (entity.getTimeSend().equals(NoticeTimeSend.SET_UP.getKey())) {
            // 定时发送
            entity.setRealLinesType(NoticeRealLinesType.AT_REGULAR_TIME.getKey());
            if (StrUtil.isEmpty(entity.getDelayedTime())) {
                throw new CustomException("请选择定时发送的时间.");
            }
            if (DateUtil.compare(entity.getDelayedTime(), DateUtil.getTimeAndToString())) {
                // 定时通知时间早于当前时间
                throw new CustomException("公告定时发送的时间不能早于当前时间，请重新设定发送时间.");
            }
        } else {
            // 手动发送
            entity.setRealLinesType(NoticeRealLinesType.HAND_MOVEMENT.getKey());
            if (entity.getState().equals(NoticeState.UP.getKey())) {
                entity.setRealLinesTime(DateUtil.getTimeAndToString());
            }
        }
    }

    @Override
    public void updatePrepose(Notice entity) {
        if (entity.getTimeSend().equals(NoticeTimeSend.DO_NOT_SET.getKey())) {
            // 手动发送
            Notice oldNotice = selectById(entity.getId());
            if (oldNotice.getTimeSend().equals(NoticeTimeSend.SET_UP.getKey())) {
                // 如果老的数据时定时发送，则删除定时任务
                iQuartzService.stopAndDeleteTaskQuartz(entity.getId());
            }
        }
    }

    @Override
    public void writePostpose(Notice entity, String userId) {
        super.writePostpose(entity, userId);
        List<String> stateList = Arrays.asList(new String[]{UserStaffState.ON_THE_JOB.getKey().toString(), UserStaffState.PROBATION.getKey().toString(), UserStaffState.PROBATION_PERIOD.getKey().toString()});
        if (entity.getSendType().equals(WhetherEnum.ENABLE_USING.getKey())) {
            // 群发所有人
            List<Map<String, Object>> userInfoList = skyeyeBaseMapper.queryAllUserList(null, stateList);
            List<String> userIds = userInfoList.stream().map(bean -> bean.get("userId").toString())
                .collect(Collectors.toList());
            noticeUserService.saveNoticeUser(entity.getId(), userIds);
        } else {
            // 群发指定人
            noticeUserService.saveNoticeUser(entity.getId(), entity.getReceiver());
        }

        if (entity.getTimeSend().equals(NoticeTimeSend.SET_UP.getKey())) {
            // 定时发送   启动定时任务，要求定时通知时间晚于当前时间
            this.startUpTaskQuartz(entity.getId(), entity.getName(), entity.getDelayedTime());
        } else {
            // 手动发送
            if (entity.getWhetherEmail().equals(WhetherEnum.ENABLE_USING.getKey())
                && entity.getState().equals(NoticeState.UP.getKey())) {
                // 开启了邮件通知并且是上线状态
                List<Map<String, Object>> userEmail;
                if (entity.getSendType().equals(WhetherEnum.ENABLE_USING.getKey())) {
                    // 群发所有人
                    userEmail = skyeyeBaseMapper.queryAllUserList(null, stateList);
                } else {
                    // 群发指定人
                    userEmail = skyeyeBaseMapper.queryAllUserList(entity.getReceiver(), stateList);
                }
                // 启动mq消息任务
                Map<String, Object> notice = new HashMap<>();
                notice.put("title", "公告提醒");
                notice.put("content", "内部公告 -【" + entity.getName() + "】");
                notice.put("email", userEmail);
                // 消息队列任务类型
                notice.put("type", MqConstants.JobMateMationJobType.NOTICE_SEND.getJobType());
                sendMQProducer(JSONUtil.toJsonStr(notice), userId);
            }
        }
    }

    @Override
    public Notice getFromCache(String key) {
        Notice notice = super.getFromCache(key);
        if (notice.getSendType().equals(WhetherEnum.DISABLE_USING.getKey())) {
            // 群发指定人
            notice.setReceiver(noticeUserService.queryNoticeUserByNoticeId(notice.getId()));
        }
        return notice;
    }

    @Override
    public Notice selectById(String id) {
        Notice notice = super.selectById(id);
        if (notice.getSendType().equals(WhetherEnum.DISABLE_USING.getKey())) {
            // 群发指定人
            List<Map<String, Object>> users = iAuthUserService.queryDataMationByIds(Joiner.on(CommonCharConstants.COMMA_MARK).join(notice.getReceiver()));
            notice.setReceiverMation(users);
        }
        return notice;
    }

    /**
     * 定时发送公告
     *
     * @param name
     * @param title
     * @param delayedTime
     */
    private void startUpTaskQuartz(String name, String title, String delayedTime) {
        SysQuartzMation sysQuartzMation = new SysQuartzMation();
        sysQuartzMation.setName(name);
        sysQuartzMation.setTitle(title);
        sysQuartzMation.setDelayedTime(delayedTime);
        sysQuartzMation.setGroupId(QuartzConstants.QuartzMateMationJobType.QUARTZ_NOTICE_GROUP_STR.getTaskType());
        iQuartzService.startUpTaskQuartz(sysQuartzMation);
    }

    private void sendMQProducer(String jsonStr, String userId) {
        JobMateMation jobMateMation = new JobMateMation();
        jobMateMation.setJsonStr(jsonStr);
        jobMateMation.setUserId(userId);
        iJobMateMationService.sendMQProducer(jobMateMation);
    }

    @Override
    public void deletePostpose(String id) {
        // 删除定时任务
        iQuartzService.stopAndDeleteTaskQuartz(id);
    }

    /**
     * 用户收到的公告
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryUserReceivedNotice(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo pageInfo = inputObject.getParams(CommonPageInfo.class);
        Page pages = PageHelper.startPage(pageInfo.getPage(), pageInfo.getLimit());
        pageInfo.setDeleteFlag(DeleteFlagEnum.NOT_DELETE.getKey());
        pageInfo.setObjectId(inputObject.getLogParams().get("id").toString());
        pageInfo.setState(NoticeState.UP.getKey().toString());
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryNoticeList(pageInfo);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    @Override
    public void editNoticeStateToUp(String id) {
        UpdateWrapper<Notice> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, id);
        updateWrapper.set(MybatisPlusUtil.toColumns(Notice::getState), NoticeState.UP.getKey());
        updateWrapper.set(MybatisPlusUtil.toColumns(Notice::getRealLinesTime), DateUtil.getTimeAndToString());
        update(updateWrapper);
        refreshCache(id);
    }

    @Override
    public void queryUserReceivedTopNotice(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo pageInfo = inputObject.getParams(CommonPageInfo.class);
        Page pages = PageHelper.startPage(CommonNumConstants.NUM_ONE, CommonNumConstants.NUM_EIGHT);
        pageInfo.setDeleteFlag(DeleteFlagEnum.NOT_DELETE.getKey());
        pageInfo.setObjectId(inputObject.getLogParams().get("id").toString());
        pageInfo.setState(NoticeState.UP.getKey().toString());
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryNoticeList(pageInfo);
        iSysDictDataService.setNameForMap(beans, "typeId", "typeName");
        String serviceClassName = getServiceClassName();
        beans.forEach(bean -> {
            bean.put("serviceClassName", serviceClassName);
        });
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

}
